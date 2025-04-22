package uz.zazu.king.lead.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.lead.dto.TrainingDto;
import uz.zazu.king.lead.entity.Training;
import uz.zazu.king.lead.mapper.TrainingMapper;
import uz.zazu.king.lead.repository.TrainingRepository;
import uz.zazu.king.lead.service.TrainingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    @Override
    public Training create(TrainingDto dto) {
        Training training = trainingMapper.toEntity(dto);
        return trainingRepository.save(training);
    }

    @Override
    public Training update(String id, TrainingDto dto) {
        Training existing = trainingRepository.getByIdAndActive(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        trainingMapper.updateEntityFromDto(dto, existing);
        return trainingRepository.save(existing);
    }

    @Override
    public Training findById(String id) {
        return trainingRepository.getByIdAndActive(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
    }

    @Override
    public List<Training> getAllActive() {
        return trainingRepository.findAllActive();
    }

    @Override
    public void remove(String id) {
        var training = trainingRepository.getByIdAndActive(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        training.setIsActive(false);
        trainingRepository.save(training);
    }
}