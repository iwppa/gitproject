package myssm.service;

import myssm.bean.User;

public interface UserService {

    int addUser(User user);

    User login(String username,String password);

    User findUserByUid(String uid);
}
