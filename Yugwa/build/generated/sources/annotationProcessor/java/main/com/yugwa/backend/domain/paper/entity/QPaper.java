package com.yugwa.backend.domain.paper.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaper is a Querydsl query type for Paper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaper extends EntityPathBase<Paper> {

    private static final long serialVersionUID = -1751984883L;

    public static final QPaper paper = new QPaper("paper");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath jrnlNmKo = createString("jrnlNmKo");

    public final StringPath summary = createString("summary");

    public final StringPath title = createString("title");

    public final StringPath year = createString("year");

    public QPaper(String variable) {
        super(Paper.class, forVariable(variable));
    }

    public QPaper(Path<? extends Paper> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaper(PathMetadata metadata) {
        super(Paper.class, metadata);
    }

}

