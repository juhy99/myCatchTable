package com.catchmind.catchtable.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTalkAdmin is a Querydsl query type for TalkAdmin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTalkAdmin extends EntityPathBase<TalkAdmin> {

    private static final long serialVersionUID = -960768954L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTalkAdmin talkAdmin = new QTalkAdmin("talkAdmin");

    public final QAuditingField _super = new QAuditingField(this);

    public final QProfile profile;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath taaContent = createString("taaContent");

    public final NumberPath<Long> taaIdx = createNumber("taaIdx", Long.class);

    public QTalkAdmin(String variable) {
        this(TalkAdmin.class, forVariable(variable), INITS);
    }

    public QTalkAdmin(Path<? extends TalkAdmin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTalkAdmin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTalkAdmin(PathMetadata metadata, PathInits inits) {
        this(TalkAdmin.class, metadata, inits);
    }

    public QTalkAdmin(Class<? extends TalkAdmin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
    }

}

