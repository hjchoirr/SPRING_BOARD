package org.choongang.restraurent.repository;

import org.choongang.global.rests.gov.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RestaurentRepository extends JpaRepository<Restaurant, Long>, QuerydslPredicateExecutor<Restaurant> {
}
