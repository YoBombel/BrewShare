package com.yobombel.brewshare.beer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;
    @Captor
    private ArgumentCaptor<Beer> captor;
    @InjectMocks
    @Spy
    private BeerService beerService;

    private Long id;
    private Beer beer;

    @BeforeEach
    void init(){
        id = 1L;
        beer = new Beer();
        beer.setName("TestBeer");
    }

    @Test
    void shouldAdd() {
        //GIVEN
        given(beerRepository.save(captor.capture())).willReturn(beer);

        //WHEN
        beerService.add(beer);

        //THEN
        verify(beerRepository).save(any(Beer.class));
    }

    @Test
    void shouldCallRepositoryDeleteById(){
        //GIVEN
        //WHEN
        beerService.deleteById(id);

        //THEN
        verify(beerRepository).deleteById(anyLong());
    }

}