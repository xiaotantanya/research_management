package xyz.management.project.library.entity;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectArrival {
    private int arrivalId;
    private int projectId;
    private Date arrivalTime;
    private BigDecimal arrivalAmount;
}
