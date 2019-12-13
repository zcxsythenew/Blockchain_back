# 区块链大作业后端部分

17343116 吴国璋

## 主要技术

Spring Boot

MySQL

FISCO BCOS Java SDK

## 项目搭建

```shell
./mvnw compile
```

## 项目运行

```shell
./mvnw spring-boot:run
```

## 生成jar

```shell
./mvnw package
```

## 项目结构

package com.example.demo.configure：包含 FISCO BCOS Java SDK 连接到节点所需的代码。

package com.example.demo.constants：包含连接节点和连接数据库所需的常量。

package com.example.demo.contract：Solidity 合约编译成 Java 后的代码。

package com.example.demo.controller：RESTful 控制器。接收前端发来的 QueryString，然后返回 JSON 数据到前端。

package com.example.demo.database：处理 MySQL 的增删改查。MySQL 保存银行地址、合约地址、用户的名称表等数据，以及作为链端的缓存。

package com.example.demo.message：controller 返回 JSON 数据到前端所需的结构。通常包括一个 message 和一个 data。

package com.example.demo.model：要用到的基本数据格式，如交易凭据的格式、User的格式、Nickname的格式等。作为 message 的 data，也作为 database 要用到的数据结构。

本项目暂时没有编写测试文件。test 目录是 Spring Initializer 自动生成的。

## 合约代码

链端的 Solidity 代码如下：

```solidity
pragma solidity ^0.4.22;

contract Money {
    struct Receipt {
        address obligor;
        address obligee;
        string datetime;
        uint amount;
        bool verified;
        uint8 transfer;
        address transferTo;
        // string transferDatetime;
        bool transferVerified;
        bool discount;
        bool discountVerified;
        // string discountDatetime;
        bool settle;
        bool settleVerified;
        // string settleDatetime;
        string note;
    }
    
    struct Transfer {
        uint a;
        uint b;
    }
    
    uint public receipt_counter;
    uint public transfer_counter;
    address public issuer;
    Receipt[] public receipts;
    Transfer[] public transfers;
    
    constructor() public {
        issuer = msg.sender;
        receipt_counter = 0;
        transfer_counter = 0;
    }
    
    function Purchase(string datetime, address obligee, uint amount, string reason) public returns (uint receipt_index) {
        Receipt memory receipt = Receipt(
            msg.sender,
            obligee,
            datetime,
            amount,
            false,
            0,
            0,
            // "",
            false,
            false,
            false,
            // "",
            false,
            false,
            // "",
            reason
            );
        receipts.push(receipt);
        receipt_index = receipt_counter;
        receipt_counter++;
    }
    
    function VerifyPurchase(uint index) public returns (bool success) {
        if (index < receipt_counter && issuer == msg.sender) {
            receipts[index].verified = true;
            success = true;
        } else {
            success = false;
        }
    }
    
    function TransferTwoReceipts(uint a, uint b/*, string datetime*/) public returns (bool success, uint transfer_index) {
        if (a >= receipt_counter || b >= receipt_counter) {
            success = false;
            return;
        }
        
        bool va = receipts[a].obligor == receipts[b].obligee;
        bool vb = receipts[b].obligor == receipts[a].obligee;
        bool vc = receipts[a].amount == receipts[b].amount;
        bool statusValid = receipts[a].verified
        && receipts[b].verified
        && receipts[a].transfer == 0
        && receipts[b].transfer == 0
        && !receipts[a].discount
        && !receipts[b].discount
        && !receipts[a].settle
        && !receipts[b].settle;
        bool userValid;
        
        if (va) {
            userValid = receipts[a].obligor == msg.sender;
        }
        
        if (vb) {
            userValid = receipts[b].obligor == msg.sender;
        }
        
        if ((va || vb) && !(va && vb) && vc && statusValid && userValid) {
            if (va) {
                receipts[a].transfer = 1;
                receipts[b].transfer = 2;
                receipts[a].transferTo = receipts[b].obligor;
                receipts[b].transferTo = receipts[a].obligee;
            } else {
                receipts[a].transfer = 2;
                receipts[b].transfer = 1;
                receipts[a].transferTo = receipts[b].obligee;
                receipts[b].transferTo = receipts[a].obligor;
            }
            // receipts[a].transferDatetime = datetime;
            // receipts[b].transferDatetime = datetime;
            
            Transfer memory transfer = Transfer(a, b);
            transfers.push(transfer);
            
            transfer_index = transfer_counter;
            transfer_counter++;
            success = true;
        } else {
            success = false;
        }
    }
    
    function VerifyTransfer(uint index) public returns (bool success) {
        if (index < transfer_counter && issuer == msg.sender
        && receipts[transfers[index].a].transfer != 0
        && receipts[transfers[index].b].transfer != 0) {
            receipts[transfers[index].a].transferVerified = true;
            receipts[transfers[index].b].transferVerified = true;
            success = true;
        } else {
            success = false;
        }
    }
    
    function CancelTransfer(uint index) public returns (bool success) {
        if (index < transfer_counter && issuer == msg.sender
        && !receipts[transfers[index].a].transferVerified
        && !receipts[transfers[index].b].transferVerified) {
            receipts[transfers[index].a].transfer = 0;
            receipts[transfers[index].b].transfer = 0;
            success = true;
        } else {
            success = false;
        }
    }
    
    function Discount(uint index/* , string datetime*/) public returns (bool success) {
        bool userValid = index < receipt_counter && receipts[index].obligee == msg.sender && receipts[index].transfer != 2;
        bool statusValid = index < receipt_counter
        && receipts[index].verified
        && (receipts[index].transfer == 0 || receipts[index].transferVerified)
        && !receipts[index].discount
        && !receipts[index].settle;
        
        if (userValid && statusValid) {
            receipts[index].discount = true;
            // receipts[index].discountDatetime = datetime;
            success = true;
        } else {
            success = false;
        }
    }
    
    function VerifyDiscount(uint index) public returns (bool success) {
        if (index < receipt_counter && issuer == msg.sender && receipts[index].discount) {
            receipts[index].discountVerified = true;
            success = true;
        } else {
            success = false;
        }
    }
    
    function CancelDiscount(uint index) public returns (bool success) {
        if (index < receipt_counter && issuer == msg.sender && !receipts[index].discountVerified) {
            receipts[index].discount = false;
            success = true;
        } else {
            success = false;
        }
    }
    
    function Settle(uint index/*, string datetime*/) public returns (bool success) {
        bool userValid = index < receipt_counter
        && receipts[index].obligee == msg.sender
        && receipts[index].transfer != 2;
        
        bool statusValid = index < receipt_counter
        && receipts[index].verified
        && (receipts[index].transfer == 0 || receipts[index].transferVerified)
        && (!receipts[index].discount || receipts[index].discountVerified)
        && !receipts[index].settle;
        
        if (userValid && statusValid) {
            receipts[index].settle = true;
            // receipts[index].settleDatetime = datetime;
            success = true;
        } else {
            success = false;
        }
    }
    
    function VerifySettle(uint index) public returns (bool success) {
        if (index < receipt_counter && issuer == msg.sender && receipts[index].settle) {
            receipts[index].settleVerified = true;
            success = true;
        } else {
            success = false;
        }
    }
    
    function CancelSettle(uint index) public returns (bool success) {
        if (index < receipt_counter && issuer == msg.sender && !receipts[index].settleVerified) {
            receipts[index].settle = false;
            success = true;
        } else {
            success = false;
        }
    }
}
```
