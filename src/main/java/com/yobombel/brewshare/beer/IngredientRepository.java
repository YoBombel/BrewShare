package com.yobombel.brewshare.beer;

import com.yobombel.brewshare.beer.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
