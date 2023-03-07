package miniproj.DAO;

import miniproj.VO.ProdVO;
import miniproj.util.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProdDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<ProdVO> productsSelect() {
        List<ProdVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sqlp = "SELECT * FROM PRODUCTS";
            rs = stmt.executeQuery(sqlp);

            while (rs.next()) {
                String pID = rs.getNString("PRODUCT_ID");
                String pName = rs.getNString("PRODUCT_NAME");
                String color = rs.getNString("COLOR");
                String pSize = rs.getNString("PSIZE");
                int price = rs.getInt("PRICE");
                int stkQuan = rs.getInt("STOCK_QUANTITY");
                ProdVO voP = new ProdVO(pID, pName, color, pSize, price, stkQuan);
                list.add(voP);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void productSelectPrint(List<ProdVO> list) {
        for (ProdVO p : list) {
            System.out.println("상품ID : " + p.getProduct_id());
            System.out.println("상품명 : " + p.getProduct_name());
            System.out.println("색상 : " + p.getColor());
            System.out.println("사이즈  : " + p.getpSize());
            System.out.println("가격 : " + p.getPrice());
            System.out.println("재고수량 : " + p.getStock_quantity());
            System.out.println("-------------------");

        }
    }

    public void productsInsert() {
        System.out.println("상품 정보를 입력 하세요.");
        System.out.print("상품ID : ");
        String pID = sc.next();
        System.out.print("상품명 : ");
        String pName = sc.next();
        System.out.print("컬러 : ");
        String color = sc.next();
        System.out.print("사이즈 : ");
        String pSize = sc.next();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        System.out.print("수량 : ");
        int stkQuan = sc.nextInt();


        String sqlpp = "INSERT INTO PRODUCTS(PRODUCT_ID, PRODUCT_NAME, COLOR, PSIZE, PRICE, STOCK_QUANTITY) VALUES("
                + "'" + pID + "'" + ", " + "'" + pName + "'" + ", " + "'" + color + "'" + ", "
                + "'" + pSize + "'" + ", " + price + ", "
                + stkQuan + ")";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sqlpp); //
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);


    }

    public void prodUpdate() {
        System.out.println("변경할 상품의 ID를 입력 하세요 : ");
        String pID = sc.next();
        System.out.print("상품명 : ");
        String pName = sc.next();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        System.out.print("수량 : ");
        int stkQuan = sc.nextInt();

        String sqlp = "UPDATE PRODUCTS SET PRODUCT_NAME = ?, PRICE = ?, STOCK_QUANTITY = ? WHERE PRODUCT_ID = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sqlp);
            pStmt.setString(1,pName);
            pStmt.setInt(2,price);
            pStmt.setInt(3,stkQuan);
            pStmt.setString(4,pID);
            pStmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void empDelete(){
        System.out.println("삭제할 상품의 ID를 입력 하세요 : ");
        String pID = sc.next();
        String sql = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,pID);
            pStmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }
}




