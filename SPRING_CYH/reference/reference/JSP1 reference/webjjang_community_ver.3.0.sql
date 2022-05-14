-- <<< 웹짱 커뮤니티 전체 스키마 >>> --
-- 작성자 : 이영환
-- 작성일 : 2021. 03. 30
-- 버  전 : 3.0 - 게시판 비밀번호 시스템

--- [1] 객체 제거
------ [1-1] 테이블 제거(FK -> PK 순)
DROP TABLE board_reply CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE notice CASCADE CONSTRAINTS;
DROP TABLE image CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE message CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;

------ [1-2] 시퀀스 제거(순서와 상관없음)
DROP SEQUENCE board_reply_seq;
DROP SEQUENCE board_seq;
DROP SEQUENCE image_seq;
DROP SEQUENCE message_seq;
DROP SEQUENCE notice_seq;
DROP SEQUENCE qna_seq;



--- [2] 객체 생성(PK -> FK 순)
------ [2-1] 등급테이블 만들기
CREATE TABLE grade(
   gradeNo NUMBER(2) CONSTRAINT grade_gradeNo_pk PRIMARY KEY,
   gradeName VARCHAR2(20)
);

------ [2-2] 회원테이블 만들기
CREATE TABLE member(
id VARCHAR2(20) CONSTRAINT member_id_pk PRIMARY KEY, -- 아이디
pw VARCHAR2(20) CONSTRAINT member_pw_nn NOT NULL, -- 비밀번호
name VARCHAR2(30) CONSTRAINT member_name_nn NOT NULL, -- 이름
gender VARCHAR2(6) CONSTRAINT member_gender_nn NOT NULL
       CONSTRAINT member_gender_ck CHECK(gender in('남자','여자')), -- 성별
birth date CONSTRAINT member_birth_nn NOT NULL, -- 생년월일
tel VARCHAR2(13), -- 연락처
email VARCHAR2(50) CONSTRAINT member_email_nn NOT NULL, -- 이메일
regDate date DEFAULT sysdate, -- 가입일
conDate date DEFAULT sysdate, -- 최근 접속일
status VARCHAR2(6) DEFAULT '정상' 
       CONSTRAINT member_status_ck CHECK(status in ('정상','강퇴','탈퇴','휴면')), -- 상태
gradeNo NUMBER(2)  DEFAULT 1 CONSTRAINT member_gradeNo_fk REFERENCES grade(gradeNo) -- 등급
);

------ [2-3] 메시지 테이블 만들기
CREATE TABLE message(
no NUMBER PRIMARY KEY, -- 메시지 번호
content VARCHAR2(2000) NOT NULL, -- 내용
-- 회원이 사라지면 메시지도 사라진다. :  REFERENCES member(id) ON DELETE CASCADE
sender VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE , -- 보내는 사람의 ID (FK-참조하고 있는 PK의 타입과 크기에 맞춘다.)
sendDate DATE DEFAULT SYSDATE, -- 보낸 날짜 : 현재날짜가 기본
accepter VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE, -- 받는 사람의 ID
acceptDate DATE DEFAULT NULL -- 받은 날짜 : 현재날짜- 기본은 null
-- -> 받았다:날짜가 있다. 받지 않았다. 날짜가 없다.
);

------ [2-4] 질문 답변 테이블 만들기
CREATE TABLE qna(
no number PRIMARY KEY, -- 글번호
title VARCHAR2(300) NOT NULL, -- 제목
content VARCHAR2(2000) NOT NULL, -- 내용
id VARCHAR2(20) NOT NULL 
    REFERENCES member(id), -- 작성자 아이디(로그인 정보에서 자동으로 가져옴)
writeDate date DEFAULT sysdate, -- 작성일
hit NUMBER DEFAULT 0, -- 조회수
refNo NUMBER REFERENCES qna(no), -- 관련글
ordNo NUMBER, -- 순서번호
levNo NUMBER, -- 들여쓰기
-- REFERENCES qna(no)로 참조키를 만들면 부모 데이터가 삭제되면 내글을 어떻게 처리할 것인가 정할 수 있다.
-- ON DELETE CASCADE : 내글도 함께 지운다.
-- ON DELETE SET NULL : 내글은 지우지 말고 참조키로 되어 있는 컬럼을 null로 셋팅해 주세요.
parentNo NUMBER REFERENCES qna(no) ON DELETE CASCADE -- 부모글(보고 있는 글)
);

------ [2-5] 이미지 테이블 만들기
CREATE TABLE image(
no NUMBER CONSTRAINT image_no_pk PRIMARY KEY, -- 글번호
title VARCHAR2(300) NOT NULL, -- 제목
content VARCHAR2(2000) NOT NULL, -- 내용
id VARCHAR2(20) NOT NULL REFERENCES member(id), -- 작성자 아이디 -> 로그인이 먼저 되어야만 한다.
writeDate DATE DEFAULT SYSDATE, -- 작성일
fileName VARCHAR2(50) NOT NULL -- 첨부된 이미지 파일명
);

------ [2-6] 공지사항 테이블 만들기
CREATE TABLE notice(
no NUMBER PRIMARY KEY, -- 공지번호
title VARCHAR2(300) NOT NULL, -- 공지제목
content VARCHAR2(2000) NOT NULL, -- 공지 내용
startDate date DEFAULT SYSDATE, -- 공지 시작일
endDate date DEFAULT '9999-12-30', -- 공지 종료일
writeDate date DEFAULT SYSDATE, --공지 작성일
updateDate date DEFAULT SYSDATE -- 공지 수정일
);

------ [2-7] 일반게시판 테이블 만들기
CREATE TABLE board(
no NUMBER PRIMARY KEY, -- 글번호
title VARCHAR2(300) NOT NULL, -- 제목
content VARCHAR2(2000) NOT NULL, -- 내용
writer VARCHAR2(30) NOT NULL, -- 작성자
-- SYSDATE : 오라클에서의 오늘 날짜를 의미하는 키워드
writeDate DATE DEFAULT SYSDATE, -- DEFAULT 값이 셋팅되어 있으면 NOT NULL을 사용하지 않는다.(의미없음)
hit NUMBER DEFAULT 0, -- 조회수
pw VARCHAR2(20)
);

------ [2-8] 일반게시판 댓글 테이블 만들기
CREATE TABLE board_reply
(
  rno NUMBER CONSTRAINT board_reply_rno_pk PRIMARY KEY, --댓글 번호
  no NUMBER CONSTRAINT board_reply_no_fk REFERENCES board(no) ON DELETE CASCADE, --글번호
  content VARCHAR2(600) NOT NULL, -- 내용
  writer VARCHAR2(30) NOT NULL, -- 작성자
  writeDate DATE DEFAULT SYSDATE -- 작성일
);


------ [2-2] 시퀀스 만들기
CREATE SEQUENCE board_reply_seq;
CREATE SEQUENCE board_seq;
CREATE SEQUENCE image_seq;
CREATE SEQUENCE message_seq;
CREATE SEQUENCE notice_seq;
CREATE SEQUENCE qna_seq;



--- [3] 샘플데이터 넣기(PK -> FK 순 : 있는 데이터만 입력가능한다.)
------ [3-1] 회원 등급 
INSERT INTO grade VALUES (1, '일반회원');
INSERT INTO grade VALUES (9, '관리자');

------ [3-2] 회원
-- 관리자 계정입력하기
INSERT INTO member(id, pw, name, gender, birth, email, gradeNo)
VALUES('admin','1111', '이영환','남자','1967-01-01','leelj1@nate.com', 9);
-- 일반회원 계정입력하기 - 회원가입을 할때 등급번호를 선택하지는 않는다. 기본값은 1(일반회원)로 셋팅해서 사용한다.
INSERT INTO member(id, pw, name, gender, birth, email)
VALUES('test','1111', '홍길동','남자','1980-01-01','leelj1@nate.com');
INSERT INTO member(id, pw, name, gender, birth, email)
VALUES('angel','1111', '김혜수','여자','1975-01-01','angel@naver.com');

------ [3-3] 메시지
-- test -> admin
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '잘지내지?', 'test', 'admin');
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '오랜만입니다.?', 'test', 'admin');
commit;
-- admin -> test
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '잘지내고 있습니다. 감사합니다.', 'admin', 'test');
-- admin -> angel
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '환영합니다. 잘 지내 보아요~~~.', 'admin', 'angel');

------ [3-4] 질문 답변
-- 1) 질문하기 - 2개 이상
--   - 관련글번호가 자신 글번호와 같다. --> 질문이다.
--   - 순서가 1번이다.
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '자바란?','자바란 무엇일까요?', 'test',
       qna_seq.nextval, 1, 0, qna_seq.nextval);
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '오라클이란?','오라클이란 무엇일까요?', 'test',
       qna_seq.nextval, 1, 0, qna_seq.nextval);
       
-- 2) 답변하기 - 한개의 질문에 2개이상의 답변
-- '자바란?'에 대한 답변 : 글번호 1, refNo : 1, ordNo : 1, levNo:0
-- 먼저 달린 답변이 있을 수 있다. 먼저 달리 답변의 순서번호(입력하려는 순서 번호보다 같거나 큰)를 전체 + 1 해줘야 한다.
-- 자바란? 첫번째 답변
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[답변]자바란?','객체지향적 프로그래밍 언어', 'admin',
       1, 2, 1, 1); -- refNo:부모의 refNo를 그대로 넣는다. ordNo = ordNo + 1. levNo = levNo + 1. 부모글의 no
       
-- 자바란? 두번째 답변
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[답변]자바란?','호환성이 좋다.', 'admin',
       1, 2, 1, 1); -- refNo:부모의 refNo를 그대로 넣는다. ordNo = ordNo + 1. levNo = levNo + 1. 부모글의 no

-- '오라클이란?' 답변 : 글번호 2, refNo : 2, ordNo : 1, levNo:0
-- 먼저 달린 답변이 있을 수 있다. 먼저 달리 답변의 순서번호(입력하려는 순서 번호보다 같거나 큰)를 전체 + 1 해줘야 한다.
-- 오라클이란? 첫번째 답변
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 2 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[답변]오라클이란?','DBMS 입니다.', 'admin',
       2, 2, 1, 2); -- refNo:부모의 refNo를 그대로 넣는다. ordNo = ordNo + 1. levNo = levNo + 1. 부모글의 no

-- 오라클이란 두번째 답변
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 2 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[답변]오라클이란?','오라클사에서 만듬.', 'admin',
       2, 2, 1, 2); -- refNo:부모의 refNo를 그대로 넣는다. ordNo = ordNo + 1. levNo = levNo + 1. 부모글의 no


-- 3) 답변>답변하기
-- '[답변]자바란?' - 4 : 글번호 4, refNo : 1, ordNo : 2, levNo:1
-- 먼저 달린 답변이 있을 수 있다. 먼저 달리 답변의 순서번호(입력하려는 순서 번호보다 같거나 큰)를 전체 + 1 해줘야 한다.
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 3; -- 데이터가 들어가야할 순서 번호와 같거나 큰 순서번호를 1증가.

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[답변][답변]자바란?','재활용성과 관리가 편하다.', 'admin',
       1, 3, 2, 4); -- refNo:부모의 refNo를 그대로 넣는다. ordNo = ordNo + 1. levNo = levNo + 1. 부모글의 no

UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 3; -- 데이터가 들어가야할 순서 번호와 같거나 큰 순서번호를 1증가.

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[답변][답변]자바란?','중복된 것과 복잡한 것을 꺼낸다..', 'admin',
       1, 3, 2, 4); -- refNo:부모의 refNo를 그대로 넣는다. ordNo = ordNo + 1. levNo = levNo + 1. 부모글의 no

------ [3-5] 이미지
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '강아지', '귀여운 강아지','test','/upload/image/m_dog01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '고양이', '야옹야옹 고양이','admin','/upload/image/m_cat01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '이쁜 꽃 모음', '이쁜 꽃 모음입니다.','admin','/upload/image/m_flower01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '귀여운 선인장', '귀여운 선인장입니다.','admin','/upload/image/m_cactus01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '꽃 바구니 - 럭셔리한 꽃바구니 이미지를 올립니다. 벌써 봄이네요.', '꽃 바구니입니다.','angel','/upload/image/m_flower04.jpg');

------ [3-6] 공지사항
-- 현재 날짜와 시간 : 2021-03-30 12:06
-- 샘플 데이터 입력
-- 현재 공지 - 시작일 현재 날짜보다 작다. 종료일이 현재 날짜보다 크다.
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '자바 개발자 과정을 국기 과정으로 전액 무료로 진행합니다.', '자바로 솔루션을 개발하는 과정',
'2020-12-10', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '문의 사항은 알맞게', '행정과 강사님 활용',
'2020-12-10', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '프로젝트 진행', 'JSP-Servlet / Spring Project',
'2021-03-10', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '프로젝트 발표 - 2번의 프로젝트를 진행하며 팀으로 개발하게 됩니다. 서로 협조해야 합니다.', '프로젝트 문서화 작업 후 진행',
'2021-03-15', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '출결 관련 공지', '큐알로 잘 찍으세요. 입실/퇴실',
'2020-12-10', '2021-05-28');
-- 지난 공지 - 종료일이 현재 날짜보다 작다.
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '자바 개발자 과정 모집', '자바로 솔루션을 개발하는 과정',
'2020-11-10', '2020-12-16');
-- 예약 공지 - 시작일이 현재 날짜보다 크다.
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '자바 개발자 과정 수료', '수료를 축하합니다.',
'2021-05-22', '2021-05-29');

------ [3-7] 일반 게시판
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'java에 대한 이해와 논리 정연한 강의 진행', 
'java jjang', '이영환', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'oracle', 'oracle jjang', '김홍길', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'web', 'web jjang', '홍길동', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'jsp', 'jsp jjang', '강감찬', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'spring은 자바 프레임 워크로 효율적인 개발을 위해 자바로 틀을 만들어 놓은 것입니다.',
'spring jjang', '손흥민', '1111');


------ [3-8] 일반 게시판 댓글
insert into board_reply(rno, no, content, writer)
values (board_reply_seq.nextval, 2, '감사합니다.','이영환');
insert into board_reply(rno, no, content, writer)
values (board_reply_seq.nextval, 2, '오라클은 디비의 일종.','홍길동');
insert into board_reply(rno, no, content, writer)
values (board_reply_seq.nextval, 2, '축구보다 힘들다.','손흥민');


COMMIT;


