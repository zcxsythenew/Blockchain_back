package com.example.demo.database;

import com.example.demo.model.Transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransfersDriver {
    public static boolean updateTransfer(Transfer transfer, boolean insert) throws Exception {
        Connection connection = ConnectionDriver.getInstance().getConnection();
        PreparedStatement preparedStatement;
        int base = insert ? 0 : -1;
        if (insert) {
            preparedStatement = connection.prepareStatement("INSERT INTO `transfer` (`id`, `a`, `b`) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, transfer.id);
        } else {
            preparedStatement = connection.prepareStatement("UPDATE `transfer` SET `a` = ?, `b` = ? WHERE (`id` = ?)");
            preparedStatement.setInt(3, transfer.id);
        }
        preparedStatement.setInt(base + 2, transfer.a);
        preparedStatement.setInt(base + 3, transfer.b);
        return preparedStatement.executeUpdate() == 1;
}

    public static void TruncateTransfers() {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("truncate table transfer");
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
