package org.choongang.global.rests.gov.restaurant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestImage is a Querydsl query type for RestImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestImage extends EntityPathBase<RestImage> {

    private static final long serialVersionUID = 1449713012L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestImage restImage = new QRestImage("restImage");

    public final QRestaurant restaurant;

    public final StringPath rstrImgUrl = createString("rstrImgUrl");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QRestImage(String variable) {
        this(RestImage.class, forVariable(variable), INITS);
    }

    public QRestImage(Path<? extends RestImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestImage(PathMetadata metadata, PathInits inits) {
        this(RestImage.class, metadata, inits);
    }

    public QRestImage(Class<? extends RestImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant")) : null;
    }

}

