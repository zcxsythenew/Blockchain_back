package com.example.demo.database;

import com.example.demo.constants.DatabaseConstants;
import com.example.demo.model.Transaction;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.sql.*;

public class TransactionsDriver {
    @Nullable
    public static ResultSet GetTransactions(String address, int type) {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement;
            switch (type) {
                case 0:
                    preparedStatement = connection.prepareStatement("SELECT * FROM transactions T\n" +
                            "WHERE T.obligor=?\n" +
                            "OR T.obligee=?\n" +
                            "OR (SELECT COUNT(*) FROM `database` WHERE address=?)=1\n" +
                            "OR (T.verified AND T.transfer=1 AND T.transferTo=?)");
                    preparedStatement.setString(1, address);
                    preparedStatement.setString(2, address);
                    preparedStatement.setString(3, address);
                    preparedStatement.setString(4, address);
                    break;
                case 1:
                    preparedStatement = connection.prepareStatement("SELECT * FROM transactions T\n" +
                            "WHERE (T.obligee=? OR T.obligor=?)\n" +
                            "AND T.verified\n" +
                            "AND T.transfer=0\n" +
                            "AND !T.discount\n" +
                            "AND !T.settle");
                    preparedStatement.setString(1, address);
                    preparedStatement.setString(2, address);
                    break;
                case 2:
                    preparedStatement = connection.prepareStatement("SELECT * FROM transactions T\n" +
                            "WHERE (T.obligee=? OR T.obligor=? OR (T.transfer=1 AND T.transferTo=?) OR (SELECT COUNT(*) FROM `database` WHERE address=?)=1)\n" +
                            "AND T.verified\n" +
                            "AND T.transfer!=0");
                    preparedStatement.setString(1, address);
                    preparedStatement.setString(2, address);
                    preparedStatement.setString(3, address);
                    preparedStatement.setString(4, address);
                    break;
                case 3:
                    preparedStatement = connection.prepareStatement("SELECT * FROM transactions T\n" +
                            "WHERE (T.obligee=? AND T.transfer!=2)\n" +
                            "AND T.verified\n" +
                            "AND (T.transfer=0 OR T.transferVerified)\n" +
                            "AND !T.discount\n" +
                            "AND !T.settle");
                    preparedStatement.setString(1, address);
                    break;
                case 4:
                    preparedStatement = connection.prepareStatement("SELECT * FROM transactions T\n" +
                            "WHERE ((T.obligee=? AND T.transfer!=2) OR (SELECT COUNT(*) FROM `database` WHERE address=?)=1)\n" +
                            "AND T.verified\n" +
                            "AND (T.transfer=0 OR T.transferVerified)\n" +
                            "AND T.discount");
                    preparedStatement.setString(1, address);
                    preparedStatement.setString(2, address);
                    break;
                case 5:
                    preparedStatement = connection.prepareStatement("SELECT * FROM transactions T\n" +
                            "WHERE (T.obligee=? AND T.transfer!=2)\n" +
                            "AND T.verified\n" +
                            "AND (T.transfer=0 OR T.transferVerified)\n" +
                            "AND (!T.discount OR T.discountVerified)\n" +
                            "AND !T.settle");
                    preparedStatement.setString(1, address);
                    break;
                case 6:
                    preparedStatement = connection.prepareStatement("SELECT * FROM transactions T\n" +
                            "WHERE ((T.obligee=? AND T.transfer!=2) OR (SELECT COUNT(*) FROM `database` WHERE address=?)=1)\n" +
                            "AND T.verified\n" +
                            "AND (T.transfer=0 OR T.transferVerified)\n" +
                            "AND (!T.discount OR T.discountVerified)\n" +
                            "AND T.settle");
                    preparedStatement.setString(1, address);
                    preparedStatement.setString(2, address);
                    break;
                case 7:
                    preparedStatement = connection.prepareStatement("SELECT t2.*, t1.id as 'transferId' FROM transfer t1, transactions t2 WHERE t1.a = t2.id OR t1.b = t2.id");
                    break;
                default:
                    return null;
            }
            return preparedStatement.executeQuery();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static void TruncateTransactions() {
        try {
            Connection connection = ConnectionDriver.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("truncate table transactions");
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static boolean updateTransaction(Transaction transaction, boolean insert) throws Exception {
        Connection connection = ConnectionDriver.getInstance().getConnection();
        PreparedStatement preparedStatement;
        int base = insert ? 0 : -1;
        if (insert) {
            preparedStatement = connection.prepareStatement("INSERT INTO transactions (`id`, `obligor`, `obligee`, `datetime`, `amount`, `verified`, `transfer`, `transferTo`, `transferVerified`, `discount`, `discountVerified`, `settle`, `settleVerified`, `note`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, transaction.id);
        } else {
            preparedStatement = connection.prepareStatement("UPDATE `transactions` SET `obligor` = ?, `obligee` = ?, `datetime` = ?, `amount` = ?, `verified` = ?, `transfer` = ?, `transferTo` = ?, `transferVerified` = ?, `discount` = ?, `discountVerified` = ?, `settle` = ?, `settleVerified` = ?, `note` = ? WHERE (`id` = ?);\n");
            preparedStatement.setInt(14, transaction.id);
        }

        preparedStatement.setString(base + 2, transaction.obligor);
        preparedStatement.setString(base + 3, transaction.obligee);
        preparedStatement.setString(base + 4, transaction.datetime);
        preparedStatement.setBigDecimal(base + 5, new BigDecimal(transaction.amount));
        preparedStatement.setBoolean(base + 6, transaction.verified);
        preparedStatement.setInt(base + 7, transaction.transfer);
        preparedStatement.setString(base + 8, transaction.transferTo);
        preparedStatement.setBoolean(base + 9, transaction.transferVerified);
        preparedStatement.setBoolean(base + 10, transaction.discount);
        preparedStatement.setBoolean(base + 11, transaction.discountVerified);
        preparedStatement.setBoolean(base + 12, transaction.settle);
        preparedStatement.setBoolean(base + 13, transaction.settleVerified);
        preparedStatement.setString(base + 14, transaction.note);
        return preparedStatement.executeUpdate() == 1;
    }
}
