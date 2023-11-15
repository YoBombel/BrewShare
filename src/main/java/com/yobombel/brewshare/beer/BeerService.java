package com.yobombel.brewshare.beer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    private final BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Beer findById(Long id) {
        return beerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void addBeer(Beer beer) {
        beerRepository.save(beer);
    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAllBeers() {
        beerRepository.deleteAll();
    }

}