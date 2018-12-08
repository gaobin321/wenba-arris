package com.wenba.arris.service.user.impl;

import com.wenba.arris.dao.UserMapper;
import com.wenba.arris.domain.User;
import com.wenba.arris.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostConstruct
    public void init() {
        log.info("testsss");
    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserById(Integer userId) {
        User arrisInfo = userMapper.selectByPrimaryKey(userId);
        return arrisInfo;
        //        return new User(userId, "Hello kitty: MOCK");
    }
}
