package me.projects.Transactions.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private BigDecimal amount;
    private LocalDateTime localDateTime;
    private String status;

    public Transactions(Long userid, BigDecimal amount, LocalDateTime localDateTime, String status) {
        this.userid = userid;
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.status = status;
    }
}
