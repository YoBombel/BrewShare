package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.beer.Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BeersmithIngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    @Spy
    private IngredientService ingredientService;

    private Long id;
    private List<Ingredient> ingredients;
    private Ingredient ingredient;

    @BeforeEach
    void init() {
        id = 1L;
        ingredient = new Ingredient();
        ingredient.setName("TestBeer");
        ingredients = new ArrayList<>();
        ingredients.add(ingredient);
    }

    @Test
    void shouldAdd(){
        //GIVEN
        given(ingredientRepository.save(any(Ingredient.class))).willReturn(ingredient);
        //WHEN
        final var result = ingredientService.add(ingredient);
        //THEN
        assertThat(result).isExactlyInstanceOf(Ingredient.class);
        verify(ingredientRepository).save(ingredient);
    }

    @Test
    void shouldFindAll(){
        //GIVEN
        given(ingredientRepository.findAll()).willReturn(List.of(ingredient));
        //WHEN
        final var result = ingredientService.findAll();
        final var first = result.iterator().next();
        //THEN
        assertThat(result).asList()
                .hasSize(1)
                .hasExactlyElementsOfTypes(Ingredient.class);
        assertThat(first).usingRecursiveComparison()
                .comparingOnlyFields("name")
                .isEqualTo(ingredient);
    }

    @Test
    void shouldAddAllForBeer(){
        //GIVEN
        Beer beer = new Beer();
        beer.setIngredients(ingredients);
        given(ingredientRepository.saveAll(anyList())).willReturn(ingredients);
        //WHEN
        ingredientService.addAllForBeer(beer);
        final var beerIngredient = beer.getIngredients().get(0);
        //THEN
        assertThat(beerIngredient.getBeer()).isEqualTo(beer);
        verify(ingredientRepository).saveAll(ingredients);
    }

    @Test
    void shouldUpdateEditedIngredients(){
        Beer beer = new Beer();
        Beer oldBeer = new Beer();
        beer.setIngredients(ingredients);
        given(ingredientRepository.saveAll(anyList())).willReturn(ingredients);
        //WHEN
        ingredientService.updateEditedIngredients(oldBeer, beer);
        final var result = beer.getIngredients();
        InOrder inOrder = Mockito.inOrder(ingredientRepository);
        //THEN
        assertThat(result.get(0).getBeer()).isEqualTo(beer);
        inOrder.verify(ingredientRepository).deleteAll(anyList());
        inOrder.verify(ingredientRepository).saveAll(ingredients);
    }

    @Test
    void shouldDelete(){
        //GIVEN
        //WHEN
        ingredientService.delete(ingredient);
        //THEN
        verify(ingredientRepository).delete(ingredient);
    }

    //TODO: temporary method, delete after implementing better beer examples
    @Test
    void shouldDeleteAll(){
        //GIVEN
        //WHEN
        ingredientService.deleteAllIngredients();
        //THEN
        verify(ingredientRepository).deleteAll();
    }

    @Test
    void shouldDeleteAllFromList() {
        //GIVEN
        //WHEN
        ingredientService.deleteAllFromList(ingredients);
        //THEN
        verify(ingredientRepository).deleteAll(ingredients);
    }

}