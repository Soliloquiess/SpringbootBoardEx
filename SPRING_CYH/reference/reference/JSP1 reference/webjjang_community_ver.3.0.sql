-- <<< ��¯ Ŀ�´�Ƽ ��ü ��Ű�� >>> --
-- �ۼ��� : �̿�ȯ
-- �ۼ��� : 2021. 03. 30
-- ��  �� : 3.0 - �Խ��� ��й�ȣ �ý���

--- [1] ��ü ����
------ [1-1] ���̺� ����(FK -> PK ��)
DROP TABLE board_reply CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE notice CASCADE CONSTRAINTS;
DROP TABLE image CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE message CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;

------ [1-2] ������ ����(������ �������)
DROP SEQUENCE board_reply_seq;
DROP SEQUENCE board_seq;
DROP SEQUENCE image_seq;
DROP SEQUENCE message_seq;
DROP SEQUENCE notice_seq;
DROP SEQUENCE qna_seq;



--- [2] ��ü ����(PK -> FK ��)
------ [2-1] ������̺� �����
CREATE TABLE grade(
   gradeNo NUMBER(2) CONSTRAINT grade_gradeNo_pk PRIMARY KEY,
   gradeName VARCHAR2(20)
);

------ [2-2] ȸ�����̺� �����
CREATE TABLE member(
id VARCHAR2(20) CONSTRAINT member_id_pk PRIMARY KEY, -- ���̵�
pw VARCHAR2(20) CONSTRAINT member_pw_nn NOT NULL, -- ��й�ȣ
name VARCHAR2(30) CONSTRAINT member_name_nn NOT NULL, -- �̸�
gender VARCHAR2(6) CONSTRAINT member_gender_nn NOT NULL
       CONSTRAINT member_gender_ck CHECK(gender in('����','����')), -- ����
birth date CONSTRAINT member_birth_nn NOT NULL, -- �������
tel VARCHAR2(13), -- ����ó
email VARCHAR2(50) CONSTRAINT member_email_nn NOT NULL, -- �̸���
regDate date DEFAULT sysdate, -- ������
conDate date DEFAULT sysdate, -- �ֱ� ������
status VARCHAR2(6) DEFAULT '����' 
       CONSTRAINT member_status_ck CHECK(status in ('����','����','Ż��','�޸�')), -- ����
gradeNo NUMBER(2)  DEFAULT 1 CONSTRAINT member_gradeNo_fk REFERENCES grade(gradeNo) -- ���
);

------ [2-3] �޽��� ���̺� �����
CREATE TABLE message(
no NUMBER PRIMARY KEY, -- �޽��� ��ȣ
content VARCHAR2(2000) NOT NULL, -- ����
-- ȸ���� ������� �޽����� �������. :  REFERENCES member(id) ON DELETE CASCADE
sender VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE , -- ������ ����� ID (FK-�����ϰ� �ִ� PK�� Ÿ�԰� ũ�⿡ �����.)
sendDate DATE DEFAULT SYSDATE, -- ���� ��¥ : ���糯¥�� �⺻
accepter VARCHAR2(20) NOT NULL REFERENCES member(id) ON DELETE CASCADE, -- �޴� ����� ID
acceptDate DATE DEFAULT NULL -- ���� ��¥ : ���糯¥- �⺻�� null
-- -> �޾Ҵ�:��¥�� �ִ�. ���� �ʾҴ�. ��¥�� ����.
);

------ [2-4] ���� �亯 ���̺� �����
CREATE TABLE qna(
no number PRIMARY KEY, -- �۹�ȣ
title VARCHAR2(300) NOT NULL, -- ����
content VARCHAR2(2000) NOT NULL, -- ����
id VARCHAR2(20) NOT NULL 
    REFERENCES member(id), -- �ۼ��� ���̵�(�α��� �������� �ڵ����� ������)
writeDate date DEFAULT sysdate, -- �ۼ���
hit NUMBER DEFAULT 0, -- ��ȸ��
refNo NUMBER REFERENCES qna(no), -- ���ñ�
ordNo NUMBER, -- ������ȣ
levNo NUMBER, -- �鿩����
-- REFERENCES qna(no)�� ����Ű�� ����� �θ� �����Ͱ� �����Ǹ� ������ ��� ó���� ���ΰ� ���� �� �ִ�.
-- ON DELETE CASCADE : ���۵� �Բ� �����.
-- ON DELETE SET NULL : ������ ������ ���� ����Ű�� �Ǿ� �ִ� �÷��� null�� ������ �ּ���.
parentNo NUMBER REFERENCES qna(no) ON DELETE CASCADE -- �θ��(���� �ִ� ��)
);

------ [2-5] �̹��� ���̺� �����
CREATE TABLE image(
no NUMBER CONSTRAINT image_no_pk PRIMARY KEY, -- �۹�ȣ
title VARCHAR2(300) NOT NULL, -- ����
content VARCHAR2(2000) NOT NULL, -- ����
id VARCHAR2(20) NOT NULL REFERENCES member(id), -- �ۼ��� ���̵� -> �α����� ���� �Ǿ�߸� �Ѵ�.
writeDate DATE DEFAULT SYSDATE, -- �ۼ���
fileName VARCHAR2(50) NOT NULL -- ÷�ε� �̹��� ���ϸ�
);

------ [2-6] �������� ���̺� �����
CREATE TABLE notice(
no NUMBER PRIMARY KEY, -- ������ȣ
title VARCHAR2(300) NOT NULL, -- ��������
content VARCHAR2(2000) NOT NULL, -- ���� ����
startDate date DEFAULT SYSDATE, -- ���� ������
endDate date DEFAULT '9999-12-30', -- ���� ������
writeDate date DEFAULT SYSDATE, --���� �ۼ���
updateDate date DEFAULT SYSDATE -- ���� ������
);

------ [2-7] �ϹݰԽ��� ���̺� �����
CREATE TABLE board(
no NUMBER PRIMARY KEY, -- �۹�ȣ
title VARCHAR2(300) NOT NULL, -- ����
content VARCHAR2(2000) NOT NULL, -- ����
writer VARCHAR2(30) NOT NULL, -- �ۼ���
-- SYSDATE : ����Ŭ������ ���� ��¥�� �ǹ��ϴ� Ű����
writeDate DATE DEFAULT SYSDATE, -- DEFAULT ���� ���õǾ� ������ NOT NULL�� ������� �ʴ´�.(�ǹ̾���)
hit NUMBER DEFAULT 0, -- ��ȸ��
pw VARCHAR2(20)
);

------ [2-8] �ϹݰԽ��� ��� ���̺� �����
CREATE TABLE board_reply
(
  rno NUMBER CONSTRAINT board_reply_rno_pk PRIMARY KEY, --��� ��ȣ
  no NUMBER CONSTRAINT board_reply_no_fk REFERENCES board(no) ON DELETE CASCADE, --�۹�ȣ
  content VARCHAR2(600) NOT NULL, -- ����
  writer VARCHAR2(30) NOT NULL, -- �ۼ���
  writeDate DATE DEFAULT SYSDATE -- �ۼ���
);


------ [2-2] ������ �����
CREATE SEQUENCE board_reply_seq;
CREATE SEQUENCE board_seq;
CREATE SEQUENCE image_seq;
CREATE SEQUENCE message_seq;
CREATE SEQUENCE notice_seq;
CREATE SEQUENCE qna_seq;



--- [3] ���õ����� �ֱ�(PK -> FK �� : �ִ� �����͸� �Է°����Ѵ�.)
------ [3-1] ȸ�� ��� 
INSERT INTO grade VALUES (1, '�Ϲ�ȸ��');
INSERT INTO grade VALUES (9, '������');

------ [3-2] ȸ��
-- ������ �����Է��ϱ�
INSERT INTO member(id, pw, name, gender, birth, email, gradeNo)
VALUES('admin','1111', '�̿�ȯ','����','1967-01-01','leelj1@nate.com', 9);
-- �Ϲ�ȸ�� �����Է��ϱ� - ȸ�������� �Ҷ� ��޹�ȣ�� ���������� �ʴ´�. �⺻���� 1(�Ϲ�ȸ��)�� �����ؼ� ����Ѵ�.
INSERT INTO member(id, pw, name, gender, birth, email)
VALUES('test','1111', 'ȫ�浿','����','1980-01-01','leelj1@nate.com');
INSERT INTO member(id, pw, name, gender, birth, email)
VALUES('angel','1111', '������','����','1975-01-01','angel@naver.com');

------ [3-3] �޽���
-- test -> admin
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '��������?', 'test', 'admin');
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '�������Դϴ�.?', 'test', 'admin');
commit;
-- admin -> test
insert into message(no, content, sender, accepter)
values(message_seq.nextval, '�������� �ֽ��ϴ�. �����մϴ�.', 'admin', 'test');
-- admin -> angel
insert into message(no, content, sender, accepter)
values(message_seq.nextval, 'ȯ���մϴ�. �� ���� ���ƿ�~~~.', 'admin', 'angel');

------ [3-4] ���� �亯
-- 1) �����ϱ� - 2�� �̻�
--   - ���ñ۹�ȣ�� �ڽ� �۹�ȣ�� ����. --> �����̴�.
--   - ������ 1���̴�.
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '�ڹٶ�?','�ڹٶ� �����ϱ��?', 'test',
       qna_seq.nextval, 1, 0, qna_seq.nextval);
INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '����Ŭ�̶�?','����Ŭ�̶� �����ϱ��?', 'test',
       qna_seq.nextval, 1, 0, qna_seq.nextval);
       
-- 2) �亯�ϱ� - �Ѱ��� ������ 2���̻��� �亯
-- '�ڹٶ�?'�� ���� �亯 : �۹�ȣ 1, refNo : 1, ordNo : 1, levNo:0
-- ���� �޸� �亯�� ���� �� �ִ�. ���� �޸� �亯�� ������ȣ(�Է��Ϸ��� ���� ��ȣ���� ���ų� ū)�� ��ü + 1 ����� �Ѵ�.
-- �ڹٶ�? ù��° �亯
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[�亯]�ڹٶ�?','��ü������ ���α׷��� ���', 'admin',
       1, 2, 1, 1); -- refNo:�θ��� refNo�� �״�� �ִ´�. ordNo = ordNo + 1. levNo = levNo + 1. �θ���� no
       
-- �ڹٶ�? �ι�° �亯
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[�亯]�ڹٶ�?','ȣȯ���� ����.', 'admin',
       1, 2, 1, 1); -- refNo:�θ��� refNo�� �״�� �ִ´�. ordNo = ordNo + 1. levNo = levNo + 1. �θ���� no

-- '����Ŭ�̶�?' �亯 : �۹�ȣ 2, refNo : 2, ordNo : 1, levNo:0
-- ���� �޸� �亯�� ���� �� �ִ�. ���� �޸� �亯�� ������ȣ(�Է��Ϸ��� ���� ��ȣ���� ���ų� ū)�� ��ü + 1 ����� �Ѵ�.
-- ����Ŭ�̶�? ù��° �亯
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 2 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[�亯]����Ŭ�̶�?','DBMS �Դϴ�.', 'admin',
       2, 2, 1, 2); -- refNo:�θ��� refNo�� �״�� �ִ´�. ordNo = ordNo + 1. levNo = levNo + 1. �θ���� no

-- ����Ŭ�̶� �ι�° �亯
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 2 and ordNo >= 2;

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[�亯]����Ŭ�̶�?','����Ŭ�翡�� ����.', 'admin',
       2, 2, 1, 2); -- refNo:�θ��� refNo�� �״�� �ִ´�. ordNo = ordNo + 1. levNo = levNo + 1. �θ���� no


-- 3) �亯>�亯�ϱ�
-- '[�亯]�ڹٶ�?' - 4 : �۹�ȣ 4, refNo : 1, ordNo : 2, levNo:1
-- ���� �޸� �亯�� ���� �� �ִ�. ���� �޸� �亯�� ������ȣ(�Է��Ϸ��� ���� ��ȣ���� ���ų� ū)�� ��ü + 1 ����� �Ѵ�.
UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 3; -- �����Ͱ� ������ ���� ��ȣ�� ���ų� ū ������ȣ�� 1����.

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[�亯][�亯]�ڹٶ�?','��Ȱ�뼺�� ������ ���ϴ�.', 'admin',
       1, 3, 2, 4); -- refNo:�θ��� refNo�� �״�� �ִ´�. ordNo = ordNo + 1. levNo = levNo + 1. �θ���� no

UPDATE qna
SET ordNo = ordNo + 1
WHERE refNo = 1 and ordNo >= 3; -- �����Ͱ� ������ ���� ��ȣ�� ���ų� ū ������ȣ�� 1����.

INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo)
values(qna_seq.nextval, '[�亯][�亯]�ڹٶ�?','�ߺ��� �Ͱ� ������ ���� ������..', 'admin',
       1, 3, 2, 4); -- refNo:�θ��� refNo�� �״�� �ִ´�. ordNo = ordNo + 1. levNo = levNo + 1. �θ���� no

------ [3-5] �̹���
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '������', '�Ϳ��� ������','test','/upload/image/m_dog01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '�����', '�߿˾߿� �����','admin','/upload/image/m_cat01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '�̻� �� ����', '�̻� �� �����Դϴ�.','admin','/upload/image/m_flower01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '�Ϳ��� ������', '�Ϳ��� �������Դϴ�.','admin','/upload/image/m_cactus01.jpg');
INSERT INTO image(no, title, content, id, fileName)
values(image_seq.nextval, '�� �ٱ��� - ���Ÿ��� �ɹٱ��� �̹����� �ø��ϴ�. ���� ���̳׿�.', '�� �ٱ����Դϴ�.','angel','/upload/image/m_flower04.jpg');

------ [3-6] ��������
-- ���� ��¥�� �ð� : 2021-03-30 12:06
-- ���� ������ �Է�
-- ���� ���� - ������ ���� ��¥���� �۴�. �������� ���� ��¥���� ũ��.
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '�ڹ� ������ ������ ���� �������� ���� ����� �����մϴ�.', '�ڹٷ� �ַ���� �����ϴ� ����',
'2020-12-10', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '���� ������ �˸°�', '������ ����� Ȱ��',
'2020-12-10', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '������Ʈ ����', 'JSP-Servlet / Spring Project',
'2021-03-10', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '������Ʈ ��ǥ - 2���� ������Ʈ�� �����ϸ� ������ �����ϰ� �˴ϴ�. ���� �����ؾ� �մϴ�.', '������Ʈ ����ȭ �۾� �� ����',
'2021-03-15', '2021-05-28');
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '��� ���� ����', 'ť�˷� �� ��������. �Խ�/���',
'2020-12-10', '2021-05-28');
-- ���� ���� - �������� ���� ��¥���� �۴�.
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '�ڹ� ������ ���� ����', '�ڹٷ� �ַ���� �����ϴ� ����',
'2020-11-10', '2020-12-16');
-- ���� ���� - �������� ���� ��¥���� ũ��.
insert into notice(no, title, content, startDate, endDate)
values (notice_seq.nextval, '�ڹ� ������ ���� ����', '���Ḧ �����մϴ�.',
'2021-05-22', '2021-05-29');

------ [3-7] �Ϲ� �Խ���
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'java�� ���� ���ؿ� �� ������ ���� ����', 
'java jjang', '�̿�ȯ', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'oracle', 'oracle jjang', '��ȫ��', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'web', 'web jjang', 'ȫ�浿', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'jsp', 'jsp jjang', '������', '1111');
insert into board(no, title, content, writer, pw)
values(board_seq.nextval, 'spring�� �ڹ� ������ ��ũ�� ȿ������ ������ ���� �ڹٷ� Ʋ�� ����� ���� ���Դϴ�.',
'spring jjang', '�����', '1111');


------ [3-8] �Ϲ� �Խ��� ���
insert into board_reply(rno, no, content, writer)
values (board_reply_seq.nextval, 2, '�����մϴ�.','�̿�ȯ');
insert into board_reply(rno, no, content, writer)
values (board_reply_seq.nextval, 2, '����Ŭ�� ����� ����.','ȫ�浿');
insert into board_reply(rno, no, content, writer)
values (board_reply_seq.nextval, 2, '�౸���� �����.','�����');


COMMIT;


