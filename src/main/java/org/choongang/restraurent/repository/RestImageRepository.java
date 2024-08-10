package org.choongang.restraurent.repository;

import org.choongang.global.rests.gov.restaurant.RestImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RestImageRepository extends JpaRepository<RestImage, Long>, QuerydslPredicateExecutor<RestImage> {
}
