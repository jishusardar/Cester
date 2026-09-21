package me.projects.Usermanagement.Service;

import me.projects.Usermanagement.DTo.UserRequestDTo;
import me.projects.Usermanagement.DTo.UserResponseDTO;
import me.projects.Usermanagement.Entity.User;
import me.projects.Usermanagement.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Transactional
    public UserResponseDTO RegisterAndUpdateUser(String subject, UserRequestDTo userRequestDTo) {
        Optional<User> userExist = userRepository.findByProviderSubjectAndIsDeletedIsFalse(subject);
        boolean userExists = userExist.isPresent();
        User user;
        if (userExists) {
            user = userExist.get();
        } else {
            user = new User();
            user.setUserEmail(userRequestDTo.getUserEmail());
        }
        user.setName(userRequestDTo.getName());
        user.setLastOnline(LocalDateTime.now());
        user.setRole("USER");
        user.setIsDeleted(false);
        user.setProviderSubject(subject);
        userRepository.save(user);
       return new UserResponseDTO(user.getName(),"Welcome to Cester");
    }
}
