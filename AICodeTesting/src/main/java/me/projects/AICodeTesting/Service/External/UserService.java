package me.projects.AICodeTesting.Service.External;

import me.projects.AICodeTesting.Dto.RequestDTO.UserRequestDTO;
import me.projects.AICodeTesting.Dto.RequestDTO.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Usermanagement")
public interface UserService {

    @PostMapping("/api/users/save")
    UserResponseDTO SaveUser(@RequestBody UserRequestDTO userRequestDTO);
}
