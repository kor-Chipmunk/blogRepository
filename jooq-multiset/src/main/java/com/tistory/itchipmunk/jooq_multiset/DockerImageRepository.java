package com.tistory.itchipmunk.jooq_multiset;

import static com.tistory.itchipmunk.jooq_multiset.Tables.DOCKER_IMAGES;
import static com.tistory.itchipmunk.jooq_multiset.Tables.DOCKER_IMAGE_TAGS;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

import com.tistory.itchipmunk.jooq_multiset.DockerImageModel.DockerImageTagModel;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DockerImageRepository {

    private final DSLContext dsl;

    public List<DockerImageModel> getDockerImages() {
        var fetched = dsl.select(
                        DOCKER_IMAGES.asterisk(),

                        multiset(
                                select(DOCKER_IMAGE_TAGS.asterisk())
                                        .from(DOCKER_IMAGE_TAGS)
                                        .where(DOCKER_IMAGE_TAGS.DOCKER_IMAGE_ID.eq(DOCKER_IMAGES.ID))
                        )
                                .as("tags")
                                .convertFrom(records -> records.map(
                                        record -> record.into(DockerImageTagModel.class))
                                )
                )
                .from(DOCKER_IMAGES)
                .fetchInto(DockerImageModel.class);

        return fetched;
    }

    public List<DockerImageModel> getDockerImagesPagination(int page, int pageSize) {
        var baseTable = dsl.select(
                        DOCKER_IMAGES.asterisk()
                ).from(DOCKER_IMAGES)
                .orderBy(DOCKER_IMAGES.ID.asc())
                .limit(pageSize)
                .offset((page - 1) * pageSize)
                .asTable(DOCKER_IMAGES);

        var fetched = dsl.select(
                        baseTable.asterisk(),

                        multiset(
                                select(DOCKER_IMAGE_TAGS.asterisk())
                                        .from(DOCKER_IMAGE_TAGS)
                                        .where(DOCKER_IMAGE_TAGS.DOCKER_IMAGE_ID.eq(baseTable.field("id").cast(Long.class)))
                        )
                                .as("tags")
                                .convertFrom(records -> records.map(
                                        record -> record.into(DockerImageTagModel.class))
                                )
                )
                .from(baseTable)
                .fetchInto(DockerImageModel.class);

        return fetched;
    }

}
