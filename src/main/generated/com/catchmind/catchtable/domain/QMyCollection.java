package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMyCollection is a Querydsl query type for MyCollection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyCollection extends EntityPathBase<MyCollection> {

    private static final long serialVersionUID = -657146809L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyCollection myCollection = new QMyCollection("myCollection");

    public final QAuditingFields _super = new QAuditingFields(this);

    public final StringPath bisNames = createString("bisNames");

    public final StringPath colContent = createString("colContent");

    public final NumberPath<Long> colIdx = createNumber("colIdx", Long.class);

    public final BooleanPath colLock = createBoolean("colLock");

    public final StringPath colTitle = createString("colTitle");

    public final QProfile profile;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QMyCollection(String variable) {
        this(MyCollection.class, forVariable(variable), INITS);
    }

    public QMyCollection(Path<? extends MyCollection> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMyCollection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMyCollection(PathMetadata metadata, PathInits inits) {
        this(MyCollection.class, metadata, inits);
    }

    public QMyCollection(Class<? extends MyCollection> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
    }

}

