package me.projects.Usermanagement.DTo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredituseDTO {
    private String Userid;
    private BigDecimal Credit;
}
