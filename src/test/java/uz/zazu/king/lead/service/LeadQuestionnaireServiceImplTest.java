package uz.zazu.king.lead.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.lead.dto.LeadQuestionnaireDto;
import uz.zazu.king.lead.entity.LeadQuestionnaireEntity;
import uz.zazu.king.lead.mapper.LeadQuestionnaireMapper;
import uz.zazu.king.lead.repository.LeadQuestionnaireRepository;
import uz.zazu.king.lead.service.impl.LeadQuestionnaireServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        LeadQuestionnaireDto inputDto = LeadQuestionnaireDto.builder().build();
        LeadQuestionnaireEntity entity = new LeadQuestionnaireEntity();
        LeadQuestionnaireEntity savedEntity = new LeadQuestionnaireEntity();
        LeadQuestionnaireDto expectedDto = LeadQuestionnaireDto.builder().build();
        when(leadQuestionnaireMapper.toEntity(inputDto)).thenReturn(entity);
        when(leadQuestionnaireRepository.save(entity)).thenReturn(savedEntity);
        when(leadQuestionnaireMapper.toDto(savedEntity)).thenReturn(expectedDto);
        LeadQuestionnaireDto actualDto = leadQuestionnaireService.create(inputDto);
        assertEquals(expectedDto, actualDto);
        verify(leadQuestionnaireMapper).toEntity(inputDto);
        verify(leadQuestionnaireRepository).save(entity);
        verify(leadQuestionnaireMapper).toDto(savedEntity);
    }

    @Test
    void testDelete_Success() {
        String id = "validId";
        LeadQuestionnaireEntity entity = new LeadQuestionnaireEntity();
        entity.setActive(true);
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(entity);
        leadQuestionnaireService.delete(id);
        verify(leadQuestionnaireRepository).findActiveById(id);
        verify(leadQuestionnaireRepository).save(entity);
        assertEquals(false, entity.isActive());
    }

    @Test
    void testDelete_NotFound() {
        String id = "invalidId";
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(null);
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.delete(id));
        verify(leadQuestionnaireRepository).findActiveById(id);
    }

    @Test
    void testDelete_InvalidId() {
        String id = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> leadQuestionnaireService.delete(id));
    }

    @Test
    void testFindById_Success() {
        String id = "validId";
        LeadQuestionnaireEntity entity = LeadQuestionnaireEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        LeadQuestionnaireDto expectedDto = LeadQuestionnaireDto.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(entity);
        when(leadQuestionnaireMapper.toDto(entity)).thenReturn(expectedDto);
        LeadQuestionnaireDto actualDto = leadQuestionnaireService.findById(id);
        assertEquals(expectedDto, actualDto);
        verify(leadQuestionnaireRepository).findActiveById(id);
        verify(leadQuestionnaireMapper).toDto(entity);
    }

    @Test
    void testFindById_NotFound() {
        String id = "invalidId";
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(null);
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.findById(id));
        verify(leadQuestionnaireRepository).findActiveById(id);
    }

    @Test
    void testFindById_InvalidId() {
        String id = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> leadQuestionnaireService.findById(id));
    }

    @Test
    void testFindAll_Success() {
        List<LeadQuestionnaireEntity> entities = List.of(new LeadQuestionnaireEntity(), new LeadQuestionnaireEntity());
        List<LeadQuestionnaireDto> expectedDtos = List.of(
                LeadQuestionnaireDto.builder().build(),
                LeadQuestionnaireDto.builder().build()
        );
        when(leadQuestionnaireRepository.findAllActive()).thenReturn(entities);
        when(leadQuestionnaireMapper.toDto(any(LeadQuestionnaireEntity.class)))
                .thenReturn(expectedDtos.get(0), expectedDtos.get(1));
        List<LeadQuestionnaireDto> actualDtos = leadQuestionnaireService.findAll();
        assertEquals(expectedDtos, actualDtos);
        verify(leadQuestionnaireRepository).findAllActive();
        verify(leadQuestionnaireMapper, times(2)).toDto(any(LeadQuestionnaireEntity.class));
    }

    @Test
    void testFindAll_EmptyList() {
        when(leadQuestionnaireRepository.findAllActive()).thenReturn(List.of());
        List<LeadQuestionnaireDto> actualDtos = leadQuestionnaireService.findAll();
        assertEquals(List.of(), actualDtos);
        verify(leadQuestionnaireRepository).findAllActive();
        verifyNoInteractions(leadQuestionnaireMapper);
    }

    @Test
    void testUpdate_Success() {
        String id = "validId";
        LeadQuestionnaireDto inputDto = LeadQuestionnaireDto.builder().build();
        LeadQuestionnaireEntity entity = new LeadQuestionnaireEntity();
        LeadQuestionnaireEntity updatedEntity = new LeadQuestionnaireEntity();
        LeadQuestionnaireDto expectedDto = LeadQuestionnaireDto.builder().build();
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(entity);
        doNothing().when(leadQuestionnaireMapper).updateEntityFromDto(inputDto, entity);
        when(leadQuestionnaireRepository.save(entity)).thenReturn(updatedEntity);
        when(leadQuestionnaireMapper.toDto(updatedEntity)).thenReturn(expectedDto);
        LeadQuestionnaireDto actualDto = leadQuestionnaireService.update(id, inputDto);
        assertEquals(expectedDto, actualDto);
        verify(leadQuestionnaireRepository).findActiveById(id);
        verify(leadQuestionnaireMapper).updateEntityFromDto(inputDto, entity);
        verify(leadQuestionnaireRepository).save(entity);
        verify(leadQuestionnaireMapper).toDto(updatedEntity);
    }

    @Test
    void testUpdate_NotFound() {
        String id = "invalidId";
        LeadQuestionnaireDto inputDto = LeadQuestionnaireDto.builder().build();
        when(leadQuestionnaireRepository.findActiveById(id)).thenReturn(null);
        Assertions.assertThrows(QuestionnaireNotFoundException.class, () -> leadQuestionnaireService.update(id, inputDto));
        verify(leadQuestionnaireRepository).findActiveById(id);
        verifyNoInteractions(leadQuestionnaireMapper);
    }

    @Test
    void testUpdate_InvalidInput() {
        String id = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> leadQuestionnaireService.update(id, null));
    }
}