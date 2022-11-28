package org.example.clickhouse;

import cn.hutool.core.lang.Assert;
import com.clickhouse.jdbc.ClickHouseDataSource;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 封装一下clickhouse的数据源，否则当引入常规数据源之后系统中会存在两个数据源，
 * 在处理事务和mybatis加载的时候就比较麻烦
 */
@Component
public class ClickHouseDataSourceWrapper implements InitializingBean {

    @Autowired
    private DemoProperties demoProperties;

    @Getter
    private DataSource originDataSource;
    @Getter
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.originDataSource = new ClickHouseDataSource(demoProperties.getUrl(), demoProperties.getProperties());
        this.jdbcTemplate = new JdbcTemplate(originDataSource);

        healthCheck();
    }

    private void healthCheck() {
        Integer result = this.jdbcTemplate.query("select 1", new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getInt(1);
            }
        });
        Assert.equals(result, 1);
    }
}
