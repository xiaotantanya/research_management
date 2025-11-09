package xyz.management.project.library.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patent {
    private String id;
    private LocalDate applicationDate;
    private LocalDate authorizationDate;
}
