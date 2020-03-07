package dev.base.service;

import dev.base.datasource.DataSource;
import dev.base.datasource.DataSourceEnum;
import dev.base.entity.User;
import dev.base.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional
    @DataSource(value = DataSourceEnum.SLAVE)
    public List Sel(int id){
        return userMapper.Sel(id);
    }

    @Transactional
    @DataSource(value = DataSourceEnum.SLAVE)
    public User login(String userName, String passWord) {
        return userMapper.login(userName,passWord);
    }

    public int register(User user) {
        return userMapper.register(user);
    }
}