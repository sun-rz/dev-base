package dev.base.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommonEntityMapper {

    List<Map<String,Object>> getColumnsBySql(Map<String,Object> map);

    @Insert("insert into [user](userName, password, realName) values (#{userName},#{password},#{realName})")
    int insertBySql(String userName,String password,String realName);

}
