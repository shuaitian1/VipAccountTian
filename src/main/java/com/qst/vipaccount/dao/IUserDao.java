package com.qst.vipaccount.dao;

import com.qst.vipaccount.entity.bo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IUserDao {

    @Update({"<script>",
            "update t_user_info",
            "<set>",
            "token = #{token},",
            "</set>",
            "where user_id = #{userId}",
            "</script>"})
    void createAndInsertToken(String token, String userId);

    @Select({"<script>",
            "select * from t_user_info where user_name = #{userName} and password = #{password}",
            "</script>"})
    User findUserByUserNameAndPassword(String userName, String password);

    @Select({"<script>",
            "select * from t_user_info where token = #{token}",
            "</script>"})
    User findUserByToken(String token);

    @Update({"<script>",
            "update t_user_info",
            "<set>",
            "token = '',",
            "</set>",
            "where user_id = #{userId}",
            "</script>"})
    void logout(@Param("userId") String userId);

    @Select({"<script>",
            "select * from t_user_info where user_name = #{userName}",
            "</script>"})
    User findUserByUserName(String userName);

    @Insert({"<script>",
            "insert into t_user_info(password,user_name,user_phone_number,user_address,user_birthday,user_sex)",
            "values",
            "(#{password},#{userName},#{userPhoneNumber},#{userAddress},#{userBirthday},#{userSex})",
            "</script>"})
    int addUser(String password, String userName, long userPhoneNumber, String userAddress,
                String userBirthday, int userSex);

    @Select({"<script>",
            "select * from t_user_info where user_lever = 1",
            "</script>"})
    List<User> findAllUser();

    @Update({"<script>",
            "update t_user_info",
            "<set>",
            "<if test = 'password != null'>",
            "password = #{password},",
            "</if>",
            "<if test = 'userPhoneNumber != null'>",
            "user_phone_number = #{userPhoneNumber},",
            "</if>",
            "<if test = 'userAddress != null'>",
            "user_address = #{userAddress},",
            "</if>",
            "<if test = 'userSex != null'>",
            "user_sex = #{userSex},",
            "</if>",
            "</set>",
            "where user_id = #{userId}",
            "</script>"})
    int updateUserInfoByUserId(String userId, String password, long userPhoneNumber,
                               String userAddress, String userBirthday, int userSex);

    @Update({"<script>",
            "update t_user_info",
            "<set>",
            "user_code = #{userCode},",
            "</set>",
            "where user_name = #{userName}",
            "</script>"})
    void increaseCodeByUserName(String userName, double userCode);
}
