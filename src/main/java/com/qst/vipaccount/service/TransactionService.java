package com.qst.vipaccount.service;

import com.qst.vipaccount.entity.bo.Transaction;
import com.qst.vipaccount.entity.dto.AddTransactionRequestParams;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    int addTransaction(AddTransactionRequestParams addTransactionRequestParams);

    List<Transaction> getAllTransaction();
}
