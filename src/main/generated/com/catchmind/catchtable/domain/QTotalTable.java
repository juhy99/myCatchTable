package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTotalTable is a Querydsl query type for TotalTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTotalTable extends EntityPathBase<TotalTable> {

    private static final long serialVersionUID = -1560200953L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTotalTable totalTable = new QTotalTable("totalTable");

    public final QResAdmin resAdmin;

    public final NumberPath<Integer> totCapacity = createNumber("totCapacity", Integer.class);

    public final NumberPath<Integer> totTable = createNumber("totTable", Integer.class);

    public final NumberPath<Long> totTableId = createNumber("totTableId", Long.class);

    public QTotalTable(String variable) {
        this(TotalTable.class, forVariable(variable), INITS);
    }

    public QTotalTable(Path<? extends TotalTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTotalTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTotalTable(PathMetadata metadata, PathInits inits) {
        this(TotalTable.class, metadata, inits);
    }

    public QTotalTable(Class<? extends TotalTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resAdmin = inits.isInitialized("resAdmin") ? new QResAdmin(forProperty("resAdmin")) : null;
    }

}

