package com.yugwa.backend.domain.vote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoteReply is a Querydsl query type for VoteReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoteReply extends EntityPathBase<VoteReply> {

    private static final long serialVersionUID = 1986802607L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoteReply voteReply = new QVoteReply("voteReply");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.yugwa.backend.domain.member.entity.QMember member;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath text = createString("text");

    public final QVote vote;

    public QVoteReply(String variable) {
        this(VoteReply.class, forVariable(variable), INITS);
    }

    public QVoteReply(Path<? extends VoteReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoteReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoteReply(PathMetadata metadata, PathInits inits) {
        this(VoteReply.class, metadata, inits);
    }

    public QVoteReply(Class<? extends VoteReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.yugwa.backend.domain.member.entity.QMember(forProperty("member")) : null;
        this.vote = inits.isInitialized("vote") ? new QVote(forProperty("vote"), inits.get("vote")) : null;
    }

}

