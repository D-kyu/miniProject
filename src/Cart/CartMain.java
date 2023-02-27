package Cart;

import Cart.dao.CartDao;
import Cart.vo.CartVO;


import java.util.List;
import java.util.Scanner;

public class CartMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CartDao dao = new CartDao();
        while(true){
            System.out.println("======== [CartDao TABLE COMMAND] ========");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT : ");
            int sel = sc.nextInt();
            switch(sel){
                case 1 :
                    List<CartVO> list = dao.CartSelect();
                    dao.CartSelectPrint(list);
                    break;
                case 2 :
                    dao.CartInsert();
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
