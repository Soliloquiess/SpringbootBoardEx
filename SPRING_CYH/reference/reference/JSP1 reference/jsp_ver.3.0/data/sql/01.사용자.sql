-- 사용자 객체 만들기 (DBMS - DB)
-- 사용자 객체는 만들어 지지만 권한이 없어서 아무일도 못한다.
CREATE USER java01 IDENTIFIED BY java01;

-- 만들어진 사용자에게 접속, 작업 권한을 준다. view table
GRANT CONNECT, RESOURCE, create view TO java01;

-- 객체 만들기 CREATE <-> DROP
DROP USER java00 CASCADE;