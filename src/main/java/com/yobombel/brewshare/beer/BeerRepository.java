package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.stats.model.BeerStatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    @Query("SELECT b.batchSize FROM Beer b")
    List<Double> findBatchSizes();

    @Query("SELECT b.originalGravity FROM Beer b")
    List<Double> findOriginalGravities();

    List<BeerStatsDto> findAllByBatchSizeIsNotNull();

}