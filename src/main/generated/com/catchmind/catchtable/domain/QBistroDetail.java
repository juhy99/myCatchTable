package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBistroDetail is a Querydsl query type for BistroDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBistroDetail extends EntityPathBase<BistroDetail> {

    private static final long serialVersionUID = -1094141773L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBistroDetail bistroDetail = new QBistroDetail("bistroDetail");

    public final StringPath bdAddr = createString("bdAddr");

    public final StringPath bdCaution = createString("bdCaution");

    public final StringPath bdHoliday = createString("bdHoliday");

    public final StringPath bdHomepage = createString("bdHomepage");

    public final StringPath bdHour = createString("bdHour");

    public final StringPath bdHp = createString("bdHp");

    public final NumberPath<Long> bdIdx = createNumber("bdIdx", Long.class);

    public final StringPath bdIntro = createString("bdIntro");

    public final StringPath bdNotice = createString("bdNotice");

    public final StringPath bdPark = createString("bdPark");

    public final QBistroInfo bistroInfo;

    public final QResAdmin resAdmin;

    public QBistroDetail(String variable) {
        this(BistroDetail.class, forVariable(variable), INITS);
    }

    public QBistroDetail(Path<? extends BistroDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBistroDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBistroDetail(PathMetadata metadata, PathInits inits) {
        this(BistroDetail.class, metadata, inits);
    }

    public QBistroDetail(Class<? extends BistroDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bistroInfo = inits.isInitialized("bistroInfo") ? new QBistroInfo(forProperty("bistroInfo"), inits.get("bistroInfo")) : null;
        this.resAdmin = inits.isInitialized("resAdmin") ? new QResAdmin(forProperty("resAdmin")) : null;
    }

}

