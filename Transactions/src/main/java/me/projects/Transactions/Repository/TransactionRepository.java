package me.projects.Transactions.Repository;

import me.projects.Transactions.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {
    List<Transactions> findAllByUserid(Long userid);
    Optional<Transactions> findByIdAndIsDeletedIsFalse(Long id);
}
