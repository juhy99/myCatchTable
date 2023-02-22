package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPending is a Querydsl query type for Pending
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPending extends EntityPathBase<Pending> {

    private static final long serialVersionUID = -1964431206L;

    public static final QPending pending = new QPending("pending");

    public final StringPath penBisName = createString("penBisName");

    public final NumberPath<Long> penBook = createNumber("penBook", Long.class);

    public final StringPath penFoodtype = createString("penFoodtype");

    public final StringPath penHp = createString("penHp");

    public final NumberPath<Long> penIdx = createNumber("penIdx", Long.class);

    public final StringPath penName = createString("penName");

    public final StringPath penRegion = createString("penRegion");

    public QPending(String variable) {
        super(Pending.class, forVariable(variable));
    }

    public QPending(Path<? extends Pending> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPending(PathMetadata metadata) {
        super(Pending.class, metadata);
    }

}

