package com.example.demo.controller;

import com.example.demo.constants.GasConstants;
import com.example.demo.contract.Money;
import com.example.demo.database.NicknameDriver;
import com.example.demo.database.TransactionsDriver;
import com.example.demo.database.TransfersDriver;
import com.example.demo.database.UserDriver;
import com.example.demo.message.DatabaseMessage;
import com.example.demo.message.UserMessage;
import com.example.demo.model.User;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    final Web3j web3j;

    public UserController(Web3j web3j) {
        this.web3j = web3j;
    }

    @NotNull
    private User createNewUser(Credentials credentials) {
        User user = new User();
        user.setAddress(credentials.getAddress());
        user.setPrivateKey(credentials.getEcKeyPair().getPrivateKey().toString(16));
        user.setPublicKey(credentials.getEcKeyPair().getPublicKey().toString(16));
        return user;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/signin")
    public UserMessage signIn() {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create();
        User user = createNewUser(credentials);
        UserMessage msg = new UserMessage();
        msg.setData(user);
        msg.setMessage("success");
        return msg;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public UserMessage logIn(@RequestParam("privateKey") String privateKey) {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(privateKey);
        UserMessage msg = new UserMessage();
        if (credentials == null) {
            msg.setMessage("fail");
        } else {
            msg.setMessage("success");
            User user = new User();
            user.setAddress(credentials.getAddress());
            user.setPublicKey(credentials.getEcKeyPair().getPublicKey().toString(16));
            user.setPrivateKey(credentials.getEcKeyPair().getPrivateKey().toString(16));
            user.setAdmin(UserDriver.IsUserAdmin(user.getAddress()));
            msg.setData(user);
        }
        return msg;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/shift")
    public DatabaseMessage shift() {
        DatabaseMessage msg = new DatabaseMessage();
        try {
            EncryptType.encryptType = 0;
            Credentials credentials = GenCredential.create();
            User user = createNewUser(credentials);
            user.setAdmin(true);
            Money money = Money.deploy(web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();
            msg.setMessage("success");
            msg.setUser(user);
            msg.setContractAddress(money.getContractAddress());
            UserDriver.ShiftDatabase(user.getAddress(), money.getContractAddress());
            TransactionsDriver.TruncateTransactions();
            NicknameDriver.TruncateNickname();
            TransfersDriver.TruncateTransfers();
        } catch (Exception ex) {
            msg.setMessage(ex.getMessage());
        }
        return msg;
    }
}
