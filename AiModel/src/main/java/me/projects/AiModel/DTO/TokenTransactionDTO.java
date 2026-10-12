package me.projects.AiModel.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class TokenTransactionDTO {
        private String userid;
        private BigDecimal amount;
        private LocalDateTime localDateTime;
        private String status;
        private boolean isDeleted=false;
}
