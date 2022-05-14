-- 1. list
SELECT i.no, i.title, m.name, i.id ,
 to_char(i.writeDate, 'yyyy-mm-dd') writeDate, i.fileName 
FROM image i , member m
WHERE m.id = i.id
ORDER BY no DESC;

select * from image;
--- 2. view
SELECT i.no, i.title, i.content, m.name, i.id ,
 to_char(i.writeDate, 'yyyy-mm-dd') writeDate, i.fileName 
FROM image i , member m
WHERE (no = 3) and (m.id = i.id);
-- 3. write : �۹�ȣ - seq, ����� : ����, ����, �̹��� ����(������ �ִ� ����), �ý���(session) : ���̵�
INSERT INTO image(no, title, content, id, fileName)
VALUES (image_seq.nextval, '�̹���','�̹���', 'test', '/upload/image/flower01.jpg');
COMMIT;

-- 4. update(���� ����), �̹��� �ۼ��ڸ� ������ �����ϴ�.
UPDATE image
SET title = 'image', content = 'image�Դϴ�.'
WHERE no = 3 AND id = 'test';
COMMIT;
-- 4-2 update ���� - �̹��� ��ȣ, ���ϸ�
UPDATE image
SET fileName = '/upload/image/flower02.jpg'
WHERE no =3;

-- 5. delete
DELETE FROM image
WHERE no = 3;
ROLLBACK