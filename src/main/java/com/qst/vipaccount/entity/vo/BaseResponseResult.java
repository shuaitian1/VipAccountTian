package com.qst.vipaccount.entity.vo;

import lombok.Data;

@Data
public class BaseResponseResult {

    private String result_code = ResponseResultCode.SUCCESS.getCode();

    private String error_message = ResponseResultCode.SUCCESS.getMessage();

    private Object data;

    public BaseResponseResult() {

    }

    public BaseResponseResult(ResponseResultCode mcsResponseResultCode) {
        setResultCode(mcsResponseResultCode);
    }

    public void setResultCode(ResponseResultCode mcsResponseResultCode) {
        this.result_code = mcsResponseResultCode.getCode();
        this.error_message = mcsResponseResultCode.getMessage();
    }

}
