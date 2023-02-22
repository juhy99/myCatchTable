package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReserve is a Querydsl query type for Reserve
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserve extends EntityPathBase<Reserve> {

    private static final long serialVersionUID = -184767553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReserve reserve = new QReserve("reserve");

    public final QAuditingFields _super = new QAuditingFields(this);

    public final QBistroDetail bistroDetail;

    public final QBistroInfo bistroInfo;

    public final StringPath prName = createString("prName");

    public final QProfile profile;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QResAdmin resAdmin;

    public final StringPath resDay = createString("resDay");

    public final StringPath resHp = createString("resHp");

    public final NumberPath<Long> resIdx = createNumber("resIdx", Long.class);

    public final StringPath resMonth = createString("resMonth");

    public final NumberPath<Long> resPerson = createNumber("resPerson", Long.class);

    public final StringPath resReason = createString("resReason");

    public final StringPath resRequest = createString("resRequest");

    public final EnumPath<com.catchmind.catchtable.domain.type.ReservationType> resStatus = createEnum("resStatus", com.catchmind.catchtable.domain.type.ReservationType.class);

    public final StringPath resTime = createString("resTime");

    public final BooleanPath revStatus = createBoolean("revStatus");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final StringPath visitHp = createString("visitHp");

    public final StringPath visitName = createString("visitName");

    public QReserve(String variable) {
        this(Reserve.class, forVariable(variable), INITS);
    }

    public QReserve(Path<? extends Reserve> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReserve(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReserve(PathMetadata metadata, PathInits inits) {
        this(Reserve.class, metadata, inits);
    }

    public QReserve(Class<? extends Reserve> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bistroDetail = inits.isInitialized("bistroDetail") ? new QBistroDetail(forProperty("bistroDetail"), inits.get("bistroDetail")) : null;
        this.bistroInfo = inits.isInitialized("bistroInfo") ? new QBistroInfo(forProperty("bistroInfo"), inits.get("bistroInfo")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
        this.resAdmin = inits.isInitialized("resAdmin") ? new QResAdmin(forProperty("resAdmin")) : null;
    }

}

