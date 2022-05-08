use aloha;

delete from board;


select * from aloha.board order by reg_date desc;


select *
from aloha.board
order by group_no desc;


select * 
from aloha.board
order by group_no desc
	, seq_no ASC
	, depth_no ASC
	, reg_date DESC;