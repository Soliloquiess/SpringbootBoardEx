--- �ּ� ��Ű�� - ���� �� �����Ͱ� �����ؾ� �ϵ��� �����Ѵ�. FK(REFERENCES Ű���� �̿�)
-- 1. ��ü ����
DROP TABLE address CASCADE CONSTRAINTS;
DROP SEQUENCE address_seq;

-- 2. ��ü ����
CREATE TABLE address(
  seq_id number PRIMARY KEY,
  address1 VARCHAR2(100) NOT NULL,
  address2 VARCHAR2(100) NOT NULL,
  address3 VARCHAR2(100) NOT NULL,
  postal_code VARCHAR2(7)  NOT NULL,
  client_id NUMBER NOT NULL REFERENCES client(client_id)
);

CREATE SEQUENCE address_seq;

-- 3. ���� ������ �ֱ�
INSERT INTO address(seq_id, address1, address2, address3, postal_code, client_id)
VALUES (address_seq.nextval, '�����ν�', '�����ε�', '������ǻ�� �п�', '11-222', 1);

-- 4. commit
COMMIT;

-- 5. ������ Ȯ��
select * from address;