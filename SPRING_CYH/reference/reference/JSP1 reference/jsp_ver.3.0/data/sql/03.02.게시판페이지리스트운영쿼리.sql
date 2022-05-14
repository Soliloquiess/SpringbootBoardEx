-- 한페이지에 몇 개의 데이터를 출력할 것인지? -> 10개
-- 1페이지 시작 : 1, 끝 : 10 ==> 계산은 자바에서
-- 1. 원본 전체 데이터 가져오기 -> 정렬 글번호 역순
select no, title, writer, writeDate, hit
from board
order by no desc;

-- 많은 데이터 만들기 쿼리
-- 원래 게시판의 데이터를 꺼내서 다시 게시판의 데이터로 넣는다. X 2 개의 데이터가 생긴다.
insert into board(no, title, content, writer)
(select board_seq.nextval, title, content, writer from board);
commit;

-- 2. 중간의 없어진 데이터 처리를 위해서 순서번호를 붙인다. rownum
select rownum rnum, no, title, writer, writeDate, hit
from(
    select no, title, writer, writeDate, hit
    from board
    order by no desc
);

-- 페이지에 맞는 데이터를 가져오기 . 1페이지 rnum이 1~10까지 데이터
select rnum, no, title, writer, writeDate, hit
from(
    select rownum rnum, no, title, writer, writeDate, hit
    from(
        select no, title, writer, to_char(writeDate, 'yyyy-mm-dd') writeDate, hit
        from board
        order by no desc
    )
)
where rnum between 1 and 10;

select count(*) from board;