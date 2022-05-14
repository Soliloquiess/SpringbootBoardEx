-- 고객 테이블
-- 1. 객체를 제거
DROP TABLE client CASCADE CONSTRAINTS;
DROP SEQUENCE client_seq;

-- 2. 객체를 생성한다.
CREATE TABLE client(
  client_id number PRIMARY KEY,
  name VARCHAR2(30) NOT NULL,
  phone VARCHAR2(13) NOT NULL,
  social_s_no CHAR(14) NOT NULL
);
CREATE SEQUENCE client_seq;

-- 3. 샘플데이터 넣기
INSERT INTO client(client_id, name, phone, social_s_no)
VALUES (client_seq.nextval, '홍길동', '010-1111-2222', '111111-1222222');
INSERT INTO client(client_id, name, phone, social_s_no)
VALUES (client_seq.nextval, '손흥민', '010-1111-4444', '111111-1444444');
INSERT INTO client(client_id, name, phone, social_s_no)
VALUES (client_seq.nextval, '황희찬', '010-1111-8888', '111111-1888888');

-- 4. 완전 적용
COMMIT;

-- 5. 데이터 확인
select * from client;