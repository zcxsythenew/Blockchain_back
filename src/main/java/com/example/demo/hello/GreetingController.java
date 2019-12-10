package com.example.demo.hello;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;

@RestController
public class GreetingController {
    final Web3j web3j;

    public GreetingController(Web3j web3j) {
        this.web3j = web3j;
    }

    @RequestMapping("/")
    public Greeting greeting(@RequestParam(value="name")String name) {
        return new Greeting(0, name);
    }

    @RequestMapping("/blocknumber")
    public BigInteger blockNumber() {
        BigInteger blockNumber;
        try {
            blockNumber = web3j.getBlockNumber().send().getBlockNumber();
        } catch (Exception ex) {
            blockNumber = new BigInteger("0");
        }
        return blockNumber;
    }
}
