-- 회원관리 운영 스키마
-- C: 회원가입
-- R: 회원 리스트, 회원 보기(내 정보 보기), 로그인 , 아이디 찾기, 비번 찾기
-- D: 사용하지 않는다. : 회원탈퇴 - 회원 상태 탈퇴로 바꿔준다. -> U

--1.리스트 - 아이디 , 이름, 생년, 연락처 , 등급번호, 등급명 , 최종 접속일
select m.id, m.name , m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate
From member m, grade g
where m.gradeNo = g.gradeNo
order by m.id;

select m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, m.conDate from member m, grade g where m.gradeNo = g.gradeNo order by m.id;

-- 2.보기
select  m.id, m.name ,m.gender, m.birth, m.tel, m.email, m.regDate,  m.conDate, m.gradeNo, g.gradeName
from member m, grade g
where (id = 'test')and (m.gradeNo = g.gradeNo);


--3.쓰기(회원가입)
insert into member(id,pw,name,gender, birth, tel, email, photo)
values ('angel','1111','아이유','여','1993-03-01','010-0142-2341','iu@naver.com', '/upload/member/angel.jpg');

--4. 수정
update member set name ='hong' , gender = '남자', birth='1990-07-01', tel = '010-3333-4444', email = 'hong@naver.com'
where id = 'test' and pw = '1111';
commit;

--5. 삭제


delete from member
where id = 'test' and pw = '1111' and tel = '010-3333-4444';



select * from member;






