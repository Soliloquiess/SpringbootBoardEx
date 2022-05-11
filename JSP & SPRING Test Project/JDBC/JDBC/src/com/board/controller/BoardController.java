package com.board.controller;

import java.util.List;
import java.util.Scanner;

import com.board.service.BoardDeleteService;
import com.board.service.BoardListService;
import com.board.service.BoardUpdateService;
import com.board.service.BoardViewService;
import com.board.service.BoardWriteService;
import com.board.vo.BoardVO;
import com.print.BoardPrint;

/*
 * Controller = �䱸�� ������ ������ � ��ü�� ������ �� �����ִ� Ŭ����
 *            = �ʿ��� ������ �����ϰ� ����Ǵ� ��ü�� �Ѱ��ش�.
 */
public class BoardController {

	public static Scanner scanner = new Scanner(System.in);

	// ������ ó�� main�� �����. �ٸ��������� main�� ���۵Ǹ� �ȵȴ�.
	public static void main(String[] args) throws Exception {
		// ���ѹݺ�
		while (true) {

			// DBŬ���� Ȯ��
			Class.forName("com.util.db.DB");

			// �޴� ���
			System.out.println("=======================");
			System.out.println("1. �Խ��� ����Ʈ 2. �Խ��� �ۺ��� 3.�Խ��� �� ���� ");
			System.out.println("4. �Խ��� �ۼ��� 5. �Խ��� �ۻ��� 6.���α׷� ���� ");

			System.out.println("=======================");
			// �޴� ����
			System.out.println("�޴� ����");
			String menu = scanner.nextLine();
			// �޴� ó��

			switch (menu) {
			case "1":
				// �Խ��� ����Ʈ ó�� -> �����ϰ� ȣ���Ѵ�. : Controller - Service - DAO
				BoardListService boardListService = new BoardListService();
				List<BoardVO> list = boardListService.service();
				// ������ Ȯ�ο����� ���
				System.out.println("boardController.main.list" + list);
				// ������ ������ ����ϱ� - ����, �׸� �̸�, ������ - �����ϰ� ȣ���Ѵ�.
				BoardPrint boardPrint = new BoardPrint();
				boardPrint.list(list);
				break;

			case "2":
				// �Խ��� �ۺ��� ó�� -> �����ϰ� ȣ���Ѵ�. : Controller - Service - DAO
				// �� ���⸦ ���ؼ� �ʿ��� ���� - �� ��ȣ�� �Է��Ѵ�.
				System.out.println(">��ȣ:");
				String strNo = scanner.nextLine();
				long no = Long.parseLong(strNo);

				// �Խ��� �ۺ��� ó��
				BoardViewService boardViewService = new BoardViewService();
				BoardVO vo = boardViewService.service(no);

				// ������ Ȯ�ο����� ���
				System.out.println("BoardController.main().vo" + vo);

				// ������ ������ ����ϱ� = =����, �׸��̸� : ������ - �����ϰ� ȣ���Ѵ�.
				boardPrint = new BoardPrint();
				if (vo == null) {
					System.out.println("���� �۹�ȣ�Դϴ�.");
					break;
				}
				boardPrint.view(vo);
				break;

			case "3":
				// ������ ���� - ���� , ���� , �ۼ���
				System.out.println("����:");
				String title = scanner.nextLine();
				System.out.println("����:");
				String content = scanner.nextLine();
				System.out.println("�ۼ���");
				String writer = scanner.nextLine();
				// BoardVO �����ϰ� �����͸� �����Ѵ�.
				vo = new BoardVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				// Controller - Service -DAO
				BoardWriteService boardWriteService = new BoardWriteService();
				boardWriteService.service(vo);
				break;

			case "4":
				// ������ �� ��ȣ�� �޴´�.
				System.out.println("������ �� ��ȣ �Է�: ");
				strNo = scanner.nextLine();
				no = Long.parseLong(strNo);
				// �Է��� �� ��ȣ�� �����͸� �����ͼ� ��� -�� ����
				boardViewService = new BoardViewService();
				vo = boardViewService.service(no);

				// ������ ������ ����ϱ� - ����, �׸��̸�: ������ �����ϰ� ȣ���Ѵ�.
				boardPrint = new BoardPrint();
//			boardPrint.view(vo);(�Ʒ��� �̵�)

				String item = "1";
				// ������ ������ �Է��Ѵ�. 1- ���� 2- ���� 3- �ۼ��� 0- ���� 9- ���
				// while(item.equals("1")||item.equals("2")||item.equals("3")) {
				while (!item.equals("0") && !item.equals("9")) {

					// ������ �����ֱ�(������ ������) - DB�� ������ �Ǿ������� �ʴ´�.
					boardPrint.view(vo);
					System.out.println("1.���� 2. ���� 3. �ۼ��� 0 �����Ϸ� 9 ���");
					System.out.print("������ �׸��� ����->");
					item = scanner.nextLine();
					switch (item) {
					case "1":
						System.out.println("����: ");
						vo.setTitle(scanner.nextLine());
						break;
					case "2":
						System.out.println("����: ");
						vo.setContent(scanner.nextLine());
						break;
					case "3":
						System.out.println("�ۼ���: ");
						vo.setWriter(scanner.nextLine());
						break;
					// ���� ó���� �Ѵ�
					// ���� ó���� �Ѵ�.

					case "0": // ���� �Ϸ� ó��
						// ��ü�� �����ϰ� ȣ���Ѵ�.
						BoardUpdateService boardUpdateService = new BoardUpdateService();
						boardUpdateService.service(vo);
						System.out.println("---------------");
						System.out.println("���� �Ϸ�");
						System.out.println("---------------");

					case "9": // ���� �Ϸ� ó��
						// ��ü�� �����ϰ� ȣ���Ѵ�.
						System.out.println("---------------");
						System.out.println("���� ���");
						System.out.println("---------------");

					default:
						System.out.println("---------------");
						System.out.println("�߸��� �׸� ����");
						System.out.println("---------------");

						break;
					}
				}
				// ���� ó���� �Ѵ�.
				break;
			case "5":
				// ����ó��
				// ������ �� ��ȣ �ޱ�
				
				System.out.println("������ �� ��ȣ �Է�: ");
				strNo = scanner.nextLine();
				no = Long.parseLong(strNo);

				// �����ϰ� ȣ���Ѵ�.
				BoardDeleteService boardDeleteService = new BoardDeleteService();
				boardDeleteService.service(no);
				break;
			case "0":
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			}

		}
	}
}