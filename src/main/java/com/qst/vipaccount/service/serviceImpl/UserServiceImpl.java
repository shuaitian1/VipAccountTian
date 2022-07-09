package com.qst.vipaccount.service.serviceImpl;

import com.qst.vipaccount.dao.IUserDao;
import com.qst.vipaccount.entity.bo.User;
import com.qst.vipaccount.entity.dto.AddUserRequestParams;
import com.qst.vipaccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User findUserByToken(String token) {
        return userDao.findUserByToken(token);
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        return userDao.findUserByUserNameAndPassword(userName, password);
    }

    @Override
    public String createAndInsertToken(String userId) {
        //用UUID生成token
        String token = UUID.randomUUID().toString();
        //保存到数据库
        userDao.createAndInsertToken(token, userId);
        return token;
    }

    @Override
    public void logout(String token) {
        userDao.logout(userDao.findUserByToken(token).getUserId());
    }

    @Override
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Override
    public int addUser(AddUserRequestParams addUserRequestParams) {

        String password = addUserRequestParams.getPassword();
        String userName = addUserRequestParams.getUserName();
        long userPhoneNumber = addUserRequestParams.getUserPhoneNumber();
        String userAddress = addUserRequestParams.getUserAddress();
        String userBirthday = addUserRequestParams.getUserBirthday();
        int userSex = addUserRequestParams.getUserSex();

        return userDao.addUser(password, userName, userPhoneNumber, userAddress, userBirthday, userSex);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public int updateUserInfoByUserId(AddUserRequestParams addUserRequestParams) {

        String userId = addUserRequestParams.userId;
        String password = addUserRequestParams.getPassword();
        long userPhoneNumber = addUserRequestParams.getUserPhoneNumber();
        String userAddress = addUserRequestParams.getUserAddress();
        String userBirthday = addUserRequestParams.getUserBirthday();
        int userSex = addUserRequestParams.getUserSex();

        return userDao.updateUserInfoByUserId(userId, password, userPhoneNumber, userAddress, userBirthday, userSex);
    }
}
