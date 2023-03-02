package Customers.Dao;

import Customers.Vo.CustomerVo;
import com.kh.jdbc.util.Common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDao {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<CustomerVo> CustomerSelect() {
        List<CustomerVo> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CUSTOMERS";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String CUSTOMER_ID = rs.getString("CUSTOMER_ID");
                String CUSTOMER_NAME = rs.getString("CUSTOMER_NAME");
                String PASSWORD = rs.getString("PASSWORD");
                String GENDER = rs.getNString("GENDER");
                String PHONE_NUMBER = rs.getString("PHONE_NUMBER");
                String EMAIL = rs.getString("EMAIL");
                String ADDRESS = rs.getString("ADDRESS");
                Date REGISTRATION_DATE = rs.getDate("REGISTRATION_DATE");
                CustomerVo vo = new CustomerVo(CUSTOMER_ID, CUSTOMER_NAME, PASSWORD, GENDER, PHONE_NUMBER,
                        EMAIL, ADDRESS, REGISTRATION_DATE);
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

    public void CustomerSelectPrint(List<CustomerVo> list) {
        for (CustomerVo e : list) {
            System.out.println("아이디 : " + e.getCustomer_id());
            System.out.println("이름 : " + e.getCustomer_name());
            System.out.println("비밀번호 : " + e.getPassword());
            System.out.println("성별 : " + e.getGender());
            System.out.println("전화번호 : " + e.getPhone_number());
            System.out.println("이메일 : " + e.getEmail());
            System.out.println("주소 : " + e.getAddress());
            System.out.println("가입일자 : " + e.getRegistration_date());
            System.out.println("----------------------------------------");
        }
    }
    public void CustomerInsert() {
        System.out.println("아이디 : ");
        String customer_id = sc.next();
        System.out.println("이름 : ");
        String customer_name = sc.next();
        System.out.println("비밀번호 : ");
        String password = sc.next();
        System.out.println("성별 : ");
        String gender = sc.next();
        System.out.println("전화번호 : ");
        int phone_number = sc.nextInt();
        System.out.println("이메일 : ");
        String email = sc.next();
        System.out.println("주소 : ");
        String address = sc.next();
        System.out.println("가입일자 : ");
        String registration_date = sc.next();

        String sql = "INSERT INTO CUSTOMERS(CUSTOMER_ID, CUSTOMER_NAME, PASSWORD ,GENDER, PHONE_NUMBER, EMAIL, ADDRESS, REGISTRATION_DATE) VALUES("
                + "'" + customer_id + "'" + ", " + "'" + customer_name + "'" + ", " + "'" + password + "'" + ", " + "'" + gender + "'" + ", " + "'" + phone_number + "'"
                + ", " + "'" + email + "'" + ", " + "'" + address + "'" + ", " + "'" + registration_date + "'" + ")";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql);
            System.out.println("Return : " + ret);
        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(conn);
        Common.close(stmt);
    }
}

