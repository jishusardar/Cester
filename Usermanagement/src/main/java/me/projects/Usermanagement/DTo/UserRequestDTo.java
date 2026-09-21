package me.projects.Usermanagement.DTo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTo {
    private String Name;
    private String UserEmail;
    private String providerSubject;
}
