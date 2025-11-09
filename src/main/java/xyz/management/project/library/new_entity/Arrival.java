package xyz.management.project.library.new_entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arrival {
    private String id;
    private String projectId;
    private LocalDate arrivalTime;
    private BigDecimal arrivalAmount;
}
