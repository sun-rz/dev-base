package dev.base.service;

import dev.base.datasource.DataSource;
import dev.base.datasource.DataSourceEnum;
import dev.base.mapper.CommonEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class CommonEntityService {

    @Autowired
    private CommonEntityMapper commonEntityMapper;

    @Transactional
    @DataSource(value = DataSourceEnum.MASTER)
    public List getInfoBySql(String sql) {
        Map<String, Object> params = new HashMap<>();
        params.put("sql", sql);
        //params.put("code","1%");
        List<Map<String, Object>> ans = commonEntityMapper.getColumnsBySql(params);


        return ans;
    }

    @DataSource(value = DataSourceEnum.MASTER)
    public int inserts(List<Map<String, Object>> ans) {
        if (Objects.nonNull(ans) && ans.size() > 0) {
            commonEntityMapper.insertBySql(ans.get(0).get("realName") + "", ans.get(0).get("password") + "", ans.get(0).get("userName") + "");
        }

        return 1;
    }
}
