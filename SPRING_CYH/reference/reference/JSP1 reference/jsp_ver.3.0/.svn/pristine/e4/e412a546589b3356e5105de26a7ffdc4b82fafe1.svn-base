-- 스키마(Schema):데이터의 구조 - CREATE TABLE
-- 테이블 객체 만들기(CREATE)와 지우기 : DDL(Data Definition Language)
-- 게시판 데이터 저장 : 글번호, 제목, 내용, 작성자, 작성일, 조회수
-- 컬럼 : 저장할 항목. 반드시 데이터의 타입과 크기가 지정되어야만 한다. - 정형데이터 SQL
-- PK - 중복 불가, 비워둘수 없다.
CREATE TABLE board(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(300),
  content VARCHAR2(2000),
  writer VARCHAR2(30),
  writeDate DATE,
  hit NUMBER
);

-- 테이블 객체 지우기 (CREATE <-> DROP)
DROP TABLE board CASCADE CONSTRAINTS;

-- 글번호는 1~~~ 1씩 증가되는 숫자를 사용하게 된다. 자동증가시키는 객체 -> 시퀀스 사용:오라클에서만 적용
CREATE SEQUENCE board_seq;
-- 시퀀스 객체 지우기
DROP SEQUENCE board_seq;

--[데이터 처리] -------------------------------------------
-- 데이터 처리 명령어 : DML(Data Manipulation Language) - 데이터 조작어
-- INSERT : 데이터 넣기, UPDATE : 데이터 수정, DELETE : 데이터 삭제
-- 시퀀스 사용 : 시퀀스.NEXTVAL - 시퀀스의 다음번호 받기
-- 현재 날짜와 시간 : SYSDATE, 문자열 : '문자열'
INSERT INTO board(no, title, content, writer, writeDate, hit)
VALUES(board_seq.NEXTVAL, 'java 개발자', '노력을 많이 해야한다.', '이영환', SYSDATE, 0);

-- 작업 완전 적용 - COMMIT / 작업 내용 취소 - ROLLBACK - 트렌젝션 : DTL
ROLLBACK;
COMMIT;

-- 데이터 확인 : SELECT - Query : SQL
SELECT * FROM board;

-- no가 3번인 글의 제목을 웹 개발자로 바꾸자.
UPDATE board SET title = '웹 개발자' WHERE no = 3;
COMMIT;

-- no가 3번인 글을 삭제해 보자.
DELETE FROM board WHERE no = 3;
COMMIT;