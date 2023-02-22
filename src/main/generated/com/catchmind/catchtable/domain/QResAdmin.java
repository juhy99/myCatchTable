package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResAdmin is a Querydsl query type for ResAdmin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResAdmin extends EntityPathBase<ResAdmin> {

    private static final long serialVersionUID = -1466499092L;

    public static final QResAdmin resAdmin = new QResAdmin("resAdmin");

    public final QAuditingField _super = new QAuditingField(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath resaBisName = createString("resaBisName");

    public final StringPath resaHp = createString("resaHp");

    public final StringPath resaName = createString("resaName");

    public final StringPath resaRegion = createString("resaRegion");

    public final StringPath resaUserid = createString("resaUserid");

    public final StringPath resaUserpw = createString("resaUserpw");

    public QResAdmin(String variable) {
        super(ResAdmin.class, forVariable(variable));
    }

    public QResAdmin(Path<? extends ResAdmin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResAdmin(PathMetadata metadata) {
        super(ResAdmin.class, metadata);
    }

}

