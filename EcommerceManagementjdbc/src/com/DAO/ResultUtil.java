package com.DAO;

public class ResultUtil {

    public static boolean checkResult(int rowsAffected, int expectedRows) {

        if (rowsAffected == expectedRows) {
            System.out.println("-------------------------------------");
            System.out.println("Transaction Successful");
            System.out.println("Rows Affected : " + rowsAffected);
            System.out.println("-------------------------------------");
            return true;
        } else {
            System.out.println("-------------------------------------");
            System.out.println("Transaction Failed");
            System.out.println("Expected Rows : " + expectedRows);
            System.out.println("Rows Affected : " + rowsAffected);
            System.out.println("-------------------------------------");
            return false;
        }
    }

}