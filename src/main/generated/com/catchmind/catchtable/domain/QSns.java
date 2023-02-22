package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSns is a Querydsl query type for Sns
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSns extends EntityPathBase<Sns> {

    private static final long serialVersionUID = -1250929157L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSns sns = new QSns("sns");

    public final QProfile profile;

    public final StringPath snsAddr = createString("snsAddr");

    public final NumberPath<Long> snsIdx = createNumber("snsIdx", Long.class);

    public final StringPath snsType = createString("snsType");

    public QSns(String variable) {
        this(Sns.class, forVariable(variable), INITS);
    }

    public QSns(Path<? extends Sns> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSns(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSns(PathMetadata metadata, PathInits inits) {
        this(Sns.class, metadata, inits);
    }

    public QSns(Class<? extends Sns> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
    }

}

