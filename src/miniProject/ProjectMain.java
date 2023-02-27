package miniProject;

import miniProject.vo.ordersVO;
import miniProject.dao.ordersDao;

import java.util.List;
import java.util.Scanner;

public class ProjectMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ordersDao dao = new ordersDao();
        while(true){
            System.out.println("======= [ordersDao TABLE COMMAND] =======");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.print("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT : ");
            int sel = sc.nextInt();
            switch(sel){
                case 1 :
                    List<ordersVO> list = dao.ordersSelect();
                    dao.ordersSelectPrint(list);
                    break;
                case 2 :
                    dao.ordersInsert();
                    break;
                case 3 :
                case 4 :
                case 5 :
                    System.out.println("메뉴를 종료 합니다.");
                    return;
            }
        }
    }
}
