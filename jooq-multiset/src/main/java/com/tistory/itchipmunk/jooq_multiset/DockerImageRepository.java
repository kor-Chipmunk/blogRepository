package com.tistory.itchipmunk.jooq_multiset;

import static com.tistory.itchipmunk.jooq_multiset.Tables.DOCKER_IMAGES;
import static com.tistory.itchipmunk.jooq_multiset.Tables.DOCKER_IMAGE_TAGS;
import static org.jooq.impl.DSL.multiset;

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
                                dsl.select(DOCKER_IMAGE_TAGS.asterisk())
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

}
