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

# 게시물의 writer 값을 member에 있는 값으로 update
UPDATE Board
SET writer = (SELECT id FROM member LIMIT 1)
WHERE id > 0;

# board.writer -> member.id 참조 (외래키)추가
# 탈퇴한 회원의 글을 게시물에서 볼 수 없도록 (삭제)
ALTER TABLE Board
    ADD FOREIGN KEY (writer) REFERENCES member (id);