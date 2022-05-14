-- CREATE INDEX 인덱스이름 ON 테이블(컬럼 [정렬방식], ...)
CREATE INDEX IDX_MEMBER_NAME ON MEMBER (NAME ASC);
CREATE INDEX IDX_MEMBER_BIRTH_TEL ON MEMBER (BIRTH ASC, TEL DESC);

DROP VIEW VIEW_MEMBER_TEL;
-- CREATE OR REPLACE VIEW 뷰이름 AS SELECT 문
-- CREATE OR REPLACE : 없으면 CREATE, 있으면 덮어쓰기(REPLACE)를 한다.
CREATE OR REPLACE VIEW VIEW_MEMBER_TEL
AS SELECT 
    id, name, tel
FROM 
    member;

-- 새로운 메시지 => 읽지 않는 메시지 => 받은 날짜 = null
-- count(*) - 데이터의 갯수를 세는 함수
select count(*) cnt from message where accepterId = 'test' and acceptDate is null;
select * from message;
