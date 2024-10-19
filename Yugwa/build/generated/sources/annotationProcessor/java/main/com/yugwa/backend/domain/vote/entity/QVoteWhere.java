package com.yugwa.backend.domain.vote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVoteWhere is a Querydsl query type for VoteWhere
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoteWhere extends EntityPathBase<VoteWhere> {

    private static final long serialVersionUID = 1991499180L;

    public static final QVoteWhere voteWhere = new QVoteWhere("voteWhere");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> voteElementId = createNumber("voteElementId", Long.class);

    public final NumberPath<Long> voteId = createNumber("voteId", Long.class);

    public QVoteWhere(String variable) {
        super(VoteWhere.class, forVariable(variable));
    }

    public QVoteWhere(Path<? extends VoteWhere> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVoteWhere(PathMetadata metadata) {
        super(VoteWhere.class, metadata);
    }

}

