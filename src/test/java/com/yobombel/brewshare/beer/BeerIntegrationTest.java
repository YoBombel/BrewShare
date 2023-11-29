package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
import com.yobombel.brewshare.beer.ingredient.Ingredient;
import com.yobombel.brewshare.beer.ingredient.IngredientRepository;
import com.yobombel.brewshare.beer.ingredient.IngredientService;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerIntegrationTest {

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
    BeerRepository beerRepository;
    @Autowired
    BeerService beerService;

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    IngredientService ingredientService;

    Long id;
    Beer beer;
    Ingredient ingredient;
    List<Ingredient> ingredients;

    @BeforeEach
    void setUp() {
        ingredientRepository.deleteAll();
        beerRepository.deleteAll();
        beer = new Beer();
        beer.setName("Test Beer");
        ingredient = new Ingredient();
        ingredients = List.of(ingredient);
    }

    @Test
    void shouldAddBeer() {
        //GIVEN
        //WHEN
        beerService.add(beer);
        List<Beer> beerList = beerRepository.findAll();
        //THEN
        assertThat(beerList).hasSize(1);
    }

    @Test
    void shouldAddBeerWithIngredients() {
        //GIVEN
        beer.setIngredients(ingredients);

        //WHEN
        Beer result = beerService.add(beer);
        List<Beer> beerList = beerRepository.findAll();


        //THEN
        assertThat(beerList).hasSize(1);
        assertThat(result.getIngredients()).isNotEmpty();
    }

    @Test
    void shouldDeleteById() {
        //GIVEN
        Long id = beerService.add(beer).getId();
        //WHEN
        beerService.deleteById(id);
        List<Beer> beerList = beerRepository.findAll();

        //THEN
        assertThat(beerList).isEmpty();
    }

    @Test
    void shouldUpdate() {
        //GIVEN
        Long id = beerService.add(beer).getId();
        Beer updatedBeer = beerService.findById(id);
        String updatedName = "UpdatedName";
        updatedBeer.setName(updatedName);

        //WHEN
        beerService.update(id, updatedBeer);
        Beer result = beerService.findById(id);
        List<Beer> allBeers = beerService.findAll();

        //THEN
        assertThat(result.getName()).isEqualTo(updatedName);
        assertThat(allBeers).hasSize(1);
    }

    @Test
    void shouldThrowBeerNotFoundException() {
        //GIVEN
        id = 1L;
        String expectedExceptionMessage = "Beer not found, id: " + id;
        beerService.add(beer);
        beerRepository.deleteAll();
        //WHEN
        Throwable thrown = catchThrowable(() -> beerService.findById(id));
        //THEN
        assertThat(thrown)
                .isExactlyInstanceOf(BeerNotFoundException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    void shouldThrowBeerToDeleteNotFoundException() {
        //GIVEN
        id = 1L;
        String expectedExceptionMessage = "Beer not found, id: " + id;
        //WHEN
        Throwable thrown = catchThrowable(() -> beerService.deleteById(id));
        //THEN
        assertThat(thrown)
                .isExactlyInstanceOf(BeerNotFoundException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    void shouldFindBeerPage(){
        //GIVEN
        beerService.add(beer);
        //WHEN
        final var result = beerService.findBeerPage(0, 10);
        final var firstElement = result.iterator().next();
        //THEN
        assertThat(result)
                .hasSize(1)
                .hasExactlyElementsOfTypes(Beer.class);
        assertThat(firstElement)
                .usingRecursiveComparison()
                .comparingOnlyFields("name")
                .isEqualTo(beer);
    }

}
