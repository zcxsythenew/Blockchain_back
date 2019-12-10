package com.example.demo.database;

import com.example.demo.constants.DatabaseConstants;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class NicknameDriver {
    @Nullable
    public static ResultSet GetNicknames(String obligor) {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nickname WHERE obligor=?");
            preparedStatement.setString(1, obligor);
            return preparedStatement.executeQuery();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static String GetNickname(String obligor, String address) {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nickname WHERE obligor=? AND address=?");
            preparedStatement.setString(1, obligor);
            preparedStatement.setString(2, address);
            ResultSet set = preparedStatement.executeQuery();
            if (set.next()) {
                return set.getString(3);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean SetNickname(String obligor, String address, String nickname) {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `nickname` (`obligor`, `address`, `nickname`) VALUES (?, ?, ?)");
            preparedStatement.setString(1, obligor);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, nickname);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException sqlEx) {
            return false;
        }
    }

    public static void TruncateNickname() {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("truncate table nickname");
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
