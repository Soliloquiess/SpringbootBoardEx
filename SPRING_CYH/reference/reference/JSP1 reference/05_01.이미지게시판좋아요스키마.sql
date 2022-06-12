-- �̹��� ���ƿ� ��Ű��
-- ȸ���� ���� - �Ѱ��� �̹����ۿ� ���� �ѹ��� �����ϰ� ���ƿ� <-> ���ƿ� ���
-- ���ƿ��� : ���̵�� �̹��� ��ȣ �����Ͱ� �ִ�.
-- ���ƿ���� : ���̵�� �̹��� ��ȣ �����Ͱ� ����.

-- ���� ���� ���̺� : ���̵�(FK-member(id)), �̹��� ��ȣ(FK-image(no))
DROP TABLE image_like CASCADE CONSTRAINTS;
CREATE TABLE image_like(
    id VARCHAR2(20) CONSTRAINT image_like_id_fk REFERENCES member(id) ON DELETE CASCADE,
    no NUMBER CONSTRAINT image_like_no_fk REFERENCES image(no) ON DELETE CASCADE
);

-- id�� no�� ���ļ� PK�� ����Ű �����Ѵ�. table ���� - ����Ű ����
ALTER TABLE image_like
ADD CONSTRAINT image_like_id_no_pk PRIMARY KEY(id, no);

---- ���ƿ� ���̺� �����Ͱ� ������ ���ƿ並 ������. �����Ͱ� ������ ���ƿ並 ������ �ʾҴ�.
--- test�� 2�� �̹��� �ۿ� ���ƿ� ������ ó��
INSERT INTO image_like
VALUES('test', 2);
COMMIT;

select no, 'LIKED' 
from image_like
where no = 2 and id = 'test';

--- test�� 2�� �̹��� �ۿ� ���ƿ� ��� ó��
delete from image_like
where no = 2 and id = 'test';
COMMIT;

