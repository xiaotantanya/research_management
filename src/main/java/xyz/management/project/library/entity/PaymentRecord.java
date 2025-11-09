package xyz.management.project.library.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentRecord {

    private String id; // UUID

    // (!! 核心 !!) 使用 BigDecimal 来精确处理货币
    private BigDecimal amount;

    private LocalDate date;

    // (!! 核心 !!) 反向关联 Project 的 ID
    private String projectId;
}
