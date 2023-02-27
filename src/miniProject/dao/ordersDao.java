package miniProject.dao;

import com.kh.jdbc.util.Common;
import miniProject.vo.ordersVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ordersDao {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<ordersVO> ordersSelect(){
        List<ordersVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String spl = "SELECT * FROM ORDERS";
            rs = stmt.executeQuery(spl);

            while(rs.next()){
                String order_id = rs.getNString("ORDER_ID");
                String customer_id = rs.getNString("CUSTOMER_ID");
                int total_cost = rs.getInt("TOTAL_COST");
                Date date = rs.getDate("ORDER_DATE");
                String payment_method = rs.getNString("PAYMENT_METHOD");
                String shipping_address = rs.getNString("SHIPPING_ADDRESS");
                ordersVO vo = new ordersVO(order_id, customer_id, total_cost, date, payment_method, shipping_address);
                list.add(vo);
            }
            Common.close(rs);       // 연결과 역순으로 해제
            Common.close((stmt));
            Common.close(conn);

            } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void ordersSelectPrint(List<ordersVO> list){
        for(ordersVO e : list){
            System.out.println("주문ID : " + e.getOrder_id());
            System.out.println("고객ID : " + e.getCustomer_id());
            System.out.println("총합가격 : " + e.getTotal_cost());
            System.out.println("주문일자 : " + e.getOrder_date());
            System.out.println("결제방법 : " + e.getPayment_method());
            System.out.println("배송지 : " + e.getShipping_address());
            System.out.println("--------------------------------");
        }
    }
    public void ordersInsert(){
        System.out.print(" 주문정보를 입력하세요. ");
        System.out.print(" 주문ID : ");
        String order_id = sc.next();
        System.out.println("고객ID : ");
        String customer_id = sc.next();
        System.out.println("총합가격 : ");
        int total_cost = sc.nextInt();
        System.out.println("주문일자 : ");
        String order_date = sc.next();
        System.out.println("결제방법 : ");
        String payment_method = sc.next();
        System.out.println("배송지 : ");
        String shippping_address = sc.next();

        String sql = "INSERT INTO ORDERS(ORDER_ID, CUSTOMER_ID, TOTAL_COST, ORDER_DATE, PAYMENT_METHOD, SHIPPING_ADDRESS) VALUES ("
                + "'" + order_id + "'" + ", " + "'" + customer_id + "'" + ", " + total_cost + ", " +
                "'" + order_date + "'" + ", " + "'" + payment_method + "'" + ", " + "'" + shippping_address + "'" + ")";

        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql);
            System.out.println("Return : " + ret);
        } catch(Exception e){
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);

    }
}
