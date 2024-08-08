package org.choongang.global.rests.gov.restaurant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = -1608548016L;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final StringPath bsnsLcncNm = createString("bsnsLcncNm");

    public final StringPath bsnsStatmBzcndNm = createString("bsnsStatmBzcndNm");

    public final ListPath<Food, QFood> food = this.<Food, QFood>createList("food", Food.class, QFood.class, PathInits.DIRECT2);

    public final StringPath rstrAreaClsfNm = createString("rstrAreaClsfNm");

    public final NumberPath<Long> rstrId = createNumber("rstrId", Long.class);

    public final ListPath<RestImage, QRestImage> rstrImg = this.<RestImage, QRestImage>createList("rstrImg", RestImage.class, QRestImage.class, PathInits.DIRECT2);

    public final StringPath rstrIntrcnCont = createString("rstrIntrcnCont");

    public final NumberPath<Double> rstrLa = createNumber("rstrLa", Double.class);

    public final StringPath rstrLnnoAdr = createString("rstrLnnoAdr");

    public final NumberPath<Double> rstrLo = createNumber("rstrLo", Double.class);

    public final StringPath rstrNm = createString("rstrNm");

    public final StringPath rstrRdnmadr = createString("rstrRdnmadr");

    public final StringPath rstrTelno = createString("rstrTelno");

    public QRestaurant(String variable) {
        super(Restaurant.class, forVariable(variable));
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurant(PathMetadata metadata) {
        super(Restaurant.class, metadata);
    }

}

