package com.example.demo.database;
import com.example.demo.constants.DatabaseConstants;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class UserDriver {
    public static void ShiftDatabase(String bankAddress, String contractAddress) {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `database` SET address = ?, contractaddr = ? WHERE TRUE");
            preparedStatement.setString(1, bankAddress);
            preparedStatement.setString(2, contractAddress);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Nullable
    public static String GetContractAddress() {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `database`");
            ResultSet set = preparedStatement.executeQuery();
            if (set.next()) {
                return set.getString(2);
            }
            return null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean IsUserAdmin(String address) {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `database` WHERE address = ?");
            preparedStatement.setString(1, address);
            ResultSet set = preparedStatement.executeQuery();
            return set.next();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
