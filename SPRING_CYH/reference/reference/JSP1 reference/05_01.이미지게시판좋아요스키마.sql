-- 이미지 좋아요 스키마
-- 회원만 가능 - 한개의 이미지글에 대해 한번만 가능하고 좋아요 <-> 좋아요 취소
-- 좋아요함 : 아이디와 이미지 번호 데이터가 있다.
-- 좋아요안함 : 아이디와 이미지 번호 데이터가 없다.

-- 정보 저장 테이블 : 아이디(FK-member(id)), 이미지 번호(FK-image(no))
DROP TABLE image_like CASCADE CONSTRAINTS;
CREATE TABLE image_like(
    id VARCHAR2(20) CONSTRAINT image_like_id_fk REFERENCES member(id) ON DELETE CASCADE,
    no NUMBER CONSTRAINT image_like_no_fk REFERENCES image(no) ON DELETE CASCADE
);

-- id와 no를 합쳐서 PK로 복합키 지정한다. table 수정 - 복합키 지정
ALTER TABLE image_like
ADD CONSTRAINT image_like_id_no_pk PRIMARY KEY(id, no);

---- 좋아요 테이블에 데이터가 있으면 좋아요를 눌렀다. 데이터가 없으면 좋아요를 누르지 않았다.
--- test가 2번 이미지 글에 좋아요 눌려서 처리
INSERT INTO image_like
VALUES('test', 2);
COMMIT;

select no, 'LIKED' 
from image_like
where no = 2 and id = 'test';

--- test가 2번 이미지 글에 좋아요 취소 처리
delete from image_like
where no = 2 and id = 'test';
COMMIT;

