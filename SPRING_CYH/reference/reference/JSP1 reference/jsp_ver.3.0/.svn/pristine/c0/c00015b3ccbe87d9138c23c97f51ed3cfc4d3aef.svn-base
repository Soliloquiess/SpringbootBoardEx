--[게시판 운영 쿼리]---------------------------------
-- 1. 게시판 리스트 - SELECT : 여러개 - 보여 줄 데이터 : 번호, 제목, 작성자, 작성일, 조회수
--    최근 작성한 글이 위로 와야 합니다. -> no가 크면 최근 글이다. 큰->작(DESC)
-- SELECT * FROM board; -- * : 여러개를 대표하는 와일드 카드 문자.(모든 컬럼)
--    - 예제 데이터 여러개 만들기
-- INSERT INTO board(no, title, content, writer) 
-- (SELECT board_seq.NEXTVAL, title, content, writer FROM board);
-- COMMIT;
SELECT no, title, writer, writeDate, hit
FROM board
ORDER BY no DESC;

-- 2. 게시판 글보기 - SELECT : 한개 - 글번호(PK) 2 - 아래 존재하는 글번호
--    보여줄 데이터 - 글번호, 제목, 내용, 작성자, 작성일, 조회수
--    비교의 WHERE. = : 오른쪽의 데이터를 왼쪽에 저장해 놔라. WHERE 안에서 사용되는 = 비교의 같다.
--    조회수 1 증가 시키는 처리를 먼저 해준다.
UPDATE board SET hit = hit + 1
WHERE no = 2;
COMMIT;
SELECT no, title, content, writer, writeDate, hit
FROM board
WHERE no = 2;

--- SELECT 문장은 COMMIT을 필요로 하지 않는다. - 데이터를 변경하지 않는다.

-- 3. 게시판 글등록 - INSERT
--    - 사용자에게 제목, 내용, 작성자를 입력 받는다. PK에 해당되는 no는 시퀀스인 board_seq.NEXTVAL를 사용한다.
INSERT INTO board(no, title, content, writer)
VALUES (board_seq.NEXTVAL, '오라클', '데이터베이스', '관리자');
COMMIT;

-- 4. 게시판 글수정 - UPDATE
--    - 사용자가 수정할 수 있는 데이터 : 제목, 내용, 작성자
--    - 사용자에게 수정하게 하기 위해서 입력된 데이터를 보여주면서 수정하라고 시킨다. -> 글보기를 먼저 처리해고 수정처리한다.
UPDATE board
SET title = '오라클이란?', content = 'DBMS', writer ='홍길동'
WHERE no = 2;
COMMIT;

-- 5. 게시판 글삭제 - DELETE : 3번 글의 데이터를 삭제한다.
DELETE FROM board
WHERE no = 3;
COMMIT;