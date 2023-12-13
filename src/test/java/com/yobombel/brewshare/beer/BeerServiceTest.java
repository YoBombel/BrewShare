package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
import com.yobombel.brewshare.beer.ingredient.Ingredient;
import com.yobombel.brewshare.beer.ingredient.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;
    @Mock
    private IngredientService ingredientService;
    @Captor
    private ArgumentCaptor<Beer> captor;
    @InjectMocks
    @Spy
    private BeerService beerService;

    private Long id;
    private Beer beer;

    @BeforeEach
    void init() {
        id = 1L;
        beer = new Beer();
        beer.setId(id);
        beer.setName("TestBeer");
    }

    @Test
    void shouldAdd() {
        //GIVEN
        given(beerRepository.save(captor.capture())).willReturn(beer);

        //WHEN
        beerService.add(beer);

        //THEN
        verify(beerRepository).save(beer);
    }

    @Test
    void shouldAddAll(){
        //GIVEN
        List<Beer> beers = List.of(beer);
        given(beerRepository.save(beer)).willReturn(beer);
        //WHEN
        List<Beer> result = beerService.addAll(beers);
        //THEN
        verify(beerService).add(beer);
        verify(beerRepository).save(beer);
        verify(ingredientService).addAllForBeer(beer);
        assertThat(result)
                .hasSize(1)
                .contains(beer);
    }

    @Test
    void shouldAddWithIngredients() {
        //GIVEN
        when(beerRepository.save(any(Beer.class))).thenReturn(beer);
        Ingredient ingredient = new Ingredient();
        List<Ingredient> ingredients = List.of(ingredient);
        beer.setIngredients(ingredients);

        //WHEN
        beerService.add(beer);

        //THEN
        verify(ingredientService).addAllForBeer(beer);
        verify(beerRepository).save(beer);

    }

    @Test
    void shouldFindAll() {
        //GIVEN
        given(beerRepository.findAll()).willReturn(List.of(beer));
        //WHEN
        beerService.findAll();
        //THEN
        verify(beerRepository).findAll();
    }

    @Test
    void shouldCallRepositoryDelete() {
        //GIVEN
        when(beerRepository.findById(id)).thenReturn(Optional.of(beer));

        //WHEN
        beerService.deleteById(id);

        //THEN
        verify(beerRepository).deleteById(id);
    }

    @Test
    void shouldUpdate() {
        //GIVEN
        given(beerRepository.save(beer)).willReturn(beer);
        given(beerRepository.findById(id)).willReturn(Optional.ofNullable(beer));

        //WHEN
        beerService.update(id, beer);

        //THEN
        verify(beerRepository).save(beer);
    }

    @Test
    void shouldThrowExceptionWhenBeerNotFound() {
        //GIVEN
        given(beerRepository.findById(id)).willReturn(Optional.empty());
        String expectedExceptionMessage = "Beer not found, id: " + id;

        //WHEN
        Throwable thrown = catchThrowable(() -> beerService.findById(id));
        //THEN
        assertThat(thrown)
                .isExactlyInstanceOf(BeerNotFoundException.class)
                .hasMessage(expectedExceptionMessage);
        verify(beerRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenBeerToDeleteNotFound() {
        //GIVEN
        when(beerRepository.findById(id)).thenReturn(Optional.empty());
        String expectedExceptionMessage = "Beer not found, id: " + id;

        //WHEN
        Throwable thrown = catchThrowable(() -> beerService.deleteById(id));
        //THEN
        assertThat(thrown)
                .isExactlyInstanceOf(BeerNotFoundException.class)
                .hasMessage(expectedExceptionMessage);
        verify(beerRepository).findById(id);
    }

    @Test
    void shouldFindBeerPage(){
        //GIVEN
        PageImpl<Beer> page = new PageImpl<>(List.of(beer));
        given(beerRepository.findAll(any(PageRequest.class))).willReturn(page);
        //WHEN
        final var result = beerService.findBeerPage(0, 1);
        final var firstElement = result.iterator().next();
        //THEN
        assertThat(result)
                .hasSize(1)
                .hasExactlyElementsOfTypes(Beer.class);
        assertThat(firstElement).usingRecursiveComparison().comparingOnlyFields("name").isEqualTo(beer);
        verify(beerRepository).findAll((any(PageRequest.class)));
    }

    @Test
    void shouldDeleteAll() {
        //GIVEN
        //WHEN
        beerService.deleteAll();
        //THEN
        verify(beerRepository).deleteAll();
    }

}