package me.projects.AICodeTesting.Dto.TestingAuditDTO;

import jakarta.persistence.Entity;
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
public class TestingAuditDTO{
    @Id
    private String username;
    private LocalDateTime datetime;
    private BigDecimal CreditUsed;
}
