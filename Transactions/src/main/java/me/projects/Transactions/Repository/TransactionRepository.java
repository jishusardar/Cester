package me.projects.Transactions.Repository;

import me.projects.Transactions.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {
    List<Transactions> findAllByUserid(Long userid);
}
