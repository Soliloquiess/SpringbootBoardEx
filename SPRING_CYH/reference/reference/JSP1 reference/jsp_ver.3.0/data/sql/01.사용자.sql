-- ����� ��ü ����� (DBMS - DB)
-- ����� ��ü�� ����� ������ ������ ��� �ƹ��ϵ� ���Ѵ�.
CREATE USER java01 IDENTIFIED BY java01;

-- ������� ����ڿ��� ����, �۾� ������ �ش�. view table
GRANT CONNECT, RESOURCE, create view TO java01;

-- ��ü ����� CREATE <-> DROP
DROP USER java00 CASCADE;