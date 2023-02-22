package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBisNotice is a Querydsl query type for BisNotice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBisNotice extends EntityPathBase<BisNotice> {

    private static final long serialVersionUID = -598478521L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBisNotice bisNotice = new QBisNotice("bisNotice");

    public final QAuditingFields _super = new QAuditingFields(this);

    public final StringPath binContent = createString("binContent");

    public final NumberPath<Long> binIdx = createNumber("binIdx", Long.class);

    public final StringPath binTitle = createString("binTitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QResAdmin resAdmin;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QBisNotice(String variable) {
        this(BisNotice.class, forVariable(variable), INITS);
    }

    public QBisNotice(Path<? extends BisNotice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBisNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBisNotice(PathMetadata metadata, PathInits inits) {
        this(BisNotice.class, metadata, inits);
    }

    public QBisNotice(Class<? extends BisNotice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resAdmin = inits.isInitialized("resAdmin") ? new QResAdmin(forProperty("resAdmin")) : null;
    }

}

