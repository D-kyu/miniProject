package Product.Dao;


import Product.Vo.ProdVO;
import Product.util.Common;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProdDAO {
    Connection conn = null;
    Statement stmt = null;
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
}

