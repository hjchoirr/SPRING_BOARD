package org.choongang.global.rests.gov.restaurant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -1152381775L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFood food = new QFood("food");

    public final NumberPath<Long> menuId = createNumber("menuId", Long.class);

    public final StringPath menuNm = createString("menuNm");

    public final NumberPath<Integer> menuPrice = createNumber("menuPrice", Integer.class);

    public final QRestaurant restaurant;

    public final StringPath spcltMenuNm = createString("spcltMenuNm");

    public final StringPath spcltMenuOgnUrl = createString("spcltMenuOgnUrl");

    public final BooleanPath spcltMenuYn = createBoolean("spcltMenuYn");

    public QFood(String variable) {
        this(Food.class, forVariable(variable), INITS);
    }

    public QFood(Path<? extends Food> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFood(PathMetadata metadata, PathInits inits) {
        this(Food.class, metadata, inits);
    }

    public QFood(Class<? extends Food> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant")) : null;
    }

}

