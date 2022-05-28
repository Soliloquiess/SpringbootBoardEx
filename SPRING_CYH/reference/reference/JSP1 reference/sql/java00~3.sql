--  ������Ʈ�� ���� ��ü ����� - ���̺�, ������, ���õ�����
--   ���� -> ���� -> ���õ����� 
--  ��ü ������ ���� ��ɾ� : CREATE
--  ��ü ���Ÿ� ���� ��ɾ� : DROP

--  ��ü ���� ===================================================
-- �Խ��� ��� ����
-- ���� : FK(board_rep) -> PK(board)
DROP TABLE board_rep CASCADE CONSTRAINTS;
DROP SEQUENCE board_rep_seq;

-- �Խ��� ����
 DROP TABLE board CASCADE CONSTRAINTS;
 DROP SEQUENCE board_seq;
 
 --- ���� ���� ����
DROP TABLE notice CASCADE CONSTRAINTS;
DROP SEQUENCE notice_seq;

-- �̹��� �Խ��� ����
-- ���� : FK(image) -> PK(member)
DROP TABLE image;
DROP SEQUENCE image_seq;

-- �����亯 ����
-- ���� : FK(qna) -> PK(member)
DROP TABLE qna;
DROP SEQUENCE qna_seq;

-- �޽��� ����
-- ���� : FK(message) -> PK(member)
DROP TABLE message;
DROP SEQUENCE message_seq;

-- ȸ������ ����
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;


 
 
 -- ��ü ���� =============================================================
 -- *** ���� ���� - �����͸� �Է��� �� ���ο� �ش��� �Ǵ� ���� - insert���� ����
 --  pk : primary key(��Ű) - �ߺ� ����, null�̸� �ȵȴ�.
 --  nn : not null(�ʼ� ������) - �����Ͱ� �� �ʿ��� ���
 
 -- ȸ����� -> ȸ�� : PK -> FK
 -- ��ü ���� : PK(grade) -> FK(member) -> ���̺� ���Ǽ� ����
CREATE TABLE grade(
    gradeNo NUMBER(2) PRIMARY KEY,
    gradeName VARCHAR2(20)
);

-- ȸ������ >>>
CREATE TABLE member(
    -- PK(PRIMARY KEY) - �ߺ� ����, null�̸� �ȵȴ�.
    id VARCHAR2(20) PRIMARY KEY,
    pw VARCHAR2(20) NOT NULL,
    name VARCHAR2(30) NOT NULL,
    -- ���� : ����, ���ڸ� �Է��� �� �ִ�.
    -- �������� : CK(Check(����))
    gender VARCHAR2(6) NOT NULL CHECK (gender in('����', '����')),
    birth DATE NOT NULL,
    tel VARCHAR2(13),
    email VARCHAR2(50) NOT NULL,
    regDate DATE DEFAULT SYSDATE,
    conDate DATE DEFAULT SYSDATE,
    status VARCHAR2(6) DEFAULT '����' CHECK (status in ('����','����','Ż��','�޸�')) ,
    photo VARCHAR2(50),
    -- FK(Foriegn Key:����Ű) - grade.gradeNo�� �����Ͱ� ������ �Է� ���� null�� �Է� ����.
    -- 1: �Ϲ�ȸ��, 9: ������
    gradeNo NUMBER(2) DEFAULT 1 REFERENCES grade(gradeNo)
);

-- �޽��� >>>
-- ���� : PK(member) -> FK(message)
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

-- ���� : PK(member) -> FK(message)
-- newest ver
--CREATE TABLE message(
--    no NUMBER PRIMARY KEY,
--    content VARCHAR2(2000) NOT NULL,
--    senderId VARCHAR2(20) NOT NULL REFERENCES member(id),
--    senderName varchar(50),
--    sendDate DATE DEFAULT SYSDATE,
--    accepterId VARCHAR2(20) NOT NULL REFERENCES member(id),
--    
--    accepterName varchar(50),
--    acceptDate DATE,
--    -- allUser NUMBER DEFAULT 0
--    allUser NUMBER (1) DEFAULT 0
--);
--CREATE SEQUENCE message_seq;


-- newestnewset ver
--������ accepterid�� senderid���� accepter,  sender�� �ڿ� id�α� �� ���ذ�
CREATE TABLE message(
    no NUMBER PRIMARY KEY,
    content VARCHAR2(2000) NOT NULL,
    sender VARCHAR2(20) NOT NULL REFERENCES member(id),
    --senderName varchar(50),
    sendDate DATE DEFAULT SYSDATE,
    accepter VARCHAR2(20) NOT NULL REFERENCES member(id),
    
--    accepterName varchar(50),
    acceptDate DATE,
    -- allUser NUMBER DEFAULT 0
    allUser NUMBER (1) DEFAULT 0
);
CREATE SEQUENCE message_seq;





-- �����亯 >>>
-- �θ� �ش�Ǵ� �����Ͱ� ������ �� ���� �����Ϳ� ���� ó�� ��� ������ �� �ִ�.
-- REFERENCES qna(no) ON DELETE CASCADE - ���� �����͵� ���� �����Ѵ�.
-- REFERENCES qna(no) ON DELETE SET NULL - ���� �������� �����׸��� null�� ������ �ش�.
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

-- �̹��� >>>
-- ���� : PK(member) -> FK(image) : �̹��� �Խ��Ǹ� �۾�
CREATE TABLE image(
    no NUMBER PRIMARY KEY,
    title VARCHAR2(300) NOT NULL,
    content VARCHAR2(2000) NOT NULL,
    id VARCHAR2(20) NOT NULL REFERENCES member(id),
    writeDate DATE DEFAULT SYSDATE,
    fileName VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE image_seq;

-- �������� >>>
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


 -- �Խ��� >>>
 CREATE TABLE board(
   no NUMBER PRIMARY KEY,
   title VARCHAR2(300) NOT NULL, -- ������ �ѱ۷� 100�ڱ���
   content VARCHAR2(2000) NOT NULL,
   writer VARCHAR2(30) NOT NULL, -- �ۼ��ڴ� �ѱ۷� 10�ڱ���
   writeDate DATE DEFAULT SYSDATE, -- �ۼ����� ���� �ð��� �ش�Ǵ� ������ �ڵ� �Է�(�⺻�� ����)�ǵ��� ����. 
   -- ���� ��¥ Ű���� - SYSDATE
   -- �⺻ �� - �����Ͱ� �ԷµǸ� �Էµ� �����͸� �Է��ϰ� �Էµ��� ������ �⺻�� ���.
   hit NUMBER DEFAULT 0
 );
 
 CREATE SEQUENCE board_seq;
 
-- �Խ��� ��� >>>
 -- ���� : PK(board) -> FK(board_rep)
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
 
 -- ���� ������ ===================================================
 -- ȸ����� : PK(grade) -> FK(member)
-- ��� �����͸� ���ʴ�� �Է��ϴ� ��� �÷��̸��� ������ �� �ִ�.
INSERT INTO grade VALUES(1, '�Ϲ�ȸ��');
INSERT INTO grade VALUES(9, '������');

-- ȸ������
-- ������ ���� - ��޹�ȣ�� 9�� �ݵ�� ����� �Ѵ�.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo, gradeNo)
VALUES('admin', '1111', '�̿�ȯ', '����', '1967-01-01', '010-1111-2222', 'leelj1@nate.com','/upload/member/admin.jpg', 9);
-- �Ϲ�ȸ�� ���� - ��޹�ȣ�� �⺻���� 1�� �ȴ�.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES('test', '1111', 'ȫ�浿', '����', '1994-01-01', '010-3333-4444', 'hong@naver.com','/upload/member/test.jpg');

-- �޽���


-- test -> admin
 INSERT INTO message(no, content, sender, accepter)
 VALUES(message_seq.nextval, '�ȳ��ϼ���~', 'test', 'admin');
-- admin -> test
 INSERT INTO message(no, content, sender, accepter)
 VALUES(message_seq.nextval, '�氩���ϴ�.~', 'admin', 'test');

-- ���� �޽���


--newsest�޽���


-- test -> admin �޽��� ����: test ���忡�� ������ �޽���
--INSERT INTO message(no, content, senderId, accepterId)
--VALUES(message_seq.nextval, '�ȳ��ϼ���~', 'test', 'admin');
-- admin -> test �޽��� ����: test ���忡�� ���� �޽���
--INSERT INTO message(no, content, senderId, accepterId)
--VALUES(message_seq.nextval, '�氩���ϴ�.~', 'admin', 'test');

commit;



-- ���� �޽���
-- select * from message where accept = 'test' order by no desc;

-- id�� �ٲ� ����(�ڿ� �� id �ϳ� ����)
select * from message where accepterId = 'test' order by no desc;

-- ���� �޽��� - ���� ���� �޽���
select * from message where accepterId = 'test' and acceptDate is null order by no desc;

-- ���� �޽��� - ���� �޽���
select * from message where accepterId = 'test' and acceptDate is not null order by no desc;

-- ���� �޽��� - ���� �޽���
select * from message where accepterId = 'test' and acceptDate is not null order by no desc;

-- ���� �޽���

select * from message where senderId = 'test' order by no desc;


-- ��� �޽���

select * from message where senderId = 'test' or accepterId = 'test' order by no desc;

-- �����亯
--  �����ϱ� #1
--  ���������� �ۼ��� seq.nextval�� ���� ��ȣ�� �Ҵ��� �ش�.
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, 'java��?', 'java�� �����ΰ���?', 'test', qna_seq.nextval, 1, 0, null);
-- �亯�ϱ� #1 - ���� �����ϱ� �����͸� ���鼭 �亯�� ó���Ѵ�.
--    �����ϱ� ������ - �۹�ȣ : 1, ���ñ۹�ȣ :1, ������ȣ : 1, �鿩���� 0
--    �亯�ϱ� ������ - �۹�ȣ :seq, ���ñ۹�ȣ :1, ������ȣ : 1+1, �鿩���� 0+1, �θ�۹�ȣ:1
--    nextval�� ���������ε� �ѹ� �ҷ����� ��� ordNO�� ������ �� ���� �����Ƿ� 1��, �鿩����� 0�� �鿩����

--�亯�ϱ� 
-- ������ ���� �亯�Ѵ�.
-- �����ִ� �� (1��) �� : �θ��(parentNo->1)
-- ���ñ�(refno) : ���� ������ �� ��ȣ -> 1��
-- ������ȣ(ordno) :   �����͸� ǥ���� ������  ���� �׿� ���� �亯�� �Ʒ�. �亯�� �������� �ֱ� �ۼ��� �亯��ȣ
-- �鿩����(levno):ȭ�鿡 �󸶳� �鿩���� ���� ���ϴ� ��ȣ

-- 1���� ���� �亯
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[�亯]java��?', '���α׷� ���', 'admin', 1, 2, 1, 1);



--  �����ϱ� #2
--  ���������� �ۼ��� seq.nextval�� ���� ��ȣ�� �Ҵ��� �ش�.
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, 'oracle��?', 'oracle�� �����ΰ���?', 'test', qna_seq.nextval, 1, 0, null);
-- �亯�ϱ� #2 - ���� �����ϱ� �����͸� ���鼭 �亯�� ó���Ѵ�.
--    �����ϱ� ������ - �۹�ȣ : 3, ���ñ۹�ȣ :3, ������ȣ : 1, �鿩���� 0
--    �亯�ϱ� ������ - �۹�ȣ :seq, ���ñ۹�ȣ :3, ������ȣ : 1+1, �鿩���� 0+1, �θ�۹�ȣ:3
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[�亯]oracle�̶�?', '�����ͺ��̽�', 'admin', 3, 2, 1, 3);


-- �亯�ϱ� #1 - ���� �����ϱ� �����͸� ���鼭 �亯�� ó���Ѵ�.
--    �����ϱ� ������ - �۹�ȣ : 1, ���ñ۹�ȣ :1, ������ȣ : 1, �鿩���� 0
--    �亯�ϱ� ������ - �۹�ȣ :seq, ���ñ۹�ȣ :1, ������ȣ : 1+1, �鿩���� 0+1, �θ�۹�ȣ:1
--    ���ñ۹�ȣ 1�� ���� ������ȣ 2�̻��� �����͸� ������ȣ + 1�� �������ش�. �׸��� �����͸� ������ �ش�.
UPDATE qna SET ordNo = ordNo + 1 WHERE refNo = 1 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[�亯]java��?', '��ü������ ���', 'admin', 1, 2, 1, 1);

-- �亯�ϱ� #1-5 - ���� �����ϱ� �����͸� ���鼭 �亯�� ó���Ѵ�.
--    �����ϱ� ������ - �۹�ȣ : 5, ���ñ۹�ȣ :1, ������ȣ : 2, �鿩���� 1
--    �亯�ϱ� ������ - �۹�ȣ :seq, ���ñ۹�ȣ :1, ������ȣ : 2+1, �鿩���� 1+1, �θ�۹�ȣ:5
--    ���ñ۹�ȣ 1�� ���� ������ȣ 3�̻��� �����͸� ������ȣ + 1�� �������ش�. �׸��� �����͸� ������ �ش�.
UPDATE qna SET ordNo = ordNo + 1 WHERE refNo = 1 and ordNo >= 3;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
VALUES(qna_seq.nextval, '[�亯][�亯]java��?', '��ü������ ����?', 'test', 1, 3, 2, 5);


-- �̹��� : PK(member) -> FK(image) - ȸ������ ��ϵǾ� �ִ� ���̵� ��� ���� - test, admin
INSERT INTO image(no, title, content, id, fileName)
VALUES(image_seq.nextval, '�̹���', '���̹���', 'test', '/upload/image/flower01.jpg');
INSERT INTO image(no, title, content, id, fileName)
VALUES(image_seq.nextval, '������', '�Ϳ������', 'admin', '/upload/image/dog01.jpg');


-- �������� - ���� ��¥ 2021-06-14
-- ���� ���� - ���� ��¥�� �����ϰ� ������ ���̿� �ִ� ������
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '�ڹٰ��� ����', '���������̤�', '2021-04-01', '2021-10-28');
-- ���� ���� - �������� ���� ��¥���� ���� ������
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '�ڹٰ��� ����', '���� jsp����', '2021-05-15', '2021-06-01');
-- ���� ���� - �������� ���� ��¥���� ū ������
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '�ڹٰ��� ����', '������ ����', '2021-10-13', '2021-10-28');

 
-- �Խ���
 -- null : �����Ͱ� ����.
 INSERT INTO board(no, title, content, writer)
 VALUES(board_seq.nextval, 'java', 'java jjang','son');
 INSERT INTO board(no, title, content, writer)
 VALUES(board_seq.nextval, 'oracle', 'oracle jjang','lee');
 INSERT INTO board(no, title, content, writer)
 VALUES(board_seq.nextval, 'web', 'web jjang','kim');
 
 
-- �Խ��� ��� >>>
-- �Խ����� �ִ� �۹�ȣ 2����
-- �Խ��ǿ� 2�� ���� �ְ� �ű⿡ ��� �ۼ�
INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '����Ŭ ���ƿ�', 'lee');
INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '����Ŭ�� ����.', 'lee');
-- �� �߰� ���� ��ȣ�� �� ��ȣ. �� ��ȣ�� �θ�Ű�� �� ��ȣ�� ������ �� ������ ������� �ʴ´�.


INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '����Ŭ �Ⱦ��', 'lee');


INSERT INTO board_reply(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '����Ŭ ���ƿ�', 'lee');
INSERT INTO board_rep(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 5, '����Ŭ�� ����.', 'lee');

 COMMIT;
 
 
 
 
 
  
-- �Խ��� ��� >>>
-- �Խ����� �ִ� �۹�ȣ 2����
-- �Խ��ǿ� 2�� ���� �ְ� �ű⿡ ��� �ۼ�
INSERT INTO board_reply(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 2, '����Ŭ ���ƿ�', 'lee');
INSERT INTO board_reply(rno, no, content, writer)
VALUES(board_rep_seq.nextval, 2, '����Ŭ�� ����.', 'lee');

 COMMIT;
 


--------------


-- ȸ������ � ��Ű��
-- C: ȸ������
-- R: ȸ�� ����Ʈ, ȸ�� ����(�� ���� ����), �α��� , ���̵� ã��, ��� ã��
-- D: ������� �ʴ´�. : ȸ��Ż�� - ȸ�� ���� Ż��� �ٲ��ش�. -> U

--1.����Ʈ - ���̵� , �̸�, ����, ����ó , ��޹�ȣ, ��޸� , ���� ������
select m.id, m.name , m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate
From member m, grade g
where m.gradeNo = g.gradeNo
order by m.id;

select m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate from member m, grade g where m.gradeNo = g.gradeNo order by m.id;

-- 2.����
select  m.id, m.name ,m.gender, m.birth, m.tel, m.email, m.regDate,  m.conDate, m.gradeNo, g.gradeName
from member m, grade g
where (id = 'test')and (m.gradeNo = g.gradeNo);


--3.����(ȸ������)
insert into member(id,pw,name,gender, birth, tel, email, photo)
values ('angel','1111','������','��','1993-03-01','010-0142-2341','iu@naver.com', '/upload/member/angel.jpg');

--4. ����
update member set name ='hong' , gender = '����', birth='1990-07-01', tel = '010-3333-4444', email = 'hong@naver.com'
where id = 'test' and pw = '1111';
commit;

--5. ����


delete from member
where id = 'test' and pw = '1111' and tel = '010-3333-4444';



select * from member;





delete from member where id ='asdf';
commit;

----



select no, title, writer, writeDate, hit from board order by no desc;


insert into board (no, title, writer, writeDate, hit) values(47,����,����,SYSDATE,0);



-- ���� �亯 �Խ���

--list 

select no, title, id, writeDate, hit, refNo, ordNo, levNo, parentNo
from qna order by refNo desc;











------

-- �� �Ʒ��� ����Ʈ�� �ٸ� ����â �ϳ� ���� �����ϸ� ����

select * from qna;



-- ���� �亯 �Խ���

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





-- �� �������� ��� �����͸� ����� ������ - 10��

-- 1������ ���� : 1, �� : 10 ����� �ڹٿ���

-- 1. ���� ��ü ������ ��������. ���� �۹�ȣ ����

select no , title, writer, writeDate, hit
from board
order by no desc;

-- ���� ������ ����� ����
-- ���� �Խ����� �����͸� ������ �ٽ� �Խ����� �����ͷ� �ִ´� x2�� �����Ͱ� �����.

insert into board(no, title, content, writer)
(select board_seq.nextval,title,content,writer from board);

commit;

-- 2.  �߰��� ������ ������ ó���� ���ؼ� ������ȣ�� ���δ�.

select rownum rnum, no, title, writer, writeDate, hit
from
( 
select no , title, writer,writeDate,hit
from board
order by no desc
);

--�������� �´� �����͸� �������� - 1������ rnum�� 1~10���� ������
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



-----


-- ���� �Խ��� ����

select rnum, no, title, id , name, writeDate, hit, refNo, ordNo, levNo, parentNo
from (
select rownum rnum , no, title, id, name, writeDate, hit, refNo, ordNo, levNo, parentNo
from (
select q.no, q.title, q.id, m.name, q.writeDate, q.hit, q.refNo, q.ordNo, q.levNo, q.parentNo
from qna q, member m
where m.id = q.id
order by q.refNo desc, q.ordNo
)
)

where rnum between 1 and 10 --1page

;

----


-- �޽��� ��Ű��(�ڹ�)

-- �޽��� � ����
select * from message;
select * from member;
-- 1. ����Ʈ
--3}  �������� �´� ������ �������� (where)
select  rownum rnum,no, sender, senderName, to_char(sendDate, 'yyyy-mm-dd') sendDate, accepter, accepterName, to_char(acceptDate,'yyyy.mm.dd') acceptDate
from(
--    2] ���� ��ȣ ���̱�(rownum)
    select rownum rnum, no ,sender, senderName, sendDate, accepter, accepterName, acceptDate
    from 
    (
    --1] ���� ������ �ҷ�����
    select m.no, m.sender, sm.name senderName, m.sendDate, m.accepter, am.name accepterName, m.acceptDate
    from message m , member sm, member am
    where (m.sender='test' or m.accepter= 'test' or m.alluser = 1) --�Ϲ�����
    and (m.sender = sm.id and m.accepter = am.id) --z��������
    order by no desc
    )
)

where rnum between 1 and 10;

-- 2. �޽��� ����
-- 2-1.�޽��� ���� ǥ�� ���� - ��ȣ�� ���� �޴� ���� 0�� �����Ϳ� ���ؼ��� ����
update message set acceptDate = sysdate where no = 2 and acceptDate is null;
commit;

-- 2-2 . ������ ��������
select m.no, m.content, m.sender, sm.name senderName, 
    to_char(m.sendDate,'yyyy.mm.dd')sendDate, m.accepter, am.name accepterName, 
    to_char(m.acceptDate)
from message m , member sm, member am
where (no=2) -- �Ϲ�����
and (m.sender = sm.id and m.accepter = am.id); --��������
--where (m.sender='test' or m.accepter= 'test' or m.alluser = 1) --�Ϲ�����
--and (m.sender = sm.id and m.accepter = am.id) --z��������
--order by no desc

-- 3. �޽��� ������ -write
-- ��ȣ (������) , ����(�Է�), ��������� ���̵�(�α��� ����, �޴� ����� ���̵�(�Է�)
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '������Ʈ ������', 'test','admin');

commit;

--4. �޽��� ���� �Ұ��ϴ�. �ֹ氣 ���谡 �����Ƿ� �����Ѵ�.

-- 5. ���� -���� ����� �������, ���� ����� ���� �ʴ� �޽����� ���ؼ��� ���� ����

delete from message
where no = 2 and (accepter = 'test' or (sender = 'test' and acceptDate is null));

commit;



--
