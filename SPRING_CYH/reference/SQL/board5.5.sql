use aloha;

select * from board;


select * 
from board b
, board_reply r
where b.board_no = r.board_no;


select *,
(select count(*) from board_reply group by board_no)
from board b
, board_reply r
where b.board_no = r.board_no;

select board_no, count(*) reply_count from board_reply group by board_no;

select b.* ,r.reply_count
from board b left join
(select board_no, count(*) reply_count from board_reply group by board_no) r
on b.board_no = r.board_no;

select b.* ,r.reply_count
from board b,
(select board_no, count(*) reply_count from board_reply group by board_no) r
where b.board_no = r.board_no;

게시글 목록 조회하는데 그때 집합이 어케 되냐면 쿼리 작성을 이거에 대한 집합과 비교해보자.

delete from board where board_no =1476; 