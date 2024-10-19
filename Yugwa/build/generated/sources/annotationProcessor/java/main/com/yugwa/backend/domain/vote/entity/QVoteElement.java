package com.yugwa.backend.domain.vote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoteElement is a Querydsl query type for VoteElement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoteElement extends EntityPathBase<VoteElement> {

    private static final long serialVersionUID = -405527935L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoteElement voteElement = new QVoteElement("voteElement");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final QVote vote;

    public QVoteElement(String variable) {
        this(VoteElement.class, forVariable(variable), INITS);
    }

    public QVoteElement(Path<? extends VoteElement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoteElement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoteElement(PathMetadata metadata, PathInits inits) {
        this(VoteElement.class, metadata, inits);
    }

    public QVoteElement(Class<? extends VoteElement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vote = inits.isInitialized("vote") ? new QVote(forProperty("vote"), inits.get("vote")) : null;
    }

}

