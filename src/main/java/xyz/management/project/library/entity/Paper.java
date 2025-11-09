package xyz.management.project.library.entity;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Paper extends ResearchItem {

    // (!! 来自图片 "期刊/会议名称"
    private String journalOrConferenceName;

    // (!! 来自图片 "是否是SCI"
    private boolean isSCI;

    // (!! 来自图片 "是否是EI"
    private boolean isEI;

    // (!! 来自图片 "CCF"
    private String ccfRating; // e.g., "A", "B", "C"

    // (!! 来自图片 "JCR"
    private String jcrRating; // e.g., "Q1", "Q2"

    // (!! 核心 !!) (为支持日期筛选而必需)
    private LocalDate publicationDate;
}
