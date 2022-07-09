package com.qst.vipaccount.entity.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {

    public String userId;

    @JsonIgnore
    public String password;

    public String userName;
    public long userPhoneNumber;
    public String userAddress;
    public String userBirthday;
    public int userSex;
    public double userCode;
    public int userLever;

    public String token;
}
