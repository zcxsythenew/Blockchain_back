package com.example.demo.controller;

import com.example.demo.database.NicknameDriver;
import com.example.demo.message.GeneralMessage;
import com.example.demo.message.NicknameMessage;
import com.example.demo.model.Nickname;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
public class NicknameController {
    final Web3j web3j;

    public NicknameController(Web3j web3j) {
        this.web3j = web3j;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/nickname")
    public NicknameMessage getNicknames(@RequestParam("address") String address) {
        ResultSet set = NicknameDriver.GetNicknames(address);
        NicknameMessage msg = new NicknameMessage();
        msg.setData(new ArrayList<>());
        try {
            if (set != null) {
                while (set.next()) {
                    Nickname nickname = new Nickname();
                    nickname.setObligor(set.getString(1));
                    nickname.setAddress(set.getString(2));
                    nickname.setNickname(set.getString(3));
                    msg.getData().add(nickname);
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
    @PostMapping("/nickname")
    public GeneralMessage setNickname(@RequestParam("privateKey") String privateKey,
                                             @RequestParam("address") String address,
                                             @RequestParam("nickname") String nickname) {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(privateKey);
        String obligor = credentials.getAddress();
        GeneralMessage msg = new GeneralMessage();
        try {
            boolean result = NicknameDriver.SetNickname(obligor, address, nickname);
            if (result) {
                msg.setMessage("success");
            } else {
                msg.setMessage("duplicate");
            }
        } catch (Exception ex) {
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
        }
        return msg;
    }
}
