-- ��Ű��(Schema):�������� ���� - CREATE TABLE
-- ���̺� ��ü �����(CREATE)�� ����� : DDL(Data Definition Language)
-- �Խ��� ������ ���� : �۹�ȣ, ����, ����, �ۼ���, �ۼ���, ��ȸ��
-- �÷� : ������ �׸�. �ݵ�� �������� Ÿ�԰� ũ�Ⱑ �����Ǿ�߸� �Ѵ�. - ���������� SQL
-- PK - �ߺ� �Ұ�, ����Ѽ� ����.
CREATE TABLE board(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(300),
  content VARCHAR2(2000),
  writer VARCHAR2(30),
  writeDate DATE,
  hit NUMBER
);

-- ���̺� ��ü ����� (CREATE <-> DROP)
DROP TABLE board CASCADE CONSTRAINTS;

-- �۹�ȣ�� 1~~~ 1�� �����Ǵ� ���ڸ� ����ϰ� �ȴ�. �ڵ�������Ű�� ��ü -> ������ ���:����Ŭ������ ����
CREATE SEQUENCE board_seq;
-- ������ ��ü �����
DROP SEQUENCE board_seq;

--[������ ó��] -------------------------------------------
-- ������ ó�� ��ɾ� : DML(Data Manipulation Language) - ������ ���۾�
-- INSERT : ������ �ֱ�, UPDATE : ������ ����, DELETE : ������ ����
-- ������ ��� : ������.NEXTVAL - �������� ������ȣ �ޱ�
-- ���� ��¥�� �ð� : SYSDATE, ���ڿ� : '���ڿ�'
INSERT INTO board(no, title, content, writer, writeDate, hit)
VALUES(board_seq.NEXTVAL, 'java ������', '����� ���� �ؾ��Ѵ�.', '�̿�ȯ', SYSDATE, 0);

-- �۾� ���� ���� - COMMIT / �۾� ���� ��� - ROLLBACK - Ʈ������ : DTL
ROLLBACK;
COMMIT;

-- ������ Ȯ�� : SELECT - Query : SQL
SELECT * FROM board;

-- no�� 3���� ���� ������ �� �����ڷ� �ٲ���.
UPDATE board SET title = '�� ������' WHERE no = 3;
COMMIT;

-- no�� 3���� ���� ������ ����.
DELETE FROM board WHERE no = 3;
COMMIT;