package com.tistory.itchipmunk.jooq_multiset;

import static com.tistory.itchipmunk.jooq_multiset.Tables.DOCKER_IMAGES;
import static com.tistory.itchipmunk.jooq_multiset.Tables.DOCKER_IMAGE_TAGS;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

import com.tistory.itchipmunk.jooq_multiset.DockerImageModel.DockerImageTagModel;
import com.tistory.itchipmunk.jooq_multiset.tables.records.DockerImageTagsRecord;
import com.tistory.itchipmunk.jooq_multiset.tables.records.DockerImagesRecord;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DockerImageRepository {

    private final DSLContext dsl;

    // 예시 레코드 생성
    public void insert() {
        List<DockerImagesRecord> records = new ArrayList<>(1_000_000);
        List<DockerImageTagsRecord> tagRecords = new ArrayList<>(10_000_000);
        for (int i = 2; i < 1_000_000; i++) {
            records.add(new DockerImagesRecord((long) i, "name" + i, "description" + i));

            for (int j = 0; j < 10; j++) {
                tagRecords.add(new DockerImageTagsRecord( null, (long) i, "name" + j ));
            }
        }
        dsl.batchInsert(records).execute();
        dsl.batchInsert(tagRecords).execute();
    }

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
                .offset((page - 1) * pageSize)
                .limit(pageSize)
                .fetchInto(DockerImageModel.class);

        return fetched;
    }

}
