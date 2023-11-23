package com.yobombel.brewshare.beer.ingredient;

import com.yobombel.brewshare.beer.Beer;
import com.yobombel.brewshare.beer.BeerRepository;
import com.yobombel.brewshare.beer.BeerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    List<Ingredient> ingredients;

    @BeforeEach
    void setUp() {
        ingredientRepository.deleteAll();
        beerRepository.deleteAll();

        ingredients = new ArrayList<>();
        beer = new Beer();
        ingredient = new Ingredient();

        beer.setName("Test Beer");
        Long beerId = beerService.add(beer);

        ingredient.setBeer(beerService.findById(beerId));
        ingredient.setName("Test Ingredient");
        ingredient.setAmount(1234.56D);

        ingredients.add(ingredient);
        beer.setIngredients(ingredients);
        ingredientService.add(ingredient);

        beerService.update(beerId, beer);
    }

    @Test
    void shouldFindAllIngredients(){
        //GIVEN
        //WHEN
        List<Ingredient> result = ingredientService.findAll();

        //THEN
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldDeleteAllIngredientsFromList(){
        //GIVEN
        //WHEN
        ingredientService.deleteAllFromList(ingredients);
        List<Ingredient> result = ingredientService.findAll();

        //THEN
        assertTrue(result.isEmpty());
    }

}
