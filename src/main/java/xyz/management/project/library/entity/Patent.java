package xyz.management.project.library.entity;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Patent extends ResearchItem {

    // (!! 来自需求 "申请号"
    private String applicationNumber;

    // (!! 来自需求 "授权号"
    private String authorizationNumber;

    // (!! 来自需求 "申请时间"
    private LocalDate applicationDate;

    // (!! 来自需求 "授权时间"
    private LocalDate authorizationDate;
}
