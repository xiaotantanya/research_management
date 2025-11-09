package xyz.management.project.library.new_vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.management.project.library.entity.PaymentRecord;
import xyz.management.project.library.enumUtil.ProjectType;

@Data
@EqualsAndHashCode(callSuper = true) // (!!) 确保 Gette/Setter 包含父类字段
public class ProjectVO extends ResearchItem {
    // (!!) 来自图片 "项目性质"
    private ProjectType projectType;

    // (!!) 来自图片 "项目总经费"
    private BigDecimal amount;

    // (!! 来自图片 "开始时间"
    private LocalDate startDate;

    // (!! 来自图片 "结束时间"
    private LocalDate endDate;

    // (!!) 来自图片 "项目ID" (使用不同变量名以区别主键)
    private String projectId;

    // (!! 来自图片 "项目批准号"
    private String approvalNumber;

    // (!! 来自图片 "经费卡号"
    private String cardNumber;

    // (!! 来自图片 "项目类别"
    private String projectCategory;

    // (!! 来自图片 "负责人"
    private String fundingPerson;

    // (!! 核心 !!) 一对多到账记录
    // Mybatis 将使用 project.id 
    // 去查询 payment_record.project_id
    private List<PaymentRecord> paymentRecords = new ArrayList<>();
}
