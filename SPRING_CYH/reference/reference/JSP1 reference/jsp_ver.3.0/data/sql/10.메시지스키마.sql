-- 메시지 스키마 -> member 테이블이 먼저 있어야 만한다.
-- 객체 제거
DROP TABLE message CASCADE CONSTRAINTS;
DROP SEQUENCE message_seq;

-- 객체 생성
CREATE TABLE message(
  no NUMBER PRIMARY KEY,
  content VARCHAR2(2000) NOT NULL,
  senderId VARCHAR2(20) NOT NULL REFERENCES member(id),
  sendDate DATE DEFAULT SYSDATE NOT NULL,
  accepterId VARCHAR2(20) NOT NULL REFERENCES member(id),
  acceptDate DATE,
  allUser  NUMBER(1) DEFAULT 0
);

CREATE SEQUENCE message_seq;

-- 샘플데이터
--  test -> admin 메시지 전송 : test입장에서 보내는 메시지
INSERT INTO message(no, content, senderId, accepterId)
VALUES(message_seq.nextval, '하이루~~~', 'test', 'admin');
--  admin -> test 메시지 전송 : test입장에서 받은 메시지
INSERT INTO message(no, content, senderId, accepterId)
VALUES(message_seq.nextval, '방가방가~~~', 'admin', 'test');
COMMIT;

-- 받은 메시지
SELECT * FROM message WHERE accepterId = 'test' ORDER BY no DESC;
-- 받은 메시지 - 읽지 않은 메시지
SELECT * FROM message WHERE accepterId = 'test' AND acceptDate IS NULL ORDER BY no DESC;
-- 받은 메시지 - 읽은 메시지
SELECT * FROM message WHERE accepterId = 'test' AND acceptDate IS NOT NULL ORDER BY no DESC;

-- 보낸 메시지
SELECT * FROM message WHERE senderId = 'test' ORDER BY no DESC;

-- 모든 메시지
SELECT * FROM message WHERE senderId = 'admin' or accepterId = 'admin' ORDER BY no DESC;

