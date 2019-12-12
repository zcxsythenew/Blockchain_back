package com.example.demo.controller;

import com.example.demo.constants.GasConstants;
import com.example.demo.contract.Money;
import com.example.demo.database.UserDriver;
import com.example.demo.message.GeneralMessage;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.ResultEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class VerifyController {
    final Web3j web3j;

    public VerifyController(Web3j web3j) {
        this.web3j = web3j;
    }

    @NotNull
    private GeneralMessage verifyImplement(String privateKey, int transactionId, int type) {
        GeneralMessage msg = new GeneralMessage();
        try {
            Money money = TransactionsController.GetMoney(web3j, privateKey);
            TransactionReceipt receipt;
            switch (type) {
                case 0:
                    receipt = money.VerifyPurchase(BigInteger.valueOf(transactionId)).send();
                    break;
                case 1:
                    receipt = money.VerifyDiscount(BigInteger.valueOf(transactionId)).send();
                    break;
                case 2:
                    receipt = money.CancelDiscount(BigInteger.valueOf(transactionId)).send();
                    break;
                case 3:
                    receipt = money.VerifySettle(BigInteger.valueOf(transactionId)).send();
                    break;
                case 4:
                    receipt = money.CancelSettle(BigInteger.valueOf(transactionId)).send();
                    break;
                case 5:
                    receipt = money.Discount(BigInteger.valueOf(transactionId)).send();
                    break;
                case 6:
                    receipt = money.Settle(BigInteger.valueOf(transactionId)).send();
                    break;
                case 7:
                    receipt = money.VerifyTransfer(BigInteger.valueOf(transactionId)).send();
                    break;
                case 8:
                    receipt = money.CancelTransfer(BigInteger.valueOf(transactionId)).send();
                    break;
                default:
                    msg.setMessage("internal error");
                    return msg;
            }
            List<ResultEntity> results = TransactionsController.GetResult(receipt);
            SetMessageFromResults(msg, results);
            if (type == 7 || type == 8) {
                if (!TransactionsController.UpdateTransferById(money, BigInteger.valueOf(transactionId), false)) {
                    msg.setMessage("Database error");
                }
            } else {
                if (!TransactionsController.UpdateTransactionById(money, BigInteger.valueOf(transactionId), false)) {
                    msg.setMessage("Database error");
                }
            }
        } catch (Exception ex) {
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
        }
        return msg;
    }

    private static void SetMessageFromResults(GeneralMessage msg, @NotNull List<ResultEntity> results) {
        boolean result = (boolean) results.get(0).getData();

        if (result) {
            msg.setMessage("success");
        } else {
            msg.setMessage("Block-chain failure");
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/verify")
    public GeneralMessage verifyTransaction(@RequestParam("privateKey") String privateKey,
                                            @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 0);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/verify/discount")
    public GeneralMessage verifyTransactionDiscount(@RequestParam("privateKey") String privateKey,
                                            @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 1);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/cancel/discount")
    public GeneralMessage cancelTransactionDiscount(@RequestParam("privateKey") String privateKey,
                                            @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 2);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/verify/settle")
    public GeneralMessage verifyTransactionSettle(@RequestParam("privateKey") String privateKey,
                                            @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 3);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/cancel/settle")
    public GeneralMessage cancelTransactionSettle(@RequestParam("privateKey") String privateKey,
                                            @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 4);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/discount")
    public GeneralMessage discount(@RequestParam("privateKey") String privateKey,
                                                  @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 5);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/settle")
    public GeneralMessage settle(@RequestParam("privateKey") String privateKey,
                                                  @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 6);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/verify/transfer")
    public GeneralMessage verifyTransactionTransfer(@RequestParam("privateKey") String privateKey,
                                                    @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 7);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/cancel/transfer")
    public GeneralMessage cancelTransactionTransfer(@RequestParam("privateKey") String privateKey,
                                                    @RequestParam("id") int transactionId) {
        return verifyImplement(privateKey, transactionId, 8);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/transfer")
    public GeneralMessage transfer(@RequestParam("privateKey") String privateKey,
                                   @RequestParam("a") int a,
                                   @RequestParam("b") int b) {
        GeneralMessage msg = new GeneralMessage();
        try {
            Money money = TransactionsController.GetMoney(web3j, privateKey);
            TransactionReceipt receipt = money.TransferTwoReceipts(BigInteger.valueOf(a), BigInteger.valueOf(b)).send();
            List<ResultEntity> results = TransactionsController.GetResult(receipt);
            SetMessageFromResults(msg, results);
            if ((boolean) results.get(0).getData()) {
                BigInteger transferId = (BigInteger) results.get(1).getData();
                if (!TransactionsController.UpdateTransferById(money, transferId, true)) {
                    msg.setMessage("Database error");
                }
            }
        } catch (Exception ex) {
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
        }
        return msg;
    }
}
