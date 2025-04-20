package uz.zazu.king.lead.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.questionnaire.dto.LeadQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.LeadQuestionnaireEntity;
import uz.zazu.king.questionnaire.mapper.LeadQuestionnaireMapper;
import uz.zazu.king.questionnaire.repository.LeadQuestionnaireRepository;
import uz.zazu.king.questionnaire.service.impl.LeadQuestionnaireServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeadQuestionnaireServiceImplTest {

    @Mock
    private LeadQuestionnaireRepository leadQuestionnaireRepository;

    @Mock
    private LeadQuestionnaireMapper leadQuestionnaireMapper;

    @InjectMocks
    private LeadQuestionnaireServiceImpl leadQuestionnaireService;

    @Test
    void testCreate() {
        // Arrange
        var inputDto = LeadQuestionnaireDto.builder().build();
        var entity = new LeadQuestionnaireEntity();
        var savedEntity = new LeadQuestionnaireEntity();
        var expectedDto = LeadQuestionnaireDto.builder().build();
        when(leadQuestionnaireMapper.toEntity(inputDto)).thenReturn(entity);
        when(leadQuestionnaireRepository.save(entity)).thenReturn(savedEntity);
        when(leadQuestionnaireMapper.toDto(savedEntity)).thenReturn(expectedDto);

        // Act
        var actualDto = leadQuestionnaireService.create(inputDto);

        // Assert
        assertEquals(expectedDto, actualDto);
        verify(leadQuestionnaireMapper).toEntity(inputDto);
        verify(leadQuestionnaireRepository).save(entity);
        verify(leadQuestionnaireMapper).toDto(savedEntity);
    }

    @Test
    void testDelete_Success() {
        // Arrange
        var id = "validId";
        var opt = Optional.of(new LeadQuestionnaireEntity());
        var entity = opt.get();
        entity.setActive(true);
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(opt);

        // Act
        leadQuestionnaireService.delete(id);

        // Assert
        verify(leadQuestionnaireRepository).findActiveById(id);
        verify(leadQuestionnaireRepository).save(entity);
        assertEquals(false, entity.isActive());
    }

    @Test
    void testDelete_NotFound() {
        String id = "invalidId";
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.delete(id));
        verify(leadQuestionnaireRepository).findActiveById(id);
    }

    @Test
    void testDelete_InvalidId() {
        String id = "";
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.delete(id));
    }

    @Test
    void testFindById_Success() {
        // Arrange
        var id = "validId";
        var entity = Optional.of(LeadQuestionnaireEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .build());
        var expectedDto = LeadQuestionnaireDto.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(entity);
        when(leadQuestionnaireMapper.toDto(entity.get())).thenReturn(expectedDto);

        // Act
        var actualDto = leadQuestionnaireService.findById(id);

        // Assert
        assertEquals(expectedDto, actualDto);
        verify(leadQuestionnaireRepository).findActiveById(id);
        verify(leadQuestionnaireMapper).toDto(entity.get());
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        var id = "invalidId";
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.findById(id));
        verify(leadQuestionnaireRepository).findActiveById(id);
    }

    @Test
    void testFindById_InvalidId() {
        // Arrange
        var id = "";

        // Act & Assert
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.findById(id));
    }

    @Test
    void testFindAll_Success() {
        // Arrange
        var entities = List.of(new LeadQuestionnaireEntity(), new LeadQuestionnaireEntity());
        var expectedDtos = List.of(
                LeadQuestionnaireDto.builder().build(),
                LeadQuestionnaireDto.builder().build()
        );
        when(leadQuestionnaireRepository.findAllActive()).thenReturn(entities);
        when(leadQuestionnaireMapper.toDto(any(LeadQuestionnaireEntity.class)))
                .thenReturn(expectedDtos.get(0), expectedDtos.get(1));

        // Act
        var actualDtos = leadQuestionnaireService.findAll();

        // Assert
        assertEquals(expectedDtos, actualDtos);
        verify(leadQuestionnaireRepository).findAllActive();
        verify(leadQuestionnaireMapper, times(2)).toDto(any(LeadQuestionnaireEntity.class));
    }

    @Test
    void testFindAll_EmptyList() {
        // Arrange
        when(leadQuestionnaireRepository.findAllActive()).thenReturn(List.of());

        // Act
        var actualDtos = leadQuestionnaireService.findAll();

        // Assert
        assertEquals(List.of(), actualDtos);
        verify(leadQuestionnaireRepository).findAllActive();
        verifyNoInteractions(leadQuestionnaireMapper);
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        var id = "validId";
        var inputDto = LeadQuestionnaireDto.builder().build();
        var opt = Optional.of(new LeadQuestionnaireEntity());
        var entity = opt.get();
        var updatedEntity = new LeadQuestionnaireEntity();
        var expectedDto = LeadQuestionnaireDto.builder().build();
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(opt);
        doNothing().when(leadQuestionnaireMapper).updateEntityFromDto(inputDto, entity);
        when(leadQuestionnaireRepository.save(entity)).thenReturn(updatedEntity);
        when(leadQuestionnaireMapper.toDto(updatedEntity)).thenReturn(expectedDto);

        // Act
        var actualDto = leadQuestionnaireService.update(id, inputDto);

        // Assert
        assertEquals(expectedDto, actualDto);
        verify(leadQuestionnaireRepository).findActiveById(id);
        verify(leadQuestionnaireMapper).updateEntityFromDto(inputDto, entity);
        verify(leadQuestionnaireRepository).save(entity);
        verify(leadQuestionnaireMapper).toDto(updatedEntity);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        var id = "invalidId";
        var inputDto = LeadQuestionnaireDto.builder().build();
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.update(id, inputDto));
        verify(leadQuestionnaireRepository).findActiveById(id);
        verifyNoInteractions(leadQuestionnaireMapper);
    }

    @Test
    void testUpdate_InvalidInput() {
        // Arrange
        var id = "";

        // Act & Assert
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.update(id, null));
    }
}