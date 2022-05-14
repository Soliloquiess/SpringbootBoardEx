-- 회원관리 스키마. - member, grade
-- 객체 제거 : FK(member) -> PK(grade)
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;
-- 객체 생성 : PK(grade) -> FK(member) -> 테이블 정의서 참조
CREATE TABLE grade(
gradeNo NUMBER(2) PRIMARY KEY,
gradeName VARCHAR2(20) NOT NULL
);
CREATE TABLE member(
-- PK(PRIMARY KEY) - 중복 배제, null이면 안된다.
id VARCHAR2(20) PRIMARY KEY,
pw VARCHAR2(20) NOT NULL,
name VARCHAR2(30) NOT NULL,
-- 성별 : 남자, 여자만 입력할 수 있다.
-- 제약조건 : CK(Check(조건))
gender VARCHAR2(6) NOT NULL CHECK (gender in('남자', '여자')),
birth DATE NOT NULL,
tel VARCHAR2(13),
email VARCHAR2(50) NOT NULL,
regDate DATE DEFAULT SYSDATE,
conDate DATE DEFAULT SYSDATE,
status VARCHAR2(6) DEFAULT '정상' CHECK (status in ('정상','강퇴','탈퇴','휴면')) ,
photo VARCHAR2(100) DEFAULT '/upload/member/noImage.jpg',
-- FK(Foriegn Key:참조키) - grade.gradeNo에 데이터가 없으면 입력 못함 null은 입력 가능.
-- PK에 해당되는 grade 테이블 데이터를 지우려고 하면 기본적으로 지울 수 없도록 정의되어 있다.
-- on delete 옵션을 이용해서 PK를 지우려고 하면 어떻게 동작을 하는 지 정의할 수 있다. cascade, set null
-- 1: 일반회원, 9: 관리자
gradeNo NUMBER(2) DEFAULT 1 REFERENCES grade(gradeNo)
);
-- 샘플 데이터 : PK(grade) -> FK(member)
-- 모든 데이터를 차례대로 입력하는 경우 컬럼이름을 생략할 수 있다.
INSERT INTO grade VALUES(1, '일반회원');
INSERT INTO grade VALUES(9, '관리자');
-- 관리자 계정 - 등급번호을 9로 반드시 해줘야 한다.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo, gradeNo)
VALUES('admin', '1111', '이영환', '남자', '1967-01-01', '010-1111-2222', 'leelj1@nate.com','/upload/member/admin.jpg', 9);
-- 일반회원 계정 - 등급번호는 기본으로 1이 된다.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES('test', '1111', '홍길동', '남자', '1994-01-01', '010-3333-4444', 'hong@naver.com','/upload/member/test.jpg');
COMMIT;
drop SEQUENCE member_seq;
create sequence member_seq START WITH 1000; -- 회원데이터 증가용 시퀀스
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
(select id || member_seq.nextval, pw, name, gender, birth, tel, email, photo from member);
commit;
select * from grade;
select * from member;