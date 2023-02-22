package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = 430999314L;

    public static final QAdmin admin = new QAdmin("admin");

    public final QAuditingField _super = new QAuditingField(this);

    public final StringPath adEmail = createString("adEmail");

    public final StringPath adHp = createString("adHp");

    public final NumberPath<Long> adIdx = createNumber("adIdx", Long.class);

    public final StringPath adName = createString("adName");

    public final StringPath adUserid = createString("adUserid");

    public final StringPath adUserpw = createString("adUserpw");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata metadata) {
        super(Admin.class, metadata);
    }

}

