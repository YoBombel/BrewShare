package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.ingredient.IngredientRepository;
import com.yobombel.brewshare.beer.ingredient.IngredientService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerIntegrationTest {

    @LocalServerPort
    private Integer port;

    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.2.0");

    @BeforeAll
    static void beforeAll(){
        mysql.start();
    }

    @AfterAll
    static void afterAll(){
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
    void setUp(){
        beerRepository.deleteAll();
        ingredientRepository.deleteAll();
        beer = new Beer();
        beer.setName("Test Beer");
        beer.setId(1L);
    }

    @Test
    void shouldAddBeer(){
        //GIVEN
        //WHEN
        Long id = beerService.add(beer);
        List<Beer> beerList = beerRepository.findAll();

        //THEN
        assertNotNull(id);
        assertThat(beerList).hasSize(1);
    }

    @Test
    void shouldDeleteById(){
        //GIVEN
        id = beerService.add(beer);

        //WHEN
        beerService.deleteById(id);
        List<Beer> beerList = beerRepository.findAll();

        //THEN
        assertThat(beerList).isEmpty();


    }

}
