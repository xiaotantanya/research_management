package xyz.management.project.library.vo;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class PaperVO extends ResearchItem{
    private String publicateLocation;
    private boolean sci;
    private boolean ei;
    private String ccf;
    private String jcr;
    private LocalDate publicateDate;
}
