package com.qst.vipaccount.service;

import com.qst.vipaccount.entity.bo.User;
import com.qst.vipaccount.entity.dto.AddUserRequestParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findUserByToken(String token);

    User findUserByUserNameAndPassword(String userName, String password);

    String createAndInsertToken(String userId);

    void logout(String token);

    User findUserByUserName(String userName);

    int addUser(AddUserRequestParams addUserRequestParams);

    List<User> findAllUser();

    int updateUserInfoByUserId(AddUserRequestParams addUserRequestParams);
}
