package me.projects.AICodeTesting.Service.External;

import me.projects.AICodeTesting.Dto.RequestDTO.UserCredituseDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserRequestDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "USERMANAGEMENT")
public interface UserService {

    @PostMapping("/api/users/save")
    UserResponseDTO SaveUser(
            @RequestHeader("X-Microservice-Name") String microservice,
            @RequestBody UserRequestDTO userRequestDTO);

    @GetMapping("/api/users/{usersubject}")
    boolean getUserBySubject(
            @RequestHeader("X-Microservice-Name") String microservice,
            @RequestHeader("X-User-Auth") String subject,
            @PathVariable String usersubject);

    @GetMapping("/api/users/credits")
    BigDecimal getUserCredits(
            @RequestHeader("X-Microservice-Name") String microservice,
            @RequestHeader("X-User-Auth") String subject
    );
    @PutMapping("/api/users/credits/removed")
    void subtractUserCredit(@RequestHeader("X-Microservice-Name") String microservice,
                            @RequestHeader("X-User-Auth") String subject,
                            @RequestBody UserCredituseDTO userCredituseDTO);
    @PutMapping("/api/users/credits/add")
    void AddUserCredits(@RequestHeader("X-Microservice-Name") String microservice,
                        @RequestHeader("X-User-Auth") String subject,
                        @RequestBody UserCredituseDTO userCredituseDTO);
}
