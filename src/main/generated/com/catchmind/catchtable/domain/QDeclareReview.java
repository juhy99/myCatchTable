package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeclareReview is a Querydsl query type for DeclareReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeclareReview extends EntityPathBase<DeclareReview> {

    private static final long serialVersionUID = -142147035L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeclareReview declareReview = new QDeclareReview("declareReview");

    public final QAuditingField _super = new QAuditingField(this);

    public final StringPath derContent = createString("derContent");

    public final NumberPath<Long> derIdx = createNumber("derIdx", Long.class);

    public final StringPath derNick = createString("derNick");

    public final StringPath derTitle = createString("derTitle");

    public final QProfile profile;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QReview review;

    public QDeclareReview(String variable) {
        this(DeclareReview.class, forVariable(variable), INITS);
    }

    public QDeclareReview(Path<? extends DeclareReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeclareReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeclareReview(PathMetadata metadata, PathInits inits) {
        this(DeclareReview.class, metadata, inits);
    }

    public QDeclareReview(Class<? extends DeclareReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

