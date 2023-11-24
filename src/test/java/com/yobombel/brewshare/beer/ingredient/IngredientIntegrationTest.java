package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.beer.BeerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IngredientIntegrationTest {

    @LocalServerPort
    private Integer port;

    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.2.0");

    @BeforeAll
    static void beforeAll() {
        mysql.start();
    }

    @AfterAll
    static void afterAll() {
        mysql.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    IngredientService ingredientService;

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerService beerService;

    Long id;
    Beer beer;
    Ingredient ingredient;
    Ingredient ingredient2;
    List<Ingredient> ingredients;

    @BeforeEach
    void setUp() {
        ingredientRepository.deleteAll();
        beerRepository.deleteAll();

        ingredients = new ArrayList<>();
        beer = new Beer();
        ingredient = new Ingredient();
        ingredient2 = new Ingredient();

        beer.setName("Test Beer");
        Long beerId = beerService.add(beer).getId();

        ingredient.setBeer(beerService.findById(beerId));
        ingredient.setName("Test Ingredient");
        ingredient.setAmount(1234.56D);

        ingredient.setBeer(beerService.findById(beerId));
        ingredient.setName("Test Ingredient2");
        ingredient.setAmount(1.0D);

        ingredients.add(ingredient);
        ingredients.add(ingredient2);

        beer.setIngredients(ingredients);
        ingredientService.add(ingredient);

        beerService.update(beerId, beer);
    }

    @Test
    void shouldFindAllIngredients() {
        //GIVEN
        //WHEN
        List<Ingredient> result = ingredientService.findAll();

        //THEN
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldDeleteAllIngredientsFromList() {
        //GIVEN
        //WHEN
        ingredientService.deleteAllFromList(ingredients);
        List<Ingredient> result = ingredientService.findAll();

        //THEN
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldUpdateIngredients(){
        //GIVEN
        String editedName = "Edited ingredient name";
        Beer editedBeer = new Beer();
        BeanUtils.copyProperties(beer, editedBeer);

        Ingredient editedIngredient = new Ingredient();
        BeanUtils.copyProperties(ingredient, editedIngredient);
        editedIngredient.setName(editedName);

        editedBeer.setIngredients(List.of(editedIngredient));

        //WHEN
        ingredientService.updateIngredientsForBeer(beer, editedBeer);
        String resultName = beerService.findById(editedBeer.getId()).getIngredients().get(0).getName();

        //THEN
        assertEquals(resultName, editedName);
    }

    @Test
    void shouldRemoveUnusedIngredientsUponUpdate(){
        //GIVEN
        Beer editedBeer = new Beer();
        BeanUtils.copyProperties(beer, editedBeer);

        Ingredient editedIngredient = new Ingredient();
        BeanUtils.copyProperties(ingredient, editedIngredient);
        editedIngredient.setName("Edited ingredient name");

        editedBeer.setIngredients(List.of(editedIngredient));

        //WHEN
        ingredientService.updateIngredientsForBeer(beer, editedBeer);
        List<Ingredient> resultDbIngredients = ingredientService.findAll();

        //THEN
        assertThat(resultDbIngredients.size()).isEqualTo(1);
    }

}
