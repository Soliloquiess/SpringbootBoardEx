-- CREATE INDEX �ε����̸� ON ���̺�(�÷� [���Ĺ��], ...)
CREATE INDEX IDX_MEMBER_NAME ON MEMBER (NAME ASC);
CREATE INDEX IDX_MEMBER_BIRTH_TEL ON MEMBER (BIRTH ASC, TEL DESC);

DROP VIEW VIEW_MEMBER_TEL;
-- CREATE OR REPLACE VIEW ���̸� AS SELECT ��
-- CREATE OR REPLACE : ������ CREATE, ������ �����(REPLACE)�� �Ѵ�.
CREATE OR REPLACE VIEW VIEW_MEMBER_TEL
AS SELECT 
    id, name, tel
FROM 
    member;

-- ���ο� �޽��� => ���� �ʴ� �޽��� => ���� ��¥ = null
-- count(*) - �������� ������ ���� �Լ�
select count(*) cnt from message where accepterId = 'test' and acceptDate is null;
select * from message;
