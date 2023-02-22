package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopResTable is a Querydsl query type for ShopResTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopResTable extends EntityPathBase<ShopResTable> {

    private static final long serialVersionUID = -862967039L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopResTable shopResTable = new QShopResTable("shopResTable");

    public final StringPath shopResDay = createString("shopResDay");

    public final NumberPath<Long> shopResId = createNumber("shopResId", Long.class);

    public final StringPath shopResMonth = createString("shopResMonth");

    public final BooleanPath shopResStatus = createBoolean("shopResStatus");

    public final StringPath shopResTime = createString("shopResTime");

    public final QTotalTable totalTable;

    public QShopResTable(String variable) {
        this(ShopResTable.class, forVariable(variable), INITS);
    }

    public QShopResTable(Path<? extends ShopResTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopResTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopResTable(PathMetadata metadata, PathInits inits) {
        this(ShopResTable.class, metadata, inits);
    }

    public QShopResTable(Class<? extends ShopResTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.totalTable = inits.isInitialized("totalTable") ? new QTotalTable(forProperty("totalTable"), inits.get("totalTable")) : null;
    }

}

