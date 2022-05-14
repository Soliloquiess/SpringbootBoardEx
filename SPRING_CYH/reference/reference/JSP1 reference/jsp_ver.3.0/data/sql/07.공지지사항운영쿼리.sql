-- 현재 공지 리스트 - 현재 날짜(SYSDATE)가 시작일과 종료일의 사이에 있다.
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE)
ORDER BY updateDate DESC;

-- view table을 이용해보자.
CREATE OR REPLACE VIEW notice_now
AS
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE)
ORDER BY updateDate DESC;

select * from notice_now;

select no, title, startDate, endDate, updateDate from notice_now;

-- 지난 공지 리스트 - 현재 날짜(SYSDATE)가 종료일보다 크다.(종료일을 지나갔다.)
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE endDate < TRUNC(SYSDATE)
ORDER BY updateDate DESC;

-- 예약 공지 리스트 - 현재 날짜(SYSDATE)가 시작일보다 작다.(시작일이 오지 않았다.)
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE startDate > SYSDATE
ORDER BY updateDate DESC;

-- 전체 공지 - 조건이 없다.
SELECT no, title, 
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
-- 1차 정렬은 updateDate하고 똑같은 데이터가 있는 경우 그안에서 no 2차 정렬시킨다.
ORDER BY updateDate DESC, no DESC;


select  to_char(TRUNC(SYSDATE), 'yyyy-mm-dd hh24 : mi : ss') from dual;

select sysdate from dual;
select to_char(sysdate, 'yyyy-mm-dd') now from dual;

-- 2. 보기 -- JSP -> list.jsp 에서 no 만 가지고 온다. inc는 조회수가 없어서 필요가 없다.
SELECT no, title, content, startDate, endDate, updateDate
FROM notice
WHERE no = 2;
-- 3. 쓰기 -- JSP -> list.jsp 에서 온다. - title, content, startDate, endDate 입력
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '공지사항입니다.', '알립니다.', '2021-12-28', '2022-01-01');
COMMIT;
-- 4. 수정 -- JSP -> view.jsp 에서 글번호 전달해서 온다. - title, content, startDate, endDate 입력
UPDATE notice
SET title = '공지 수정', content = '공지 수정입니다.', startDate = '2021-12-28', endDate = '2022-01-01'
WHERE no = 4;
COMMIT;
-- 5. 삭제 -- JSP - view.jsp에서 글번호를 전달해서 온다. 
DELETE FROM notice
WHERE no = 4;

ROLLBACK;

