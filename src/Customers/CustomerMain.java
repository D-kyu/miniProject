package Customers;

import Customers.Dao.CustomerDao;
import Customers.Vo.CustomerVo;


import java.util.List;
import java.util.Scanner;
public class CustomerMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerDao dao = new CustomerDao();
        while (true) {
            System.out.println("===== [EMP Table 조회] =====");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<CustomerVo> list = dao.CustomerSelect();
                    dao.CustomerSelectPrint(list);
                    break;
                case 2 :
                    dao.CustomerInsert();
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
