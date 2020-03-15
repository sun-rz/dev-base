package dev.base.service;

import dev.base.datasource.DataSource;
import dev.base.datasource.DataSourceEnum;
import dev.base.entity.User;
import dev.base.mapper.CommonEntityMapper;
import dev.base.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonEntityMapper commonEntityMapper;

    @Transactional
    public User getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }

    @Transactional
    public User login(String userName, String passWord) {
        return userMapper.login(userName, passWord);
    }

    @Transactional
    public int register(String userName, String passWord) {
        return userMapper.register(userName, passWord);
    }

    @Transactional
    public List getListBySql(String sql){
        Map map=new HashMap();
        map.put("sql",sql);
        return commonEntityMapper.getColumnsBySql(map);
    }
}