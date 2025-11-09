package xyz.management.project.library.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String id;
    private String projectType;
    private BigDecimal amount;
    private LocalDate starDate;
    private LocalDate endDate;
    private String projectId;
    private String approvalNumber;
    private String cardNumber;
    private String projectCategory;
    private String fundingPerson;
}
