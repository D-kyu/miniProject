package miniproj.DAO;

import com.kh.jdbc.util.Common;
import miniproj.VO.CartVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<CartVO> CartSelect() {
        List<CartVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String spl = "SELECT * FROM CART";
            rs = stmt.executeQuery(spl);

            while (rs.next()) {
                String customer_id = rs.getNString("CUSTOMER_ID");
                String product_id = rs.getNString("PRODUCT_ID");
                int quantity = rs.getInt("QUANTITY");
                String product_name = rs.getNString("PRODUCT_NAME");
                int price = rs.getInt("PRICE");
                int ttl = quantity * price;
                CartVO cVo = new CartVO(customer_id, product_id, quantity, product_name, price,ttl);
                list.add(cVo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void CartSelectPrint(List<CartVO> list){
        for(CartVO ca : list){
            System.out.println("고객 ID : " + ca.getCustomer_id());
            System.out.println("상품 ID : " + ca.getProduct_id());
            System.out.println("수량 : " + ca.getQuantity());
            System.out.println("상품명 : " + ca.getProduct_name());
            System.out.println("가격 : " + ca.getPrice()+"원");
            System.out.println("총액 : " + ca.getTtl() + "원");
            System.out.println("--------------------------");
        }
    }


    public void CartInsert() {
        System.out.print("장바구니 추가정보를 입력하세요.");
        System.out.print("고객 ID : ");
        String customer_id = sc.next();
        System.out.print("상품 ID : ");
        String product_id = sc.next();
        System.out.print("수량 : ");
        int quantity = sc.nextInt();
        cartInsertT(customer_id, product_id, quantity);
        if (cartInsertT(customer_id, product_id, quantity) !=0)
            cartUpdateTTL(customer_id);
    }

    public int cartInsertT(String customer_id, String product_id, int quantity){
        int ret = 0;
        String sql = "INSERT INTO CART(CUSTOMER_ID, PRODUCT_ID, QUANTITY) VALUES ("
                + "'" + customer_id + "'" + ", " + "'" + product_id + "'" + ", " + quantity + ")";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            ret = stmt.executeUpdate(sql);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
        return ret;
    }


    public void cartUpdateTTL(String customer_id){
        String sql2 = "UPDATE CART SET TOTAL_COST = QUANTITY * PRICE WHERE CUSTOMER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql2);
            pStmt.setString(1, customer_id);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void CartUpdate() {
        System.out.print("변경할 상품 ID를 입력하세요 : ");
        String product_id = sc.next();
        System.out.print("변경할 고객 ID를 입력하세요 : ");
        String customer_id = sc.next();
        System.out.print("수량 : ");
        int quantity = sc.nextInt();
        cartUpdateT(product_id,customer_id,quantity);
        cartUpdateTTL(customer_id);
    }


    public void cartUpdateT(String product_id,String customer_id,int quantity) {
        String sql = "UPDATE CART SET QUANTITY = ? WHERE PRODUCT_ID =? AND CUSTOMER_ID =?" ;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, quantity);
            pStmt.setString(2, product_id);
            pStmt.setString(3,customer_id);
            pStmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void CartDelete() {
        System.out.print("삭제할 상품 ID를 입력 하세요 : ");
        String product_id = sc.next();
        System.out.print("삭제할 고객 ID를 입력 하세요 : ");
        String customer_id = sc.next();
        String sql = "DELETE FROM CART WHERE PRODUCT_ID = ? AND CUSTOMER_ID = ? ";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, product_id);
            pStmt.setString(2, customer_id);
            pStmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

}