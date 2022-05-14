-- ȸ������ ��Ű��. - member, grade
-- ��ü ���� : FK(member) -> PK(grade)
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE grade CASCADE CONSTRAINTS;
-- ��ü ���� : PK(grade) -> FK(member) -> ���̺� ���Ǽ� ����
CREATE TABLE grade(
gradeNo NUMBER(2) PRIMARY KEY,
gradeName VARCHAR2(20) NOT NULL
);
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
photo VARCHAR2(100) DEFAULT '/upload/member/noImage.jpg',
-- FK(Foriegn Key:����Ű) - grade.gradeNo�� �����Ͱ� ������ �Է� ���� null�� �Է� ����.
-- PK�� �ش�Ǵ� grade ���̺� �����͸� ������� �ϸ� �⺻������ ���� �� ������ ���ǵǾ� �ִ�.
-- on delete �ɼ��� �̿��ؼ� PK�� ������� �ϸ� ��� ������ �ϴ� �� ������ �� �ִ�. cascade, set null
-- 1: �Ϲ�ȸ��, 9: ������
gradeNo NUMBER(2) DEFAULT 1 REFERENCES grade(gradeNo)
);
-- ���� ������ : PK(grade) -> FK(member)
-- ��� �����͸� ���ʴ�� �Է��ϴ� ��� �÷��̸��� ������ �� �ִ�.
INSERT INTO grade VALUES(1, '�Ϲ�ȸ��');
INSERT INTO grade VALUES(9, '������');
-- ������ ���� - ��޹�ȣ�� 9�� �ݵ�� ����� �Ѵ�.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo, gradeNo)
VALUES('admin', '1111', '�̿�ȯ', '����', '1967-01-01', '010-1111-2222', 'leelj1@nate.com','/upload/member/admin.jpg', 9);
-- �Ϲ�ȸ�� ���� - ��޹�ȣ�� �⺻���� 1�� �ȴ�.
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES('test', '1111', 'ȫ�浿', '����', '1994-01-01', '010-3333-4444', 'hong@naver.com','/upload/member/test.jpg');
COMMIT;
drop SEQUENCE member_seq;
create sequence member_seq START WITH 1000; -- ȸ�������� ������ ������
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
(select id || member_seq.nextval, pw, name, gender, birth, tel, email, photo from member);
commit;
select * from grade;
select * from member;