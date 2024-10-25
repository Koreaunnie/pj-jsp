USE jsp;

CREATE TABLE member
(
    id          VARCHAR(50) PRIMARY KEY,
    password    VARCHAR(100) NOT NULL,
    nick_name   VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(2000),
    inserted    DATE         NOT NULL DEFAULT NOW()
);

SELECT *
FROM member;

# 권한 테이블
CREATE TABLE authorization
(
    id   VARCHAR(50) REFERENCES member (id),
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id, name)
);

INSERT INTO authorization
    (id, name)
VALUES ('admin', 'admin'),
       ('bdmin', 'admin');