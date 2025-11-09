package xyz.management.project.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OtherItem extends ResearchItem {

    // (!! 来自需求 "备注字段"
    private String notes;
}
