package xyz.management.project.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class LibraryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplateConnection() {
        try {
            // 执行简单查询（查询 MySQL 版本）
            String sql = "SELECT VERSION()";
            String mysqlVersion = jdbcTemplate.queryForObject(sql, String.class);

            System.out.println("\n=== JdbcTemplate 连接测试结果 ===");
            System.out.println("MySQL 版本：" + mysqlVersion);
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            System.err.println("\n=== JdbcTemplate 连接失败 ===");
            e.printStackTrace();
            throw e; // 抛出异常，让测试失败
        }
    }
}
