-- ���������� �� ���� �����͸� ����� ������? -> 10��
-- 1������ ���� : 1, �� : 10 ==> ����� �ڹٿ���
-- 1. ���� ��ü ������ �������� -> ���� �۹�ȣ ����
select no, title, writer, writeDate, hit
from board
order by no desc;

-- ���� ������ ����� ����
-- ���� �Խ����� �����͸� ������ �ٽ� �Խ����� �����ͷ� �ִ´�. X 2 ���� �����Ͱ� �����.
insert into board(no, title, content, writer)
(select board_seq.nextval, title, content, writer from board);
commit;

-- 2. �߰��� ������ ������ ó���� ���ؼ� ������ȣ�� ���δ�. rownum
select rownum rnum, no, title, writer, writeDate, hit
from(
    select no, title, writer, writeDate, hit
    from board
    order by no desc
);

-- �������� �´� �����͸� �������� . 1������ rnum�� 1~10���� ������
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