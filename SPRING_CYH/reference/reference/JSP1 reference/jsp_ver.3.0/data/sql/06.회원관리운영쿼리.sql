-- ȸ������ � ��Ű��

-- C - ȸ������
-- R - ȸ������Ʈ, ȸ������(����������), �α���, ���̵�ã��, ��й�ȣ ã��
-- U - ȸ������ ����, ��޼���, ���¼���
-- D - ������� �ʴ´�. : ȸ��Ż�� - ȸ�� ���� Ż��� �ٲ� �ش�. -> U

--+++++++++++++++ ȸ�������� �⺻ CRUD
-- 1. ����Ʈ - ���̵�, �̸�, �������, ����ó, ��޹�ȣ, ��޸�, ����������
SELECT m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate
FROM member m, grade g
WHERE m.gradeNo = g.gradeNo
ORDER BY m.id;
-- 2. ����
SELECT m.id, m.name, m.gender, m.birth, m.tel, m.email, m.regDate, m.conDate, m.status, m.gradeNo, g.gradeName
FROM member m, grade g 
WHERE (id = 'test') AND (m.gradeNo = g.gradeNo);
-- 3. ���� (ȸ������)
INSERT INTO member(id, pw, name, gender, birth, tel, email, photo)
VALUES ('angel', '1111', '������', '����', '1993-03-01', '010-1004-1004',
'angel@naver.com', '/upload/member/angel.jpg');
COMMIT;
-- 4. ���� - �̸�, ����, �������, ����ó, �̸��� - ���̵�, ��й�ȣ : ���� Ȯ�ο�
UPDATE member set name = 'hong', gender = '����', birth = '1990-08-01', tel='010-3333-4444', email = 'hong@naver.com'
WHERE id = 'test' AND pw = '1111';
commit;
-- 5. ����-ȸ��Ż��
DELETE FROM member
WHERE id = 'test' AND pw = '1111' AND tel = '010-3333-4444';
ROLLBACK;

-- 6. �α��� ó�� - ���̵�, ��й�ȣ : ����� �Է�, �������� ������ - ���̵�, �̸�, ��޹�ȣ, ��޸�, ����
SELECT m.id, m.name, m.gradeNo, g.gradeName, m.photo
FROM member m, grade g
WHERE (m.id='test' AND m.pw = '1111') AND (m.gradeNo = g.gradeNo);

select * from member;




