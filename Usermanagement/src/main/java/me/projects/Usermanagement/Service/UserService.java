package me.projects.Usermanagement.Service;

import me.projects.Usermanagement.DTo.UserCredituseDTO;
import me.projects.Usermanagement.DTo.UserRequestDTo;
import me.projects.Usermanagement.DTo.UserResponseDTO;
import me.projects.Usermanagement.Entity.User;
import me.projects.Usermanagement.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
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
            user.setCredits(BigDecimal.valueOf(5));
        }
        user.setName(userRequestDTo.getName());
        user.setLastOnline(LocalDateTime.now());
        user.setRole("USER");
        user.setIsDeleted(false);
        user.setProviderSubject(subject);
        userRepository.save(user);
       return new UserResponseDTO(user.getName(),"Welcome to Cester");
    }
    public boolean findUserBySubject(String Subject){
        Optional<User> user=userRepository.findByProviderSubjectAndIsDeletedIsFalse(Subject);
        if(user.isPresent())
            return true;
        else
            return false;
    }
    @Transactional
    public UserResponseDTO SoftDeleteUsers(Long id){
        User user=userRepository.findByIdAndIsDeletedIsFalse(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        user.setIsDeleted(true);
        return new UserResponseDTO(user.getName(),"User Deleted Successfully");
    }
    public BigDecimal getUserCredits(String Userid){
        User user=userRepository.findByProviderSubjectAndIsDeletedIsFalse(Userid).orElseThrow(()-> new RuntimeException("User Not Found"));
        return user.getCredits();
    }
    @Transactional
    public void subtractUserCredit(UserCredituseDTO userCredituseDTO){
        String Userid=userCredituseDTO.getUserid();
        BigDecimal credits=userCredituseDTO.getCredit();
        User user=userRepository.findByProviderSubjectAndIsDeletedIsFalse(Userid).orElseThrow(()->new RuntimeException("User not Found"));
        user.setCredits(user.getCredits().subtract(credits));
        userRepository.save(user);
    }
    @Transactional
    public void AddUserCredits(UserCredituseDTO userCredituseDTO){
        String Userid=userCredituseDTO.getUserid();
        BigDecimal credits=userCredituseDTO.getCredit();
        User user=userRepository.findByProviderSubjectAndIsDeletedIsFalse(Userid).orElseThrow(()-> new RuntimeException("User not Found"));
        user.setCredits(user.getCredits().add(credits));
        userRepository.save(user);
    }
}
