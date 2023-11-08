package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.util.TempBeerList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    private final TempBeerList tempBeerList;

    public BeerService(TempBeerList tempBeerList) {
        this.tempBeerList = tempBeerList;
    }

    public List<Beer> findAll(){
        return tempBeerList.getTemporaryBeerList();
    }

}
