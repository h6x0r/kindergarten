package uz.zazu.king.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.employee.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    @Query(value = "SELECT * FROM employees WHERE is_active = true", nativeQuery = true)
    List<EmployeeEntity> findAllActive();

    @Query(value = "SELECT * FROM employees WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<EmployeeEntity> findActiveById(String id);
}