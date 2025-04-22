package uz.zazu.king.lead.service;


import uz.zazu.king.lead.dto.TrainingDto;
import uz.zazu.king.lead.entity.Training;

import java.util.List;

public interface TrainingService {

    Training create(TrainingDto dto);

    Training update(String id, TrainingDto dto);

    Training findById(String id);

    List<Training> getAllActive();

    void remove(String id);
}