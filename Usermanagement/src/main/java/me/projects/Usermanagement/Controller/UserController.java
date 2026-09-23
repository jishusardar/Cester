package me.projects.Usermanagement.Controller;

import me.projects.Usermanagement.DTo.UserCredituseDTO;
import me.projects.Usermanagement.DTo.UserRequestDTo;
import me.projects.Usermanagement.DTo.UserResponseDTO;
import me.projects.Usermanagement.Entity.User;
import me.projects.Usermanagement.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> SaveUser(
            @RequestHeader("X-Microservice-Name") String microservice,
            @RequestBody UserRequestDTo userRequestDTo){
        String ProviderSubject=userRequestDTo.getProviderSubject();
        UserResponseDTO userResponseDTO=userService.RegisterAndUpdateUser(ProviderSubject, userRequestDTo);
        return ResponseEntity.ok(userResponseDTO);
    }
    @GetMapping("/{providerSubject}")
    public ResponseEntity<String> getUserBySubject(@PathVariable String providerSubject){
        boolean isExist = userService.findUserBySubject(providerSubject);

        if (!isExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not present");
        }

        return ResponseEntity.ok("User Present");
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> SoftDeleteUser(@PathVariable Long id){
        UserResponseDTO userResponseDTO=userService.SoftDeleteUsers(id);
        return ResponseEntity.ok(userResponseDTO);
    }
    @GetMapping("/credits")
    public BigDecimal getUserCredits(@RequestHeader("X-User-Auth") String subject){
        return userService.getUserCredits(subject);
    }
    @PutMapping("/credits/removed")
    public void subtractUserCredit(
            @RequestHeader("X-Microservice-Name") String microservice,
            @RequestHeader("X-User-Auth") String subject,
            @RequestBody UserCredituseDTO userCredituseDTO){
        userService.subtractUserCredit(userCredituseDTO);
    }
    @PutMapping("/credits/add")
    public void AddUserCredits(
            @RequestHeader("X-Microservice-Name") String microservice,
            @RequestHeader("X-User-Auth") String subject,
            @RequestBody UserCredituseDTO userCredituseDTO){
        userService.AddUserCredits(userCredituseDTO);
    }
}
