package org.choongang.restraurent.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.global.ListData;
import org.choongang.global.Pagination;
import org.choongang.global.rests.gov.restaurant.QRestaurant;
import org.choongang.global.rests.gov.restaurant.Restaurant;
import org.choongang.restraurent.controllers.RestaurantSearch;
import org.choongang.restraurent.exceptions.RestaurantNotFoundException;
import org.choongang.restraurent.repository.RestaurentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional //EntityManager 사용시 필수
public class RestaurantInfoService {
    private final RestaurentRepository repository;
    private final JPAQueryFactory queryFactory;
    private final HttpServletRequest request;

    public ListData<Restaurant> getList(RestaurantSearch search) {

        int page = Math.max(1, search.getPage());

        int limit = search.getLimit();   // 1페이지당 갯수
        limit = limit < 1 ? 20 : limit;

        int offset = (page - 1) * limit; // 레코드 시작위치

        String sopt = search.getSopt();  //ALL 통합검색
        String skey = search.getSkey(); // 검색 키워드
        QRestaurant restaurant = QRestaurant.restaurant;
        BooleanBuilder andBuilder = new BooleanBuilder();

        List<Restaurant> items = queryFactory.selectFrom(restaurant)
            .leftJoin(restaurant.rstrImg)
            .fetchJoin()
            .where(andBuilder)
            .offset(offset)
            .limit(limit)
            .orderBy(restaurant.createdAt.desc())
            .fetch();

        long total = repository.count(andBuilder); //조회된 전체갯수

        Pagination pagination = new Pagination(page, (int)total, 10, limit, request);

        return new ListData<>(items, pagination);
    }

    public Restaurant get(Long rstrId) {
        Restaurant item = repository.findById(rstrId).orElseThrow(RestaurantNotFoundException::new);

        return item;
    }
}
