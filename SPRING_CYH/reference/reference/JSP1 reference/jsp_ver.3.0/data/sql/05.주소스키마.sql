--- 주소 스키마 - 먼저 고객 데이터가 존재해야 하도록 설계한다. FK(REFERENCES 키워드 이용)
-- 1. 객체 제거
DROP TABLE address CASCADE CONSTRAINTS;
DROP SEQUENCE address_seq;

-- 2. 객체 생성
CREATE TABLE address(
  seq_id number PRIMARY KEY,
  address1 VARCHAR2(100) NOT NULL,
  address2 VARCHAR2(100) NOT NULL,
  address3 VARCHAR2(100) NOT NULL,
  postal_code VARCHAR2(7)  NOT NULL,
  client_id NUMBER NOT NULL REFERENCES client(client_id)
);

CREATE SEQUENCE address_seq;

-- 3. 샘플 데이터 넣기
INSERT INTO address(seq_id, address1, address2, address3, postal_code, client_id)
VALUES (address_seq.nextval, '의정부시', '의정부동', '이젠컴퓨터 학원', '11-222', 1);

-- 4. commit
COMMIT;

-- 5. 데이터 확인
select * from address;