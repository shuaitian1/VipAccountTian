package com.qst.vipaccount.service.serviceImpl;

import com.qst.vipaccount.dao.ITransactionDao;
import com.qst.vipaccount.dao.IUserDao;
import com.qst.vipaccount.entity.bo.Transaction;
import com.qst.vipaccount.entity.bo.User;
import com.qst.vipaccount.entity.dto.AddTransactionRequestParams;
import com.qst.vipaccount.service.TransactionService;
import com.qst.vipaccount.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    ITransactionDao transactionDao;


    @Autowired
    IUserDao userDao;

    @Override
    public int addTransaction(AddTransactionRequestParams addTransactionRequestParams) {

        String userName = addTransactionRequestParams.getUserName();
        String goodsName = addTransactionRequestParams.getGoodsName();
        double goodsAmount = addTransactionRequestParams.getGoodsAmount();
        String transactionTime = String.valueOf(System.currentTimeMillis());
        double codeChange;

        User user = userDao.findUserByUserName(userName);

        String nowTime = DateUtils.FileTimeStamp2Date(transactionTime);
        String birthTime = DateUtils.FileTimeStamp2Date(user.getUserBirthday());

        // 对比当前时间是否是消费者生日
        if (nowTime.substring(5, 11).equalsIgnoreCase(birthTime.substring(5, 11))) {
            codeChange = goodsAmount * 2;
        } else {
            codeChange = goodsAmount;
        }
        double userCode = user.userCode + codeChange;
        userDao.increaseCodeByUserName(userName, userCode);
        return transactionDao.addTransaction(userName, goodsName, goodsAmount, transactionTime, codeChange);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionDao.getAllTransaction();
    }

}
