package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = -1591269204L;

    public static final QProfile profile = new QProfile("profile");

    public final StringPath prBirth = createString("prBirth");

    public final BooleanPath prBlock = createBoolean("prBlock");

    public final StringPath prGender = createString("prGender");

    public final StringPath prHp = createString("prHp");

    public final NumberPath<Long> prIdx = createNumber("prIdx", Long.class);

    public final StringPath prIntro = createString("prIntro");

    public final StringPath prMemo = createString("prMemo");

    public final StringPath prName = createString("prName");

    public final StringPath prNick = createString("prNick");

    public final NumberPath<Integer> prNoshow = createNumber("prNoshow", Integer.class);

    public final NumberPath<Integer> prPoint = createNumber("prPoint", Integer.class);

    public final StringPath prRegion = createString("prRegion");

    public final NumberPath<Integer> prReview = createNumber("prReview", Integer.class);

    public final StringPath prUserpw = createString("prUserpw");

    public final EnumPath<com.catchmind.catchtable.domain.type.MemberRole> role = createEnum("role", com.catchmind.catchtable.domain.type.MemberRole.class);

    public QProfile(String variable) {
        super(Profile.class, forVariable(variable));
    }

    public QProfile(Path<? extends Profile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfile(PathMetadata metadata) {
        super(Profile.class, metadata);
    }

}

