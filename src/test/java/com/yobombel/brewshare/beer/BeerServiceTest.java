package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void init() {
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
        verify(beerRepository).save(beer);
    }

    @Test
    void shouldFindAll(){
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
        verify(beerRepository).delete(beer);
    }

    @Test
    void shouldUpdate() {
        //GIVEN
        given(beerRepository.save(beer)).willReturn(beer);

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
    void shouldDeleteAll(){
        //GIVEN
        //WHEN
        beerService.deleteAll();
        //THEN
        verify(beerRepository).deleteAll();
    }

}