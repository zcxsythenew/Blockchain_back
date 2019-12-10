package com.example.demo.controller;

import com.example.demo.constants.GasConstants;
import com.example.demo.contract.Money;
import com.example.demo.database.NicknameDriver;
import com.example.demo.database.TransactionsDriver;
import com.example.demo.database.TransfersDriver;
import com.example.demo.database.UserDriver;
import com.example.demo.message.GeneralMessage;
import com.example.demo.message.TransactionsMessage;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transfer;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.protocol.exceptions.TransactionException;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.BaseException;
import org.fisco.bcos.web3j.tx.txdecode.ResultEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionsController {
    final Web3j web3j;

    public TransactionsController(Web3j web3j) {
        this.web3j = web3j;
    }

    private void makeTransactionFriendly(String address, @NotNull Transaction transaction) {
        String obligor = transaction.getObligor();
        if (obligor.equals(address)) {
            transaction.setObligorNickname("我");
        } else {
            obligor = NicknameDriver.GetNickname(address, transaction.getObligor());
            if (obligor != null) {
                transaction.setObligorNickname(obligor);
            }
        }

        String obligee = transaction.getObligee();
        if (obligee.equals(address)) {
            transaction.setObligeeNickname("我");
        } else {
            obligee = NicknameDriver.GetNickname(address, transaction.getObligee());
            if (obligee != null) {
                transaction.setObligeeNickname(obligee);
            }
        }

        String transferTo = transaction.getTransferTo();
        if (transferTo.equals(address)) {
            transaction.setTransferToNickname("我");
        } else {
            transferTo = NicknameDriver.GetNickname(address, transaction.getTransferTo());
            if (transferTo != null) {
                transaction.setTransferToNickname(transferTo);
            }
        }
    }

    @NotNull
    private TransactionsMessage messageFromResultSet(String address, ResultSet set, boolean includeTransferId) {
        TransactionsMessage msg = new TransactionsMessage();
        msg.setData(new ArrayList<>());
        try {
            if (set != null) {
                while (set.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setId(set.getInt(1));
                    transaction.setObligor(set.getString(2));
                    transaction.setObligee(set.getString(3));
                    transaction.setDatetime(set.getString(4));
                    transaction.setAmount(set.getBigDecimal(5).toBigInteger());
                    transaction.setVerified(set.getBoolean(6));
                    transaction.setTransfer(set.getInt(7));
                    transaction.setTransferTo(set.getString(8));
                    transaction.setTransferVerified(set.getBoolean(9));
                    transaction.setDiscount(set.getBoolean(10));
                    transaction.setDiscountVerified(set.getBoolean(11));
                    transaction.setSettle(set.getBoolean(12));
                    transaction.setSettleVerified(set.getBoolean(13));
                    transaction.setNote(set.getString(14));
                    if (includeTransferId) {
                        transaction.setTransferId(set.getInt(15));
                    }
                    makeTransactionFriendly(address, transaction);
                    msg.getData().add(transaction);
                }
                msg.setMessage("success");
            } else {
                msg.setMessage("MySQL Server error");
            }
        } catch (Exception ex) {
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
        }

        return msg;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/transactions")
    public TransactionsMessage getTransactions(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 0);
        return messageFromResultSet(address, set, false);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/transfer")
    public TransactionsMessage getTransfer(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 1);
        return messageFromResultSet(address, set, false);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/transfer/previous")
    public TransactionsMessage getPreviousTransfer(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 2);
        return messageFromResultSet(address, set, false);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/discount")
    public TransactionsMessage getDiscount(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 3);
        return messageFromResultSet(address, set, false);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/discount/previous")
    public TransactionsMessage getPreviousDiscount(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 4);
        return messageFromResultSet(address, set, false);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/settle")
    public TransactionsMessage getSettle(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 5);
        return messageFromResultSet(address, set, false);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/settle/previous")
    public TransactionsMessage getPreviousSettle(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 6);
        return messageFromResultSet(address, set, false);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/transfer/admin")
    public TransactionsMessage getAdminTransfer(@RequestParam("address") String address) {
        ResultSet set = TransactionsDriver.GetTransactions(address, 7);
        return messageFromResultSet(address, set, true);
    }

    public static boolean UpdateTransactionById(@NotNull Money money, BigInteger id, boolean insert) throws Exception {
        var transactionTuple = money.receipts(id).send();
        Transaction transaction = new Transaction();
        transaction.setId(id.intValue());
        transaction.setObligor(transactionTuple.getValue1());
        transaction.setObligee(transactionTuple.getValue2());
        transaction.setDatetime(transactionTuple.getValue3());
        transaction.setAmount(transactionTuple.getValue4());
        transaction.setVerified(transactionTuple.getValue5());
        transaction.setTransfer(transactionTuple.getValue6().intValue());
        transaction.setTransferTo(transactionTuple.getValue7());
        transaction.setTransferVerified(transactionTuple.getValue8());
        transaction.setDiscount(transactionTuple.getValue9());
        transaction.setDiscountVerified(transactionTuple.getValue10());
        transaction.setSettle(transactionTuple.getValue11());
        transaction.setSettleVerified(transactionTuple.getValue12());
        transaction.setNote(transactionTuple.getValue13());

        return TransactionsDriver.updateTransaction(transaction, insert);
    }

    public static boolean UpdateTransferById(@NotNull Money money, BigInteger id, boolean insert) throws Exception {
        var transferTuple = money.transfers(id).send();
        boolean success;
        Transfer transfer = new Transfer();
        transfer.setId(id.intValue());
        transfer.setA(transferTuple.getValue1().intValue());
        transfer.setB(transferTuple.getValue2().intValue());
        success = UpdateTransactionById(money, transferTuple.getValue1(), false);
        success = UpdateTransactionById(money, transferTuple.getValue2(), false) && success;
        return TransfersDriver.updateTransfer(transfer, insert) && success;
    }

    @NotNull
    public static Money GetMoney(Web3j web3j, String privateKey) throws Exception {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(privateKey);
        String contractAddress = UserDriver.GetContractAddress();
        if (contractAddress == null) throw new Exception("Contract does not exist");

        return Money.load(contractAddress, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
    }

    public static List<ResultEntity> GetResult(@NotNull TransactionReceipt receipt) throws BaseException, TransactionException {
        String input = receipt.getInput();
        String output = receipt.getOutput();
        return Money.getTransactionDecoder().decodeOutputReturnObject(input, output).getResult();
    }
}
