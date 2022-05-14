-- 게시판 댓글 스키마 -> 게시판 스키마가 있어야만 한다.
-- 객체 제거
DROP TABLE board_reply CASCADE CONSTRAINTS;
DROP SEQUENCE board_rep_seq;

--객체 생성
CREATE TABLE board_reply(
  rno NUMBER PRIMARY KEY,
  no NUMBER REFERENCES board(no),
  content VARCHAR2(500) NOT NULL,
  writer VARCHAR2(30) NOT NULL,
  writeDate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE board_rep_seq;

-- 샘플데이터
-- 원본글 -> 게시판(board)의 글번호 : 1
INSERT INTO board_reply(rno, no, content, writer)
VALUES (board_rep_seq.nextval, 1,'컴퓨터 프로그램', '이영환');
COMMIT;
SELECT * FROM board WHERE no = 1;
SELECT * FROM board_reply WHERE no = 1 ORDER BY rno;