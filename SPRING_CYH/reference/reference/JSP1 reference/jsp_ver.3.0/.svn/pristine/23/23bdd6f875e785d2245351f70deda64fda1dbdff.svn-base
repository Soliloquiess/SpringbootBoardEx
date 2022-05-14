-- 객체 제거
DROP TABLE notice CASCADE CONSTRAINTS;
DROP SEQUENCE notice_seq;

-- 객체 생성
CREATE TABLE notice(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  startDate DATE DEFAULT SYSDATE,
  endDate DATE DEFAULT '9999-12-30',
  writeDate DATE DEFAULT SYSDATE,
  updateDate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE notice_seq;

-- 샘플 데이터 - 현재 날짜 : 2021.12.27
-- 지난 공지 - 2021년 성탄절 이벤트 - 2021.11.25 ~ 2021.12.25
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '2021년 성탄절 이벤트', '2021년 성탄절 이벤트- 눈이 오면 쏩니다.', '2021-11-25', '2021-12-25');
-- 현재 공지 - 2022년 새해 메시지 이벤트 - 2021.12.01~2022.01.07 -> 모든 사용자가 볼 수 있다.
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '2022년 새해 메시지 이벤트', '2022년 새해 메시지 이벤트- 지인에게 메세지를', '2021-12-01', '2022-01-07');
-- 예약 공지 - 2022년 수료 - 2022.03.15~2022.03.30
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '2022년 수료', '2022년 수료 - 축하드립니다. 수고하셨습니다.', '2022-03-15', '2022-03-30');

COMMIT;

SELECT * FROM notice;