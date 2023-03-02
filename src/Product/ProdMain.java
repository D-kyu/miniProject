package Product;


import Product.Dao.ProdDAO;
import Product.Vo.ProdVO;

import java.util.List;
import java.util.Scanner;

public class ProdMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdDAO dao = new ProdDAO();
        while (true) {
            System.out.println("===== [EMP Table 조회] =====");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<ProdVO> list = dao.productsSelect();
                    dao.productSelectPrint(list);
                    break;
                case 2 :
                    dao.productsInsert();
                    break;
                case 3 :
                case 4 :
                case 5 :
                    System.out.println("메뉴를 종료 합니다");
                    return;
            }
        }
    }
}

