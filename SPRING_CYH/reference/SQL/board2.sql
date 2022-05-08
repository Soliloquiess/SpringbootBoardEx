use blog;
select *
from board 
-- where title like concat('%', #{keyword},'%')
where title like concat('%', #{keyword},'%')
	or content like concat('%', #{keyword},'%')
    or writer like concat('%', #{keyword},'%');
    
    
select *
from board
where reg_date >= date(now())-30;


select *
from board
where reg_date >= date(subdate(now(), interval 7 day));

-- 1 페이지
select *
from board
limit 0,10; -- index(순서번호), count(개수)

-- 2 페이지
select *
from board
limit 10,10; -- index(순서번호), count(개수)

-- 3 페이지
select *
from board
limit 20,10; -- index(순서번호), count(개수)


-- 일반적인 페이지
select *
from board
limit #{startRowIndex}, #{rowsPerPage};


