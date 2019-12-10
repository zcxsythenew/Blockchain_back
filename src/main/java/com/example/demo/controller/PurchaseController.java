package com.example.demo.controller;

import com.example.demo.constants.GasConstants;
import com.example.demo.contract.Money;
import com.example.demo.database.TransactionsDriver;
import com.example.demo.database.UserDriver;
import com.example.demo.message.GeneralMessage;
import com.example.demo.model.Transaction;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.ResultEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class PurchaseController {
    final Web3j web3j;

    public PurchaseController(Web3j web3j) {
        this.web3j = web3j;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/purchase")
    public GeneralMessage sendPurchase(@RequestParam("privateKey") String privateKey,
                                       @RequestParam("datetime") String datetime,
                                       @RequestParam("obligee") String obligee,
                                       @RequestParam("amount") BigInteger amount,
                                       @RequestParam("note") String note) {
        GeneralMessage msg = new GeneralMessage();
        try {
            Money money = TransactionsController.GetMoney(web3j, privateKey);
            TransactionReceipt receipt = money.Purchase(datetime, obligee, amount, note).send();
            List<ResultEntity> results = TransactionsController.GetResult(receipt);
            BigInteger receipt_index = (BigInteger) results.get(0).getData();

            boolean databaseResult = TransactionsController.UpdateTransactionById(money, receipt_index, true);

            if (databaseResult) {
                msg.setMessage("success");
            } else {
                msg.setMessage("Database error");
            }
        } catch (Exception ex) {
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
        }
        return msg;
    }
}
