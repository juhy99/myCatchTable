package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBistroInfo is a Querydsl query type for BistroInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBistroInfo extends EntityPathBase<BistroInfo> {

    private static final long serialVersionUID = -1927236208L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBistroInfo bistroInfo = new QBistroInfo("bistroInfo");

    public final QAuditingField _super = new QAuditingField(this);

    public final StringPath bisCategory = createString("bisCategory");

    public final StringPath bisDesc = createString("bisDesc");

    public final StringPath bisDinner = createString("bisDinner");

    public final NumberPath<Long> bisIdx = createNumber("bisIdx", Long.class);

    public final StringPath bisLunch = createString("bisLunch");

    public final StringPath bisRegion = createString("bisRegion");

    public final QPhoto photo;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QResAdmin resAdmin;

    public QBistroInfo(String variable) {
        this(BistroInfo.class, forVariable(variable), INITS);
    }

    public QBistroInfo(Path<? extends BistroInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBistroInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBistroInfo(PathMetadata metadata, PathInits inits) {
        this(BistroInfo.class, metadata, inits);
    }

    public QBistroInfo(Class<? extends BistroInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.photo = inits.isInitialized("photo") ? new QPhoto(forProperty("photo"), inits.get("photo")) : null;
        this.resAdmin = inits.isInitialized("resAdmin") ? new QResAdmin(forProperty("resAdmin")) : null;
    }

}

