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






