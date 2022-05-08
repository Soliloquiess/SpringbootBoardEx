use aloha;
select count(*)
from board;

-- 실제 행번호(글번호가 아닌)
select @rownum := @rownum+1 row_no, b.*
from 
-- 어노테이션 붙이면 쿼리에서 변수선언 가능
(select @rownum:= #{startRowIndex} from dual) 
tmp,
board b
order by board_no desc
, reg_date desc
limit #{startRowIndex}, #{rowsPerPage}
;


select @rownum := @rownum+1 row_no, b.*
from 
-- 어노테이션 붙이면 쿼리에서 변수선언 가능
(select @rownum:= #{startRowIndex} from dual) 
tmp,
board b
order by board_no desc
, reg_date desc
limit 0,10;

