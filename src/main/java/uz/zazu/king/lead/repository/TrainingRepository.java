package uz.zazu.king.lead.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.zazu.king.lead.entity.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends MongoRepository<Training, String> {

    @Query("{ '_id': ?0, 'isActive': true }")
    Optional<Training> getByIdAndActive(String id);

    @Query("{ 'isActive': true }")
    List<Training> findAllActive();
}