--DB 개발 - 수정이 계속 된다. => 원래 것은 지우고 다시 만들어야 한다. 샘플데이터 넣기. COMMIT -> SELECT
-- 1. 게시판 객체 지운다.(맨 처음에는 board 테이블이 없으면 오류가 난다.)
DROP TABLE board CASCADE CONSTRAINTS;
DROP SEQUENCE board_seq;

-- 2. 게시판 관련 객체를 만든다.(테이블, 시퀀스) - 글번호, 제목, 내용, 작성자, 작성일, 조회수
--  2-1. 컬럼이름, 타입, 문자열인 경우 크기 필수
--  2-2. 글번호는 중복이 되면 안됩니다. 데이터가 없는 것(NULL)도 안됨. -> PRIMARY KEY(기본키)
--  2-3. 제목, 내용, 작성자는 사용자가 꼭 입력해서 넣아야만 한다. -> NULL 안됨. :  NOT NULL
--  2-4. 똑같은 데이터로 넣어지는 것 : 작성일 - 항상 현재 날짜와 시간 -> SYSDATE, 조회수 - 0 => DEFAULT
CREATE TABLE board(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  writer VARCHAR2(30) NOT NULL,
  writeDate DATE DEFAULT SYSDATE,
  hit NUMBER DEFAULT 0
);

CREATE SEQUENCE board_seq;

-- 3. 샘플 데이터 넣기
--  3-1. 글번호(자동 - 객체:시퀀스), 제목, 내용, 작성자
INSERT INTO board(no, title, content, writer)
VALUES(board_seq.NEXTVAL, '자바란?', '프로그램 언어', '이영환');

-- 4. 데이터 완전 적용
COMMIT;

-- 5. 데이터 확인
SELECT * FROM board;