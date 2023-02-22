package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBistroSave is a Querydsl query type for BistroSave
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBistroSave extends EntityPathBase<BistroSave> {

    private static final long serialVersionUID = -1926950305L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBistroSave bistroSave = new QBistroSave("bistroSave");

    public final QBistroDetail bistroDetail;

    public final NumberPath<Long> colIdx = createNumber("colIdx", Long.class);

    public final QProfile profile;

    public final QResAdmin resAdmin;

    public final NumberPath<Long> saveIdx = createNumber("saveIdx", Long.class);

    public QBistroSave(String variable) {
        this(BistroSave.class, forVariable(variable), INITS);
    }

    public QBistroSave(Path<? extends BistroSave> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBistroSave(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBistroSave(PathMetadata metadata, PathInits inits) {
        this(BistroSave.class, metadata, inits);
    }

    public QBistroSave(Class<? extends BistroSave> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bistroDetail = inits.isInitialized("bistroDetail") ? new QBistroDetail(forProperty("bistroDetail"), inits.get("bistroDetail")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
        this.resAdmin = inits.isInitialized("resAdmin") ? new QResAdmin(forProperty("resAdmin")) : null;
    }

}

