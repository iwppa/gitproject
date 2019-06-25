package myssm.mapper;

import myssm.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int addUser(@Param("user") User user);

    User login(@Param("username") String username,@Param("password") String password);

    User findUserByUid(@Param("uid") String uid);

}
