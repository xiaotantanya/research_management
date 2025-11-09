package xyz.management.project.library.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import xyz.management.project.library.entity.Attachment;
import xyz.management.project.library.entity.PaymentRecord;
import xyz.management.project.library.enumUtil.ItemType;
import xyz.management.project.library.enumUtil.ProjectType;

@Data
public class ResearchItemDTO {

    // (公共字段)
    private String id;
    private String name;
    private ItemType type;
    private int listOrder;

    // (附件) - (!! 关键 !!)
    // DTO 只包含*已存在*的附件信息。新文件通过 MultipartFile 单独上传。
    private Attachment homepageFile;
    private Attachment fulltextFile;
    private List<Attachment> otherFiles;

    // (项目字段)
    private ProjectType projectType;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<PaymentRecord> paymentRecords;
    private String projectIdStr; // (!! 注意: 前端字段名是 projectId, 后端模型是 projectIdStr !!)
    private String approvalNumber;
    private String fundingCardNumber;
    private String projectCategory;
    private String projectSubCategory;
    private String personInCharge;

    // (专利字段)
    private String applicationNumber;
    private String authorizationNumber;
    private LocalDate applicationDate;
    private LocalDate authorizationDate;

    // (论文字段)
    private String journalOrConferenceName;
    private boolean isSCI;
    private boolean isEI;
    private String ccfRating;
    private String jcrRating;
    private LocalDate publicationDate;

    // (其他字段)
    private String notes;
}
