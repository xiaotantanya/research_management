package xyz.management.project.library.new_entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paper {
    private String id;
    private String publicateLocation;
    private boolean sci;
    private boolean ei;
    private String ccf;
    private String jcr;
    private LocalDate publicateDate;
}
