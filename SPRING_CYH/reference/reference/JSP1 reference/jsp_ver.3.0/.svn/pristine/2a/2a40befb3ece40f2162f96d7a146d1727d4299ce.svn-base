-- 객체 지우기
DROP TABLE image CASCADE CONSTRAINTS;
DROP SEQUENCE image_seq;

-- 객체 생성
CREATE TABLE image(
 no NUMBER PRIMARY KEY,
 title VARCHAR2(300) NOT NULL,
 content VARCHAR2(2000) NOT NULL,
 -- id는 회원관리테이블의 PK를 참조하므로 회원테이블의 타입과 길이가 같아야한다.
 -- 데이터 입력할 때 회원테이블에 있는 데이터만 사용할 수 있다.
 id VARCHAR2(20) NOT NULL REFERENCES member(id),
 writeDate DATE DEFAULT sysdate,
 fileName VARCHAR2(100) NOT NULL
);

CREATE SEQUENCE image_seq;

-- 샘플 데이터 넣기
INSERT INTO image(no,title, content, id, fileName)
VALUES (image_seq.nextval, '강아지', 'image입니다.', 'test', '/upload/image/dog01.jpg');
INSERT INTO image(no,title, content, id, fileName)
VALUES (image_seq.nextval, '야옹이', '예쁜 고양이', 'admin', '/upload/image/cat01.jpg');
COMMIT;

SELECT * FROM image;
