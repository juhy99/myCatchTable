package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentHeart is a Querydsl query type for CommentHeart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentHeart extends EntityPathBase<CommentHeart> {

    private static final long serialVersionUID = 1362264068L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentHeart commentHeart = new QCommentHeart("commentHeart");

    public final QComment comment;

    public final NumberPath<Long> heIdx = createNumber("heIdx", Long.class);

    public final QProfile profile;

    public final QReview review;

    public QCommentHeart(String variable) {
        this(CommentHeart.class, forVariable(variable), INITS);
    }

    public QCommentHeart(Path<? extends CommentHeart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentHeart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentHeart(PathMetadata metadata, PathInits inits) {
        this(CommentHeart.class, metadata, inits);
    }

    public QCommentHeart(Class<? extends CommentHeart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

