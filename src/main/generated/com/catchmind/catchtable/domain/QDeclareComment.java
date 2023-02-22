package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeclareComment is a Querydsl query type for DeclareComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeclareComment extends EntityPathBase<DeclareComment> {

    private static final long serialVersionUID = -261145294L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeclareComment declareComment = new QDeclareComment("declareComment");

    public final QAuditingField _super = new QAuditingField(this);

    public final QComment comment;

    public final StringPath decContent = createString("decContent");

    public final NumberPath<Long> decIdx = createNumber("decIdx", Long.class);

    public final StringPath decNick = createString("decNick");

    public final StringPath decTitle = createString("decTitle");

    public final QProfile profile;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QReview review;

    public QDeclareComment(String variable) {
        this(DeclareComment.class, forVariable(variable), INITS);
    }

    public QDeclareComment(Path<? extends DeclareComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeclareComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeclareComment(PathMetadata metadata, PathInits inits) {
        this(DeclareComment.class, metadata, inits);
    }

    public QDeclareComment(Class<? extends DeclareComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

