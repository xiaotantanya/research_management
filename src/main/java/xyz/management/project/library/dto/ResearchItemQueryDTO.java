package xyz.management.project.library.dto;

import java.io.Serializable;

import lombok.Data;

@Data // Lombok 注解，自动生成 getter/setter/toString，简化代码
public class ResearchItemQueryDTO implements Serializable {
    // 对应前端 condition 字段，与前端接口字段完全一致
    private String searchQuery;    // 模糊查询关键词
    private String type;           // 类型筛选（PROJECT/PATENT/等）
    private String projectType;    // 项目类型筛选
    private String startDate;      // 开始日期
    private String endDate;        // 结束日期
}