-- ��ü ����
DROP TABLE notice CASCADE CONSTRAINTS;
DROP SEQUENCE notice_seq;

-- ��ü ����
CREATE TABLE notice(
  no NUMBER PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content VARCHAR2(2000) NOT NULL,
  startDate DATE DEFAULT SYSDATE,
  endDate DATE DEFAULT '9999-12-30',
  writeDate DATE DEFAULT SYSDATE,
  updateDate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE notice_seq;

-- ���� ������ - ���� ��¥ : 2021.12.27
-- ���� ���� - 2021�� ��ź�� �̺�Ʈ - 2021.11.25 ~ 2021.12.25
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '2021�� ��ź�� �̺�Ʈ', '2021�� ��ź�� �̺�Ʈ- ���� ���� ���ϴ�.', '2021-11-25', '2021-12-25');
-- ���� ���� - 2022�� ���� �޽��� �̺�Ʈ - 2021.12.01~2022.01.07 -> ��� ����ڰ� �� �� �ִ�.
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '2022�� ���� �޽��� �̺�Ʈ', '2022�� ���� �޽��� �̺�Ʈ- ���ο��� �޼�����', '2021-12-01', '2022-01-07');
-- ���� ���� - 2022�� ���� - 2022.03.15~2022.03.30
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '2022�� ����', '2022�� ���� - ���ϵ帳�ϴ�. �����ϼ̽��ϴ�.', '2022-03-15', '2022-03-30');

COMMIT;

SELECT * FROM notice;