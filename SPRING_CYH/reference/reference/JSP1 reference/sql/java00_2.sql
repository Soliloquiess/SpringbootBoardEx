--  프로젝트를 위한 객체 만들기 - 테이블, 시퀀스, 샘플데이터
--   제거 -> 생성 -> 샘플데이터 
--  객체 생성을 위한 명령어 : CREATE
--  객체 제거를 위한 명령어 : DROP

--  객체 제거 ===================================================
-- 게시판 댓글 제거
-- 제거 : FK(board_rep) -> PK(board)
DROP TABLE board_rep CASCADE CONSTRAINTS;
DROP SEQUENCE board_rep_seq;

-- 게시판 제거
 DROP TABLE board CASCADE CONSTRAINTS;
 DROP SEQUENCE board_seq;
 
 --- 공지 사항 제거
DROP TABLE notice CASCADE CONSTRAINTS;
DROP SEQUENCE notice_seq;

-- 이미지 게시판 제거
-- 제거 : FK(image) -> PK(member)
DROP TABLE image;
DROP SEQUENCE image_seq;

-- 질문답변 제거
-- 제거 : FK(qna) -> PK(member)
DROP TABLE qna;
DROP SEQUENCE qna_seq;

-- 메시지 제거
-- 제거 : FK(message) -> PK(member)
DROP TABLE message;
DROP SEQUENCE message_seq;

-- 회원관리 제거
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;


 
 
 -- 객체 생성 =============================================================
 -- *** 제약 조건 - 데이터를 입력할 지 여부에 해당이 되는 조건 - insert에서 동작
 --  pk : primary key(주키) - 중복 배제, null이면 안된다.
 --  nn : not null(필수 데이터) - 데이터가 꼭 필요한 경우
 
 -- 회원등급 -> 회원 : PK -> FK
 -- 객체 생성 : PK(grade) -> FK(member) -> 테이블 정의서 참조
CREATE TABLE grade(
    gradeNo NUMBER(2) PRIMARY KEY,
    gradeName VARCHAR2(20)
);

-- 회원관리 >>>
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
    photo VARCHAR2(50),
    -- FK(Foriegn Key:참조키) - grade.gradeNo에 데이터가 없으면 입력 못함 null은 입력 가능.
    -- 1: 일반회원, 9: 관리자
    gradeNo NUMBER(2) DEFAULT 1 REFERENCES grade(gradeNo)
);

-- 메시지 >>>
-- 생성 : PK(member) -> FK(message)
CREATE TABLE message(
    no NUMBER PRIMARY KEY,
    content VARCHAR2(2000) NOT NULL,
    sender VARCHAR2(20) NOT NULL REFERENCES member(id),
    sendDate DATE DEFAULT SYSDATE,
    accepter VARCHAR2(20) NOT NULL REFERENCES member(id),
    acceptDate DATE,
    allUser NUMBER DEFAULT 0
    -- allUser NUMBER (1) DEFAULT 0
);
CREATE SEQUENCE message_seq;

-- drop sequence  message_seq;


drop table message;

-- 생성 : PK(member) -> FK(message)
-- newest ver
CREATE TABLE message(
    no NUMBER PRIMARY KEY,
    content VARCHAR2(2000) NOT NULL,
    senderId VARCHAR2(20) NOT NULL REFERENCES member(id),
    sendDate DATE DEFAULT SYSDATE,
    accepterId VARCHAR2(20) NOT NULL REFERENCES member(id),
    acceptDate DATE,
    -- allUser NUMBER DEFAULT 0
    allUser NUMBER (1) DEFAULT 0
);
CREATE SEQUENCE message_seq;





-- 질문답변 >>>
-- 부모에 해당되는 데이터가 삭제될 때 현재 데이터에 대한 처리 방법 선언할 수 있다.
-- REFERENCES qna(no) ON DELETE CASCADE - 현재 데이터도 같이 삭제한다.
-- REFERENCES qna(no) ON DELETE SET NULL - 현재 데이터의 참조항목을 null로 수정해 준다.
CREATE TABLE qna(
    no NUMBER PRIMARY KEY,
    title VARCHAR2(300) NOT NULL,
    content VARCHAR2(2000) NOT NULL,
    id VARCHAR2(20) NOT NULL REFERENCES member(id),
    writeDate DATE DEFAULT SYSDATE,
    hit NUMBER DEFAULT 0,
    refNo NUMBER REFERENCES qna(no),
    ordNo NUMBER,
    levNo NUMBER,
    parentNo NUMBER REFERENCES qna(no) ON DELETE CASCADE
);

CREATE SEQUENCE qna_seq;

-- 이미지 >>>
-- 생성 : PK(member) -> FK(image) : 이미지 게시판만 작업
CREATE TABLE image(
    no NUMBER PRIMARY KEY,
    title VARCHAR2(300) NOT NULL,
    content VARCHAR2(2000) NOT NULL,
    id VARCHAR2(20) NOT NULL REFERENCES member(id),
    writeDate DATE DEFAULT SYSDATE,
    fileName VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE image_seq;

-- 공지사항 >>>
CREATE TABLE notice(
    no NUMBER PRIMARY KEY,
    title VARCHAR2(300) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    startDate DATE DEFAULT SYSDATE,
    endDate DATE DEFAULT '9999-12-30',
    writeDate DATE DEFAULT SYSDATE,
    updateDate DATE DEFAULT SYSDATE
);
CREATE SEQUENCE notice_seq;


 -- 게시판 >>>
 CREATE TABLE board(
   no NUMBER PRIMARY KEY,
   title VARCHAR2(300) NOT NULL, -- 제목은 한글로 100자까지
   content VARCHAR2(2000) NOT NULL,
   writer VARCHAR2(30) NOT NULL, -- 작성자는 한글로 10자까지
   writeDate DATE DEFAULT SYSDATE, -- 작성일은 현재 시간에 해당되는 정보로 자동 입력(기본값 설정)되도록 하자. 
   -- 현재 날짜 키워드 - SYSDATE
   -- 기본 값 - 데이터가 입력되면 입력된 데이터를 입력하고 입력되지 않으면 기본값 사용.
   hit NUMBER DEFAULT 0
 );
 
 CREATE SEQUENCE board_seq;
 
-- 게시판 댓글 >>>
 -- 생성 : PK(board) -> FK(board_rep)
--CREATE TABLE board_rep(
--    rno NUMBER PRIMARY KEY,
--    no NUMBER REFERENCES board(no) ON DELETE CASCADE,
--    content VARCHAR2(500) NOT NULL,
--    writer VARCHAR2(30) NOT NULL,
--    writeDate DATE DEFAULT SYSDATE
--);
--CREATE SEQUENCE board_rep_seq;

-- drop table board_rep;
--drop sequence board_rep_seq;

CREATE TABLE board_reply(
    rno NUMBER PRIMARY KEY,
    no NUMBER REFERENCES board(no) ON DELETE CASCADE,
    content VARCHAR2(500) NOT NULL,
    writer VARCHAR2(30) NOT NULL,
    writeDate DATE DEFAULT SYSDATE
);
CREATE SEQUENCE board_rep_seq;
 
 commit;
 
 -- 샘플 데이터 ===================================================
 -- 회원등급 : PK(grade) -> FK(member)
-- 모든 데이터를 차례대로 입력하는 경우 컬럼이름을 생략할 수 있다.
INSERT INTO grade VALUES(1, '일반회원');
INSERT INTO grade VALUES(9, '관리자');

-- 회원관리
-- 관리자 계정 - 등급번호을 9로 반드시 해줘야 한다.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo, gradeNo)
VALUES('admin', '1111', '이영환', '남자', '1967-01-01', '010-1111-2222', 'leelj1@nate.com','/upload/member/admin.jpg', 9);
-- 일반회원 계정 - 등급번호는 기본으로 1이 된다.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES('test', '1111', '홍길동', '남자', '1994-01-01', '010-3333-4444', 'hong@naver.com','/upload/member/test.jpg');

-- 메시지
-- test -> admin
-- INSERT INTO message(no, content, sender, accepter)
-- VALUES(message_seq.nextval, '안녕하세요~', 'test', 'admin');
-- admin -> test
-- INSERT INTO message(no, content, sender, accepter)
-- VALUES(message_seq.nextval, '방갑습니다.~', 'admin', 'test');

-- 받은 메시지


--newsest메시지


-- test -> admin 메시지 전송: test 입장에서 보내는 메시지
INSERT INTO message(no, content, senderId, accepterId)
VALUES(message_seq.nextval, '안녕하세요~', 'test', 'admin');
-- admin -> test 메시지 전송: test 입장에서 받은 메시지
INSERT INTO message(no, content, senderId, accepterId)
VALUES(message_seq.nextval, '방갑습니다.~', 'admin', 'test');

commit;



-- 받은 메시지
-- select * from message where accept = 'test' order by no desc;

-- id로 바꾼 버전(뒤에 걍 id 하나 붙임)
select * from message where accepterId = 'test' order by no desc;

-- 받은 메시지 - 읽지 않은 메시지
select * from message where accepterId = 'test' and acceptDate is null order by no desc;

-- 받은 메시지 - 읽은 메시지
select * from message where accepterId = 'test' and acceptDate is not null order by no desc;

-- 받은 메시지 - 읽은 메시지
select * from message where accepterId = 'test' and acceptDate is not null order by no desc;

-- 보낸 메시지

select * from message where senderId = 'test' order by no desc;


-- 모든 메시지

select * from message where senderId = 'test' or accepterId = 'test' order by no desc;

-- 질문답변
--  질문하기 #1
--  동일쿼리에 작성된 seq.nextval는 같은 번호를 할당해 준다.
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, 'java란?', 'java란 무엇인가요?', 'test', qna_seq.nextval, 1, 0, null);
-- 답변하기 #1 - 위의 질문하기 데이터를 보면서 답변을 처리한다.
--    질문하기 데이터 - 글번호 : 1, 관련글번호 :1, 순서번호 : 1, 들여쓰기 0
--    답변하기 데이터 - 글번호 :seq, 관련글번호 :1, 순서번호 : 1+1, 들여쓰기 0+1, 부모글번호:1

--답변하기 
-- 질문을 보고 답변한다.
-- 보고있는 글 (1번) 글 : 부모글(parentNo->1)
-- 관련글(refno) : 최초 질문의 글 번호 -> 1번
-- 순서번호(ordno) :   데이터를 표시할 질문을  위에 그에 대한 답변은 아래. 답변이 여러개면 최근 작성된 답변번호
-- 들여쓰기(levno):화면에 얼마나 들여쓰기 할지 정하는 번호

-- 1번에 대한 답변
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[답변]java란?', '프로그램 언어', 'admin', 1, 2, 1, 1);



--  질문하기 #2
--  동일쿼리에 작성된 seq.nextval는 같은 번호를 할당해 준다.
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, 'oracle란?', 'oracle란 무엇인가요?', 'test', qna_seq.nextval, 1, 0, null);
-- 답변하기 #2 - 위의 질문하기 데이터를 보면서 답변을 처리한다.
--    질문하기 데이터 - 글번호 : 3, 관련글번호 :3, 순서번호 : 1, 들여쓰기 0
--    답변하기 데이터 - 글번호 :seq, 관련글번호 :3, 순서번호 : 1+1, 들여쓰기 0+1, 부모글번호:3
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[답변]oracle이란?', '데이터베이스', 'admin', 3, 2, 1, 3);


-- 답변하기 #1 - 위의 질문하기 데이터를 보면서 답변을 처리한다.
--    질문하기 데이터 - 글번호 : 1, 관련글번호 :1, 순서번호 : 1, 들여쓰기 0
--    답변하기 데이터 - 글번호 :seq, 관련글번호 :1, 순서번호 : 1+1, 들여쓰기 0+1, 부모글번호:1
--    관련글번호 1에 대한 순서번호 2이상의 데이터를 순서번호 + 1로 수정해준다. 그리고 데이터를 삽입해 준다.
UPDATE qna SET ordNo = ordNo + 1 WHERE refNo = 1 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[답변]java란?', '객체지향적 언어', 'admin', 1, 2, 1, 1);

-- 답변하기 #1-5 - 위의 질문하기 데이터를 보면서 답변을 처리한다.
--    질문하기 데이터 - 글번호 : 5, 관련글번호 :1, 순서번호 : 2, 들여쓰기 1
--    답변하기 데이터 - 글번호 :seq, 관련글번호 :1, 순서번호 : 2+1, 들여쓰기 1+1, 부모글번호:5
--    관련글번호 1에 대한 순서번호 3이상의 데이터를 순서번호 + 1로 수정해준다. 그리고 데이터를 삽입해 준다.
UPDATE qna SET ordNo = ordNo + 1 WHERE refNo = 1 and ordNo >= 3;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[답변][답변]java란?', '객체지향적 언어란?', 'test', 1, 3, 2, 5);


-- 이미지 : PK(member) -> FK(image) - 회원으로 등록되어 있는 아이디만 사용 가능 - test, admin
INSERT INTO image(no, title, content, id, fileName)
VALUES(image_seq.nextval, '이미지', '꽃이미지', 'test', '/upload/image/flower01.jpg');
INSERT INTO image(no, title, content, id, fileName)
VALUES(image_seq.nextval, '강아지', '귀여운강아지', 'admin', '/upload/image/dog01.jpg');


-- 공지사항 - 현재 날짜 2021-06-14
-- 현재 공지 - 현재 날짜가 시작일과 종료일 사이에 있는 데이터
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '자바과정 진행', '내용이이이ㅏ', '2021-04-01', '2021-10-28');
-- 지난 공지 - 종료일이 현재 날짜보다 작은 데이터
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '자바과정 시작', 'ㅇㅇ jsp진행', '2021-05-15', '2021-06-01');
-- 예약 공지 - 시작일이 현재 날짜보자 큰 데이터
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '자바과정 수료', '스프링 진행', '2021-10-13', '2021-10-28');

 
-- 게시판
 -- null : 데이터가 없다.
 INSERT INTO board(no, title, content, writer)
 VALUES(board_seq.nextval, 'java', 'java jjang','son');
 INSERT INTO board(no, title, content, writer)
 VALUES(board_seq.nextval, 'oracle', 'oracle jjang','lee');
 INSERT INTO board(no, title, content, writer)
 VALUES(board_seq.nextval, 'web', 'web jjang','kim');
 
 
-- 게시판 댓글 >>>
-- 게시판의 있는 글번호 2번글
-- 게시판에 2번 글이 있고 거기에 댓글 작성
INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '오라클 좋아요', 'lee');
INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '오라클은 디비다.', 'lee');
-- 이 중간 들어가는 번호그 글 번호. 글 번호가 부모키로 글 번호가 없으면 이 문장은 실행되지 않는다.


INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '오라클 싫어요', 'lee');


INSERT INTO board_reply(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '오라클 좋아요', 'lee');
INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '오라클은 디비다.', 'lee');

 COMMIT;
 
 
 
 
 
  
-- 게시판 댓글 >>>
-- 게시판의 있는 글번호 2번글
-- 게시판에 2번 글이 있고 거기에 댓글 작성
INSERT INTO board_reply(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 2, '오라클 좋아요', 'lee');
INSERT INTO board_reply(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 2, '오라클은 디비다.', 'lee');

 COMMIT;
 


--------------


-- 회원관리 운영 스키마
-- C: 회원가입
-- R: 회원 리스트, 회원 보기(내 정보 보기), 로그인 , 아이디 찾기, 비번 찾기
-- D: 사용하지 않는다. : 회원탈퇴 - 회원 상태 탈퇴로 바꿔준다. -> U

--1.리스트 - 아이디 , 이름, 생년, 연락처 , 등급번호, 등급명 , 최종 접속일
select m.id, m.name , m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate
From member m, grade g
where m.gradeNo = g.gradeNo
order by m.id;

select m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate from member m, grade g where m.gradeNo = g.gradeNo order by m.id;

-- 2.보기
select  m.id, m.name ,m.gender, m.birth, m.tel, m.email, m.regDate,  m.conDate, m.gradeNo, g.gradeName
from member m, grade g
where (id = 'test')and (m.gradeNo = g.gradeNo);


--3.쓰기(회원가입)
insert into member(id,pw,name,gender, birth, tel, email, photo)
values ('angel','1111','아이유','여','1993-03-01','010-0142-2341','iu@naver.com', '/upload/member/angel.jpg');

--4. 수정
update member set name ='hong' , gender = '남자', birth='1990-07-01', tel = '010-3333-4444', email = 'hong@naver.com'
where id = 'test' and pw = '1111';
commit;

--5. 삭제


delete from member
where id = 'test' and pw = '1111' and tel = '010-3333-4444';



select * from member;





delete from member where id ='asdf';
commit;

----



select no, title, writer, writeDate, hit from board order by no desc;


insert into board (no, title, writer, writeDate, hit) values(47,ㅎㅇ,ㅎㅇ,SYSDATE,0);



-- 질문 답변 게시판

--list 

select no, title, id, writeDate, hit, refNo, ordNo, levNo, parentNo
from qna order by refNo desc;











------

-- 이 아래는 셀렉트든 다른 쿼리창 하나 열고 실행하면 좋다

select * from qna;



-- 질문 답변 게시판

--list 

select no, title, id, writeDate, hit, refNo, ordNo, levNo, parentNo
from qna order by refNo desc,ordNo;


select * from message where accepter = 'test' order by no desc;


-------


select * from board;
select * from message;
select * from board_rep;


select * from board_reply;

select * from board where no  = 5;
select * from board_rep where no = 5 order by rno;





-- 한 페이지에 몇개의 데이터를 출력할 것인지 - 10개

-- 1페이지 시작 : 1, 끝 : 10 계산은 자바에서

-- 1. 원본 전체 데이터 가져오기. 정렬 글번호 역순

select no , title, writer, writeDate, hit
from board
order by no desc;

-- 많은 데이터 만들기 쿼리
-- 원래 게시판의 데이터를 꺼내서 다시 게시판의 데이터로 넣는다 x2로 데이터가 생긴다.

insert into board(no, title, content, writer)
(select board_seq.nextval,title,content,writer from board);

commit;

-- 2.  중간의 없어진 데이터 처리를 위해서 순서번호를 붙인다.

select rownum rnum, no, title, writer, writeDate, hit
from
( 
select no , title, writer,writeDate,hit
from board
order by no desc
);

--페이지에 맞는 데이터를 가져오기 - 1페이지 rnum이 1~10까지 데이터
select rnum , no, title, writer, writeDate, hit
from (
select rownum rnum, no, title, writer, writeDate, hit
from
( 
select no , title, writer,writeDate,hit
from board
order by no desc
)
)
where rnum between 1 and 10
;