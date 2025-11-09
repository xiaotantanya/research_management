package xyz.management.project.library.vo;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class PatentVO extends ResearchItem{
    private LocalDate applicationDate;
    private LocalDate authorizationDate;
}
