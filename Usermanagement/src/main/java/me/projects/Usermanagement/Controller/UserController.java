package me.projects.Usermanagement.Controller;

import me.projects.Usermanagement.DTo.UserRequestDTo;
import me.projects.Usermanagement.DTo.UserResponseDTO;
import me.projects.Usermanagement.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> SaveUser(
            @RequestBody UserRequestDTo userRequestDTo){
        String ProviderSubject=userRequestDTo.getProviderSubject();
        UserResponseDTO userResponseDTO=userService.RegisterAndUpdateUser(ProviderSubject, userRequestDTo);
        return ResponseEntity.ok(userResponseDTO);
    }
}
