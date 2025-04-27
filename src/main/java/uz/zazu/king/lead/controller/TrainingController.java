package uz.zazu.king.lead.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.zazu.king.lead.dto.TrainingDto;
import uz.zazu.king.lead.entity.Training;
import uz.zazu.king.lead.service.TrainingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/training")
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping
    public Training create(@RequestBody TrainingDto dto) {
        return trainingService.create(dto);
    }

    @PutMapping("/{id}")
    public Training update(@PathVariable String id, @RequestBody TrainingDto dto) {
        return trainingService.update(id, dto);
    }

    @GetMapping("/{id}")
    public Training getById(@PathVariable String id) {
        return trainingService.findById(id);
    }

    @GetMapping
    public List<Training> getAllActive() {
        return trainingService.getAllActive();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        trainingService.remove(id);
    }
}