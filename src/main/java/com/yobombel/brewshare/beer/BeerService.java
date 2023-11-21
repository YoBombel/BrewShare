package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.CRUDService;
import com.yobombel.brewshare.beer.exception.BeerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
    }

    public void deleteById(Long id) {
        beerRepository.findById(id)
                .ifPresentOrElse(beerRepository::delete, () -> {
                            throw new BeerNotFoundException(id);
                        });
    }

    //TODO - temporary method, delete after implementing better beer examples
    public void deleteAll() {
        beerRepository.deleteAll();
    }

    public void update(Long id, Beer editedBeer) {
        editedBeer.setId(id);
        beerRepository.save(editedBeer);
    }
}