package me.projects.AICodeTesting.Repository;

import me.projects.AICodeTesting.Dto.TestingAuditDTO.TestingAuditDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestingAuditRepository extends JpaRepository<TestingAuditDTO,String> {
}
