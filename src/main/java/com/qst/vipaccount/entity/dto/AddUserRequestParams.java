package com.qst.vipaccount.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddUserRequestParams extends BaseRequestParams {

    public String userId;
    public String password;
    public String userName;
    public long userPhoneNumber;
    public String userAddress;
    public String userBirthday;
    public int userSex;
    public int userCode;

    public String token;
}
