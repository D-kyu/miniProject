package miniproj.DAO;


import miniproj.VO.OrderItemsVO;
import miniproj.util.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderItemsDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null; //// 더블형 추가
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<OrderItemsVO> orderItemsSelect() {
        List<OrderItemsVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sqloi = "SELECT * FROM ORDER_ITEMS";
            rs = stmt.executeQuery(sqloi);

            while (rs.next()) {
                String order_id = rs.getNString("ORDER_ID");
                String product_id = rs.getNString("PRODUCT_ID");
                int order_quantity = rs.getInt("ORDER_QUANTITY");
                int order_price = rs.getInt("ORDER_PRICE");
                OrderItemsVO voOI = new OrderItemsVO(order_id, product_id, order_quantity,order_price);
                list.add(voOI);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void orderItemsSelectPrint(List<OrderItemsVO> list) {
        for (OrderItemsVO oi : list) {
            System.out.println("주문 ID : " + oi.getOrder_id());
            System.out.println("상품 ID : " + oi.getProduct_id());
            System.out.println("주문 수량 : " + oi.getOrder_quantity() + "개");
            System.out.println("주문 가격  : " + oi.getOrder_price() + "원");
            System.out.println("-------------------");

        }
    }
    public void orderItemsInsert() {
        System.out.println("주문 정보를 입력 하세요.");
        System.out.print("주문 ID : ");
        String order_id = sc.next();
        System.out.print("상품 ID : ");
        String product_id = sc.next();
        System.out.print("주문 수량 : ");
        int order_quantity = sc.nextInt();
        System.out.print("주문 금액 : ");
        int order_price = sc.nextInt();


        String sqloi = "INSERT INTO ORDER_ITEMS(ORDER_ID, PRODUCT_ID, ORDER_QUANTITY, ORDER_PRICE) VALUES("
                + "'" + order_id + "'" + ", " + "'" + product_id + "'" + ", " +  order_quantity  + ", " + order_price  + ")";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sqloi); //
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);

    }

    public void orderItemsUpdate() {
        System.out.println("변경할 주문 ID를 입력 하세요 : ");
        String order_id = sc.next();
        System.out.println("변경할 상품 ID를 입력 하세요 : ");
        String product_id = sc.next();
        System.out.print("주문 수량 : ");
        int order_q = sc.nextInt();
        System.out.print("주문 가격 : ");
        int order_price = sc.nextInt();

        String sqloi = "UPDATE ORDER_ITEMS SET ORDER_QUANTITY = ?, ORDER_PRICE = ? WHERE ORDER_ID = ? AND PRODUCT_ID = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sqloi);
            pStmt.setInt(1,order_q);
            pStmt.setInt(2,order_price);
            pStmt.setString(3,order_id);
            pStmt.setString(4,product_id);
            pStmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void orderItemsDelete(){
        System.out.println("삭제할 주문 ID를 입력 하세요 : ");
        String order_id = sc.next();
        System.out.println("삭제할 상품 ID를 입력 하세요 : ");
        String product_id = sc.next();
        String sqlio = "DELETE FROM ORDER_ITEMS WHERE ORDER_ID = ? AND PRODUCT_ID = ?";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sqlio);
            pStmt.setString(1,order_id);
            pStmt.setString(2,product_id);
            pStmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }
}
