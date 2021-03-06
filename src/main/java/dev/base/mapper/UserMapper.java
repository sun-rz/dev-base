package dev.base.mapper;

import dev.base.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User getUserInfo(int id);

    User login(String userName, String passWord);

    int register(String userName, String passWord);
}