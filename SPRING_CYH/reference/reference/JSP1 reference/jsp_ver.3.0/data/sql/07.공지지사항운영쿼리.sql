-- ���� ���� ����Ʈ - ���� ��¥(SYSDATE)�� �����ϰ� �������� ���̿� �ִ�.
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE)
ORDER BY updateDate DESC;

-- view table�� �̿��غ���.
CREATE OR REPLACE VIEW notice_now
AS
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE startDate <= SYSDATE AND endDate >= TRUNC(SYSDATE)
ORDER BY updateDate DESC;

select * from notice_now;

select no, title, startDate, endDate, updateDate from notice_now;

-- ���� ���� ����Ʈ - ���� ��¥(SYSDATE)�� �����Ϻ��� ũ��.(�������� ��������.)
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE endDate < TRUNC(SYSDATE)
ORDER BY updateDate DESC;

-- ���� ���� ����Ʈ - ���� ��¥(SYSDATE)�� �����Ϻ��� �۴�.(�������� ���� �ʾҴ�.)
SELECT no, title,
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
WHERE startDate > SYSDATE
ORDER BY updateDate DESC;

-- ��ü ���� - ������ ����.
SELECT no, title, 
  to_char(startDate, 'yyyy-mm-dd') startDate,
  to_char(endDate, 'yyyy-mm-dd') endDate,
  to_char(updateDate, 'yyyy-mm-dd') updateDate
FROM notice
-- 1�� ������ updateDate�ϰ� �Ȱ��� �����Ͱ� �ִ� ��� �׾ȿ��� no 2�� ���Ľ�Ų��.
ORDER BY updateDate DESC, no DESC;


select  to_char(TRUNC(SYSDATE), 'yyyy-mm-dd hh24 : mi : ss') from dual;

select sysdate from dual;
select to_char(sysdate, 'yyyy-mm-dd') now from dual;

-- 2. ���� -- JSP -> list.jsp ���� no �� ������ �´�. inc�� ��ȸ���� ��� �ʿ䰡 ����.
SELECT no, title, content, startDate, endDate, updateDate
FROM notice
WHERE no = 2;
-- 3. ���� -- JSP -> list.jsp ���� �´�. - title, content, startDate, endDate �Է�
INSERT INTO notice(no, title, content, startDate, endDate)
VALUES(notice_seq.nextval, '���������Դϴ�.', '�˸��ϴ�.', '2021-12-28', '2022-01-01');
COMMIT;
-- 4. ���� -- JSP -> view.jsp ���� �۹�ȣ �����ؼ� �´�. - title, content, startDate, endDate �Է�
UPDATE notice
SET title = '���� ����', content = '���� �����Դϴ�.', startDate = '2021-12-28', endDate = '2022-01-01'
WHERE no = 4;
COMMIT;
-- 5. ���� -- JSP - view.jsp���� �۹�ȣ�� �����ؼ� �´�. 
DELETE FROM notice
WHERE no = 4;

ROLLBACK;

