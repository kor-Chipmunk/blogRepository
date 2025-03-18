package com.tistory.itchipmunk.jooq_multiset;

import java.util.List;

public class DockerImageModel {

    private Long id;
    private String name;
    private String description;

    private List<DockerImageTagModel> tags;

    static class DockerImageTagModel {
        private Long id;
        private String name;

        @Override
        public String toString() {
            return "DockerImageTagModel{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DockerImageModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }
}
