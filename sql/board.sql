USE jsp;

CREATE TABLE Board
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(200)  NOT NULL,
    content  VARCHAR(5000) NOT NULL,
    writer   VARCHAR(200)  NOT NULL,
    inserted DATETIME      NOT NULL DEFAULT NOW()
);

SELECT *
FROM Board;

# 페이징 연습용 게시물 복붙
INSERT INTO Board
    (title, content, writer)
SELECT title, content, writer
FROM Board;

SELECT COUNT(*)
FROM Board;