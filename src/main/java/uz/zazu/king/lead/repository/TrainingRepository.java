package uz.zazu.king.lead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zazu.king.lead.entity.Training;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, String> {

    @Query(value = "SELECT * FROM trainings WHERE id = ?1 AND is_active = true", nativeQuery = true)
    Optional<Training> getByIdAndActive(String id);

    @Query(value = "SELECT * FROM trainings WHERE is_active = true", nativeQuery = true)
    List<Training> findAllActive();
}