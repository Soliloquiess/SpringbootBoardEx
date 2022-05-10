package com.print;

import java.util.List;

import com.board.vo.BoardVO;

public class BoardPrint {
	//1.List ���
	public void list(List<BoardVO> list) {
		//����
		System.out.println("+===============+");
		System.out.println("+�Խ��� ����Ʈ+");
		System.out.println("+===============+");

		//�׸�
		System.out.println("+===============+");
		System.out.println(" ��ȣ| ���� | �ۼ��� | �ۼ��� | ��ȸ�� ");
		System.out.println("+===============+");
		
		//������
		for (BoardVO vo :list) {
			System.out.print(" "+ vo.getNo());
			System.out.print("|"+ vo.getTitle());
			System.out.print("|"+ vo.getWriter());
			System.out.print("|"+ vo.getWriteDate());
			System.out.print("|"+ vo.getHit());

			System.out.println();
		}
		System.out.println();
	}//end of list;
	
	public void view(BoardVO vo) {
		//����
		System.out.println("+===============+");
		System.out.println("+�Խ��� �۾���+");
		System.out.println("+===============+");
		
		//������ ��� - �׸� �̸� : ������

		System.out.println("+===============+");
		
		System.out.print("��ȣ : "+ vo.getNo());
		System.out.print("���� : "+ vo.getTitle());
		System.out.print("���� : "+ vo.getContent());
		System.out.print("�ۼ��� : "+ vo.getWriter());
		System.out.print("�ۼ��� : "+ vo.getWriteDate());
		System.out.print("��ȸ�� : "+ vo.getHit());
		System.out.println("+===============+");
	}
}
