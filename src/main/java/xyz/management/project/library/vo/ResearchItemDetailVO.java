package xyz.management.project.library.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.management.project.library.entity.Attachment;
import xyz.management.project.library.entity.PaymentRecord;
import xyz.management.project.library.enumUtil.ItemType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResearchItemDetailVO {

    private String id;
    private String name;
    private ItemType type;
    // project
    private String projectType;
    private BigDecimal amount;
    private LocalDate starDate;
    private LocalDate endDate;
    private PaymentRecord[] paymentRecords;
    private String projectId;
    private String projectCategory;
    private String projectSubCategory;
    private String personInCharge;
    // patent
    private String applicationNumber;
    private String authorizationNumber;
    private LocalDate applicationDate;
    private LocalDate authorizationDate;
    // paper
    private String journalOrConferenceName;
    private boolean isSci;
    private boolean isEi;
    private String ccfRating;
    private String jcrRating;
    private LocalDate publicationDate;
    private String notes;

    private Attachment homepageFile;
    private Attachment fulltextFile;
    private List<Attachment> otherFiles = new ArrayList<>();
}
