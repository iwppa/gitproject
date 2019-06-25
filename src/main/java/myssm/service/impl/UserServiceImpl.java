package myssm.service.impl;


import myssm.bean.User;
import myssm.mapper.UserMapper;
import myssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User login(String username,String password) {
        return userMapper.login(username,password);
    }

    @Override
    public User findUserByUid(String uid) {
        return  userMapper.findUserByUid(uid);
    }
}
