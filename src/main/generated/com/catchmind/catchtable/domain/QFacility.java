package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacility is a Querydsl query type for Facility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacility extends EntityPathBase<Facility> {

    private static final long serialVersionUID = -581324800L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacility facility = new QFacility("facility");

    public final QAuditingField _super = new QAuditingField(this);

    public final QBistroInfo bistroInfo;

    public final BooleanPath facAnimal = createBoolean("facAnimal");

    public final BooleanPath facCorkage = createBoolean("facCorkage");

    public final BooleanPath facHandi = createBoolean("facHandi");

    public final NumberPath<Long> facIdx = createNumber("facIdx", Long.class);

    public final BooleanPath facNokid = createBoolean("facNokid");

    public final BooleanPath facParking = createBoolean("facParking");

    public final BooleanPath facValet = createBoolean("facValet");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QResAdmin resAdmin;

    public QFacility(String variable) {
        this(Facility.class, forVariable(variable), INITS);
    }

    public QFacility(Path<? extends Facility> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacility(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacility(PathMetadata metadata, PathInits inits) {
        this(Facility.class, metadata, inits);
    }

    public QFacility(Class<? extends Facility> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bistroInfo = inits.isInitialized("bistroInfo") ? new QBistroInfo(forProperty("bistroInfo"), inits.get("bistroInfo")) : null;
        this.resAdmin = inits.isInitialized("resAdmin") ? new QResAdmin(forProperty("resAdmin")) : null;
    }

}

