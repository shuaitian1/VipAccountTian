package com.qst.vipaccount.controller;

import com.qst.vipaccount.entity.dto.AddTransactionRequestParams;
import com.qst.vipaccount.entity.vo.BaseResponseResult;
import com.qst.vipaccount.entity.vo.ResponseResultCode;
import com.qst.vipaccount.service.TransactionService;
import com.qst.vipaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.StringUtils.isEmpty;


@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Transactional
    @PostMapping("/addTransaction")
    public BaseResponseResult addTransaction(@RequestBody AddTransactionRequestParams addTransactionRequestParams) {
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        try {
            if (!isEmpty(addTransactionRequestParams)) {
                if (!isEmpty(userService.findUserByUserName(addTransactionRequestParams.userName))) {
                    if (transactionService.addTransaction(addTransactionRequestParams) == 0) {
                        baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_ERROR);
                    } else {
                        baseResponseResult.setResultCode(ResponseResultCode.SUCCESS);
                    }
                } else {
                    baseResponseResult.setResultCode(ResponseResultCode.ACCOUNT_NO_EXIST);
                }
            } else {
                baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_PARAM_ERROR);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_ERROR);
        }
        return baseResponseResult;
    }

    @PostMapping("getAllTransaction")
    public BaseResponseResult getAllTransaction() {
        BaseResponseResult baseResponseResult = new BaseResponseResult();
        try {
            baseResponseResult.setData(transactionService.getAllTransaction());
            baseResponseResult.setResultCode(ResponseResultCode.SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            baseResponseResult.setResultCode(ResponseResultCode.SYSTEM_ERROR);
        }
        return baseResponseResult;
    }


}
