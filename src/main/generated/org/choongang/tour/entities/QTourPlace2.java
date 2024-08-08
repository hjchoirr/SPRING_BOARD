package org.choongang.tour.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTourPlace2 is a Querydsl query type for TourPlace2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTourPlace2 extends EntityPathBase<TourPlace2> {

    private static final long serialVersionUID = -800866890L;

    public static final QTourPlace2 tourPlace2 = new QTourPlace2("tourPlace2");

    public final org.choongang.member.entities.QBaseEntity _super = new org.choongang.member.entities.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath description = createString("description");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath photoUrl = createString("photoUrl");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath tel = createString("tel");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> tourDays = createNumber("tourDays", Integer.class);

    public QTourPlace2(String variable) {
        super(TourPlace2.class, forVariable(variable));
    }

    public QTourPlace2(Path<? extends TourPlace2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTourPlace2(PathMetadata metadata) {
        super(TourPlace2.class, metadata);
    }

}

