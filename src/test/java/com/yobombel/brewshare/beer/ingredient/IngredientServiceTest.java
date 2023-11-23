package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.beer.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

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
    }

    @Test
    void shouldDeleteAllFromList(){
        //GIVEN
        //WHEN
        ingredientService.deleteAllFromList(ingredients);
        //THEN
        verify(ingredientRepository).deleteAll(ingredients);
    }

}