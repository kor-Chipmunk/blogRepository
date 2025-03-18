CREATE TABLE IF NOT EXISTS docker_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL DEFAULT ""
);

CREATE TABLE IF NOT EXISTS docker_image_tags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    docker_image_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (docker_image_id) REFERENCES docker_images(id)
);

INSERT INTO docker_images
    (name, description)
VALUES
    ('mysql', 'MySQL is a widely used, open-source relational database management system (RDBMS).'),
    ('nginx', 'Official build of Nginx.');

INSERT INTO docker_image_tags
    (docker_image_id, name)
VALUES
    (1, 'latest'),
    (1, '9.2.0'),
    (1, '8.4.4'),
    (1, '8.0.41'),
    (2, 'stable-perl'),
    (2, 'stable-otel'),
    (2, '1.26.3-perl');
