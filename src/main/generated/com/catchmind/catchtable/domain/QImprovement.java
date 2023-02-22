package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImprovement is a Querydsl query type for Improvement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImprovement extends EntityPathBase<Improvement> {

    private static final long serialVersionUID = -594128295L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImprovement improvement = new QImprovement("improvement");

    public final QAuditingFields _super = new QAuditingFields(this);

    public final StringPath impAnswer = createString("impAnswer");

    public final StringPath impContent = createString("impContent");

    public final NumberPath<Long> impIdx = createNumber("impIdx", Long.class);

    public final BooleanPath impStatus = createBoolean("impStatus");

    public final StringPath impTitle = createString("impTitle");

    public final QProfile profile;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QImprovement(String variable) {
        this(Improvement.class, forVariable(variable), INITS);
    }

    public QImprovement(Path<? extends Improvement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImprovement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImprovement(PathMetadata metadata, PathInits inits) {
        this(Improvement.class, metadata, inits);
    }

    public QImprovement(Class<? extends Improvement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
    }

}

