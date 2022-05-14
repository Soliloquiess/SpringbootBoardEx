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
-- 3. write : 글번호 - seq, 사용자 : 제목, 내용, 이미지 파일(서버에 있는 파일), 시스템(session) : 아이디
INSERT INTO image(no, title, content, id, fileName)
VALUES (image_seq.nextval, '이미지','이미지', 'test', '/upload/image/flower01.jpg');
COMMIT;

-- 4. update(정보 수정), 이미지 작성자만 수정이 가능하다.
UPDATE image
SET title = 'image', content = 'image입니다.'
WHERE no = 3 AND id = 'test';
COMMIT;
-- 4-2 update 파일 - 이미지 번호, 파일명
UPDATE image
SET fileName = '/upload/image/flower02.jpg'
WHERE no =3;

-- 5. delete
DELETE FROM image
WHERE no = 3;
ROLLBACK