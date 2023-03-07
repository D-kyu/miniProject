package miniproj;

import miniproj.DAO.*;
import miniproj.VO.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MiniMain {
    public static void main(String[] args) throws IOException {
        ProdDAO pDao = new ProdDAO();
        CustomerDAO cDao = new CustomerDAO();
        OrdersDAO oDao = new OrdersDAO();
        OrderItemsDAO oiDao = new OrderItemsDAO();
        CartDAO ctDao = new CartDAO();
        Scanner sc = new Scanner(System.in);
        boolean custom = false;
        boolean admin = false;

        String pwd = "mini0303";
        System.out.println("사용자를 선택하세요  : ");
        System.out.println("[1]고객, [2]관리자 :");
        int sel0 = sc.nextInt();

        if(sel0 == 1)custom = true;
        else custom = false;

        if(sel0 == 2) {
            System.out.println("사용자 비밀번호를 입력하세요");
            String adminpwd = sc.next();
            if(adminpwd.equals(pwd)) admin= true;
            else System.out.println("비밀번호가 틀렸습니다. 프로그램을 새로 시작해주세요.");
        } else {
            admin = false;
        }




        while (custom) {
            switch (sel0) {
                case 1:
                    System.out.println("조회 할 정보를 선택하세요 : ");
                    System.out.print("[1]상품 정보, [2]고객 정보, [3]장바구니, [4] 종료 :");
                    int sel1 = sc.nextInt();
                    switch (sel1) {
                        case 1:
                            List<ProdVO> listP = pDao.productsSelect();
                            pDao.productSelectPrint(listP);
                            break;
                        case 2:
                            List<CustomerVO> listC = cDao.CustomerSelect();
                            cDao.CustomerSelectPrint(listC);
                            System.out.print("조회 할 테이블의 수정을 선택하세요 : ");
                            System.out.print("[1]INSERT, [2]UPDATE, [3]DELETE, [4]EXIT :");
                            int sel3 = sc.nextInt();
                            switch (sel3) {
                                case 1:cDao.CustomerInsert();break;
                                case 2:cDao.CustomerUpdate();break;
                                case 3:cDao.CustomerDelete();break;
                                case 4:
                                    break;
                            }
                            break;
                        case 3:
                            List<CartVO> listCT = ctDao.CartSelect();
                            ctDao.CartSelectPrint(listCT);
                            System.out.print("조회 할 테이블의 수정을 선택하세요 : ");
                            System.out.print("[1]INSERT, [2]UPDATE, [3]DELETE, [4]EXIT :");
                            int sel4 = sc.nextInt();
                            switch (sel4) {
                                case 1:ctDao.CartInsert();break;
                                case 2:ctDao.CartUpdate();break;
                                case 3:ctDao.CartDelete(); break;
                                case 4:break;
                            }
                            break;
                        case 4:
                            System.out.println("메뉴를 종료 합니다.");
                            custom = false;
                            break;
                    }
                    break;
            }
        }

        while(admin){
            System.out.println("조회 할 정보를 선택하세요 : ");
            System.out.print("[1]상품 정보, [2]고객 정보, [3]주문 정보, [4]주문 상품 정보, [5]장바구니, [6] 종료 :");
            int sel5 = sc.nextInt();
            switch (sel5) {
                case 1:
                    List<ProdVO> listP = pDao.productsSelect();
                    pDao.productSelectPrint(listP);
                    System.out.println("조회 할 테이블의 수정을 선택하세요 : ");
                    System.out.println("[1]INSERT, [2]UPDATE, [3]DELETE, [4]EXIT :");
                    int sel10 = sc.nextInt();
                    switch (sel10) {
                        case 1: pDao.productsInsert();
                        case 2: pDao.prodUpdate();
                        case 3: pDao.empDelete();
                        case 4: break;
                    }
                    break;
                case 2:
                    List<CustomerVO> listC = cDao.CustomerSelect();
                    cDao.CustomerSelectPrint(listC);
                    System.out.println("조회 할 테이블의 수정을 선택하세요 : ");
                    System.out.println("[1]INSERT, [2]UPDATE, [3]DELETE, [4]EXIT :");
                    int sel6 = sc.nextInt();
                    switch (sel6){
                        case 1: cDao.CustomerInsert(); break;
                        case 2: cDao.CustomerUpdate(); break;
                        case 3: cDao.CustomerDelete(); break;
                        case 4: break;
                    }
                    break;
                case 3:
                    List<OrdersVO> listO = oDao.ordersSelect();
                    oDao.ordersSelectPrint(listO);
                    System.out.println("조회 할 테이블의 수정을 선택하세요 : ");
                    System.out.println("[1]INSERT, [2]UPDATE, [3]DELETE, [4]EXIT :");
                    int sel7 = sc.nextInt();
                    switch (sel7){
                        case 1: oDao.ordersInsert(); break;
                        case 2: oDao.ordersUpdate(); break;
                        case 3: oDao.ordersDelete(); break;
                        case 4: break;
                    }
                    break;
                case 4:
                    List<OrderItemsVO> listOI = oiDao.orderItemsSelect();
                    oiDao.orderItemsSelectPrint(listOI);
                    System.out.println("조회 할 테이블의 수정을 선택하세요 : ");
                    System.out.println("[1]INSERT, [2]UPDATE, [3]DELETE, [4]EXIT :");
                    int sel8 = sc.nextInt();
                    switch (sel8){
                        case 1: oiDao.orderItemsInsert(); break;
                        case 2: oiDao.orderItemsUpdate(); break;
                        case 3: oiDao.orderItemsDelete(); break;
                        case 4: break;
                    }
                    break;

                case 5:
                    List<CartVO> listCT = ctDao.CartSelect();
                    ctDao.CartSelectPrint(listCT);
                    System.out.println("조회 할 테이블의 수정을 선택하세요 : ");
                    System.out.println("[1]INSERT, [2]UPDATE, [3]DELETE, [4]EXIT :");
                    int sel9 = sc.nextInt();
                    switch (sel9){
                        case 1: ctDao.CartInsert(); break;
                        case 2: ctDao.CartUpdate(); break;
                        case 3: ctDao.CartDelete(); break;
                        case 4: break;
                    }
                    break;
                case 6:
                    System.out.println("메뉴를 종료 합니다.");
                    admin = false;
                    break;
            }
        }
    }

}
