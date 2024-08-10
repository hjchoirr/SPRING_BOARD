package org.choongang.restraurent.repository;

import org.choongang.global.rests.gov.restaurant.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FoodRepository extends JpaRepository<Food, Long>, QuerydslPredicateExecutor<Food> {
}
