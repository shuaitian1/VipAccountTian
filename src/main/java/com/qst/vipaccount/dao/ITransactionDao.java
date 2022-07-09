package com.qst.vipaccount.dao;

import com.qst.vipaccount.entity.bo.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface ITransactionDao {

    @Insert({"<script>",
            "insert into t_transaction_info(transaction_id,user_name,goods_amount,goods_name,transaction_time,code_change)",
            "values",
            "(null,#{userName},#{goodsAmount},#{goodsName},#{transactionTime},#{codeChange})",
            "</script>"})
    int addTransaction(String userName, String goodsName, double goodsAmount, String transactionTime, double codeChange);

    @Select({"<script>",
            "select * from t_transaction_info",
            "</script>"})
    List<Transaction> getAllTransaction();
}
