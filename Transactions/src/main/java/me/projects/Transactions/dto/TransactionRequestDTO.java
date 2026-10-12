package me.projects.Transactions.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class TransactionRequestDTO {
    private String userid;
    private BigDecimal amount;
    private LocalDateTime localDateTime;
    private String status;
    private boolean isDeleted;
    public TransactionRequestDTO(String userid, BigDecimal amount, LocalDateTime localDateTime, String status) {
        this.userid = userid;
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.status = status;
        this.isDeleted=false;
    }
}
