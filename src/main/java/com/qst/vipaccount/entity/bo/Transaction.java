package com.qst.vipaccount.entity.bo;

import lombok.Data;

@Data
public class Transaction {

    public int transactionId;
    public String userName;
    public String goodsName;
    public double goodsAmount;
    public String transactionTime;

}
