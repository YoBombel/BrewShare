package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @BeforeEach
    void setUp() {
        beerRepository.deleteAll();
        ingredientRepository.deleteAll();
        beer = new Beer();
        beer.setName("Test Beer");
        beer.setId(1L);
        id = beerService.add(beer);
    }

    @Test
    void shouldAddBeer() {
        //GIVEN
        //WHEN
        List<Beer> beerList = beerRepository.findAll();
        //THEN
        assertNotNull(id);
        assertThat(beerList).hasSize(1);
    }

    @Test
    void shouldDeleteById() {
        //GIVEN
        //WHEN
        beerService.deleteById(id);
        List<Beer> beerList = beerRepository.findAll();

        //THEN
        assertThat(beerList).isEmpty();
    }

    @Test
    void shouldUpdate() {
        //GIVEN
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
        String expectedExceptionMessage = "Beer not found, id: " + id;
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
        String expectedExceptionMessage = "Beer not found, id: " + id;
        beerRepository.deleteAll();
        //WHEN
        Throwable thrown = catchThrowable(() -> beerService.deleteById(id));
        //THEN
        assertThat(thrown)
                .isExactlyInstanceOf(BeerNotFoundException.class)
                .hasMessage(expectedExceptionMessage);
    }

}
