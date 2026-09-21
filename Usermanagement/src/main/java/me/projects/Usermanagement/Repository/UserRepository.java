package me.projects.Usermanagement.Repository;

import me.projects.Usermanagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByProviderSubjectAndIsDeletedIsFalse(String providerSubject);
}
