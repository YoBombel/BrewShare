package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService implements CRUDService<Beer, Long> {

    private final BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public Long add(Beer entity) {
        return beerRepository.save(entity)
                .getId();
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Beer findById(Long id) {
        return beerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {beerRepository.deleteById(id);}

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAllBeers() {
        beerRepository.deleteAll();
    }


}