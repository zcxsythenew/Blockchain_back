package com.example.demo.model;

import java.math.BigInteger;

public class Transaction {
    public int id;
    public String obligor;
    public String obligee;
    public String datetime;
    public BigInteger amount;
    public boolean verified;
    public int transfer;
    public String transferTo;
    public boolean transferVerified;
    public boolean discount;
    public boolean discountVerified;
    public boolean settle;
    public boolean settleVerified;
    public String note;

    public String obligorNickname;
    public String obligeeNickname;
    public String transferToNickname;

    public int transferId;

    public int getId() {
        return id;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public int getTransfer() {
        return transfer;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getNote() {
        return note;
    }

    public String getObligee() {
        return obligee;
    }

    public String getObligor() {
        return obligor;
    }

    public String getTransferTo() {
        return transferTo;
    }

    public String getObligeeNickname() {
        return obligeeNickname;
    }

    public String getObligorNickname() {
        return obligorNickname;
    }

    public String getTransferToNickname() {
        return transferToNickname;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public void setDiscountVerified(boolean discountVerified) {
        this.discountVerified = discountVerified;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setObligee(String obligee) {
        this.obligee = obligee;
    }

    public void setObligor(String obligor) {
        this.obligor = obligor;
    }

    public void setSettle(boolean settle) {
        this.settle = settle;
    }

    public void setSettleVerified(boolean settleVerified) {
        this.settleVerified = settleVerified;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo;
    }

    public void setTransferVerified(boolean transferVerified) {
        this.transferVerified = transferVerified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isDiscount() {
        return discount;
    }

    public boolean isDiscountVerified() {
        return discountVerified;
    }

    public boolean isSettle() {
        return settle;
    }

    public boolean isSettleVerified() {
        return settleVerified;
    }

    public boolean isTransferVerified() {
        return transferVerified;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setObligeeNickname(String obligeeNickname) {
        this.obligeeNickname = obligeeNickname;
    }

    public void setObligorNickname(String obligorNickname) {
        this.obligorNickname = obligorNickname;
    }

    public void setTransferToNickname(String transferToNickname) {
        this.transferToNickname = transferToNickname;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }
}
