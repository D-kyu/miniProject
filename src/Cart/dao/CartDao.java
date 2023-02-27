package Cart.dao;

import Cart.vo.CartVO;
import com.kh.jdbc.util.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartDao {
    Connection conn = null;
    Statement stmt = null;
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
                CartVO vo = new CartVO(customer_id, product_id, quantity, product_name, price);
                list.add(vo);
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
        for(CartVO e : list){
            System.out.println("고객 ID : " + e.getCustomer_id());
            System.out.println("상품 ID : " + e.getProduct_id());
            System.out.println("수량 : " + e.getQuantity());
            System.out.println("상품명 : " + e.getProduct_name());
            System.out.println("가격 : " + e.getPrice());
            System.out.println("--------------------------");
        }
    }
    public void CartInsert(){
        System.out.print("장바구니 추가정보를 입력하세요.");
        System.out.print("고객 ID : ");
        String customer_id = sc.next();
        System.out.print("상품 ID : ");
        String product_id = sc.next();
        System.out.println("수량 : ");
        int quantity = sc.nextInt();
        System.out.println("상품명 : ");
        String product_name = sc.next();
        System.out.println("가격 : ");
        int price = sc.nextInt();

        String sql = "INSERT INTO ORDERS(CUSTOMER_ID, PRODUCT_ID, QUANTITY, PRODUCT_NAME, PRICE) VALUES ("
                + "'" + customer_id + "'" + ", " + "'" + product_id + "'" + ", " + quantity + ", "
                + "'" + product_name + "'" + ", " + price + ")";
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
