package uz.zazu.king.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileDto;
import uz.zazu.king.employee.entity.CandidateApplicationBusinessRoleEntity;
import uz.zazu.king.employee.entity.CandidateApplicationEducatorRoleEntity;
import uz.zazu.king.employee.entity.CandidateApplicationNannyRoleEntity;
import uz.zazu.king.employee.mapper.CandidateProfileMapper;
import uz.zazu.king.employee.repository.CandidateProfileBusinessRoleRepository;
import uz.zazu.king.employee.repository.CandidateProfileEducatorRoleRepository;
import uz.zazu.king.employee.repository.CandidateProfileNannyRoleRepository;
import uz.zazu.king.employee.service.impl.EmployeeEmployeeQuestionnaireServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeEmployeeQuestionnaireServiceImplTest {

    @Mock
    private CandidateProfileMapper candidateProfileMapper;

    @Mock
    private CandidateProfileBusinessRoleRepository businessRoleRepository;

    @Mock
    private CandidateProfileEducatorRoleRepository educativeRoleRepository;

    @InjectMocks
    private EmployeeEmployeeQuestionnaireServiceImpl questionnaireService;

    @Test
    void create_shouldSaveBusinessRoleSuccessfully() {
        // Arrange
        var businessRoleDto = new CandidateProfileBusinessDto();
        var businessRoleEntity = new CandidateApplicationBusinessRoleEntity();
        when(candidateProfileMapper.toBusinessRoleEntity(businessRoleDto)).thenReturn(businessRoleEntity);
        when(businessRoleRepository.save(businessRoleEntity)).thenReturn(businessRoleEntity);
        when(candidateProfileMapper.toBusinessRoleDto(businessRoleEntity)).thenReturn(businessRoleDto);

        // Act
        var result = questionnaireService.create(businessRoleDto);

        // Assert
        assertNotNull(result);
        assertSame(businessRoleDto, result);
        verify(candidateProfileMapper, times(1)).toBusinessRoleEntity(businessRoleDto);
        verify(businessRoleRepository, times(1)).save(businessRoleEntity);
        verify(candidateProfileMapper, times(1)).toBusinessRoleDto(businessRoleEntity);
    }

    @Test
    void create_shouldSaveEducativeRoleSuccessfully() {
        // Arrange
        var educativeRoleDto = new CandidateProfileEducatorDto();
        var educativeRoleEntity = new CandidateApplicationEducatorRoleEntity();
        when(candidateProfileMapper.toEducativeRoleEntity(educativeRoleDto)).thenReturn(educativeRoleEntity);
        when(educativeRoleRepository.save(educativeRoleEntity)).thenReturn(educativeRoleEntity);
        when(candidateProfileMapper.toEducativeRoleDto(educativeRoleEntity)).thenReturn(educativeRoleDto);

        // Act
        var result = questionnaireService.create(educativeRoleDto);

        // Assert
        assertNotNull(result);
        assertSame(educativeRoleDto, result);
        verify(candidateProfileMapper, times(1)).toEducativeRoleEntity(educativeRoleDto);
        verify(educativeRoleRepository, times(1)).save(educativeRoleEntity);
        verify(candidateProfileMapper, times(1)).toEducativeRoleDto(educativeRoleEntity);
    }

    @Test
    void create_shouldThrowExceptionForUnknownDtoType() {
        // Arrange
        var unknownDto = mock(CandidateProfileDto.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.create(unknownDto));
        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, candidateProfileMapper);
    }

    @Test
    void create_shouldThrowExceptionWhenDtoIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.create(null));
        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, candidateProfileMapper);
    }

    @Test
    void findById_shouldReturnBusinessRoleSuccessfully() {
        // Arrange
        var id = "business123";
        var businessRoleEntity = new CandidateApplicationBusinessRoleEntity();
        var businessRoleDto = new CandidateProfileBusinessDto();

        when(businessRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(businessRoleEntity);
        when(candidateProfileMapper.toBusinessRoleDto(businessRoleEntity)).thenReturn(businessRoleDto);

        // Act
        var result = questionnaireService.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(businessRoleDto, result);
        verify(businessRoleRepository, times(1)).findByIdAndIsActiveTrue(id);
        verify(candidateProfileMapper, times(1)).toBusinessRoleDto(businessRoleEntity);
        verifyNoInteractions(educativeRoleRepository);
    }

    @Test
    void findById_shouldReturnEducativeRoleSuccessfully() {
        // Arrange
        var id = "educative456";
        var educativeRoleEntity = new CandidateApplicationEducatorRoleEntity();
        var educativeRoleDto = new CandidateProfileEducatorDto();

        when(educativeRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(educativeRoleEntity);
        when(candidateProfileMapper.toEducativeRoleDto(educativeRoleEntity)).thenReturn(educativeRoleDto);

        // Act
        var result = questionnaireService.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(educativeRoleDto, result);
        verify(educativeRoleRepository, times(1)).findByIdAndIsActiveTrue(id);
        verify(candidateProfileMapper, times(1)).toEducativeRoleDto(educativeRoleEntity);
    }

    @Test
    void findById_shouldThrowExceptionWhenNotFound() {
        // Arrange
        var id = "unknownId";
        when(businessRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(null);
        when(educativeRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(null);

        // Act & Assert
        var exception = assertThrows(QuestionnaireNotFoundException.class, () -> questionnaireService.findById(id));
        assertEquals("Анкета с идентификатором не найдена: " + id, exception.getMessage());

        verify(businessRoleRepository, times(1)).findByIdAndIsActiveTrue(id);
        verify(educativeRoleRepository, times(1)).findByIdAndIsActiveTrue(id);
    }

    @Test
    void findById_shouldThrowExceptionWhenIdIsNullOrEmpty() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.findById(null));
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.findById(""));

        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, candidateProfileMapper);
    }

    @Test
    void findAll_shouldReturnAllQuestionnairesSuccessfully() {
        // Arrange
        var businessEntity = CandidateApplicationBusinessRoleEntity.builder()
                .isActive(true)
                .build();
        var educativeEntity = CandidateApplicationEducatorRoleEntity.builder()
                .isActive(true)
                .build();

        var businessDto = new CandidateProfileBusinessDto();
        var educativeDto = new CandidateProfileEducatorDto();

        when(businessRoleRepository.findAll()).thenReturn(List.of(businessEntity));
        when(educativeRoleRepository.findAll()).thenReturn(List.of(educativeEntity));

        when(candidateProfileMapper.toBusinessRoleDto(businessEntity)).thenReturn(businessDto);
        when(candidateProfileMapper.toEducativeRoleDto(educativeEntity)).thenReturn(educativeDto);

        // Act
        var result = questionnaireService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(businessDto));
        assertTrue(result.contains(educativeDto));
        verify(businessRoleRepository, times(1)).findAll();
        verify(educativeRoleRepository, times(1)).findAll();
        verify(candidateProfileMapper, times(1)).toBusinessRoleDto(businessEntity);
        verify(candidateProfileMapper, times(1)).toEducativeRoleDto(educativeEntity);
    }

    @Test
    void findAll_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(businessRoleRepository.findAll()).thenReturn(List.of());
        when(educativeRoleRepository.findAll()).thenReturn(List.of());

        // Act
        var result = questionnaireService.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(businessRoleRepository, times(1)).findAll();
        verify(educativeRoleRepository, times(1)).findAll();
        verifyNoInteractions(candidateProfileMapper);
    }

    @Test
    void findAllBusiness_shouldReturnAllBusinessRolesSuccessfully() {
        // Arrange
        var businessEntity1 = new CandidateApplicationBusinessRoleEntity();
        var businessEntity2 = new CandidateApplicationBusinessRoleEntity();

        var businessDto1 = new CandidateProfileBusinessDto();
        var businessDto2 = new CandidateProfileBusinessDto();

        when(businessRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of(businessEntity1, businessEntity2));
        when(candidateProfileMapper.toBusinessRoleDto(businessEntity1)).thenReturn(businessDto1);
        when(candidateProfileMapper.toBusinessRoleDto(businessEntity2)).thenReturn(businessDto2);

        // Act
        var result = questionnaireService.findAllBusiness();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(businessDto1));
        assertTrue(result.contains(businessDto2));
        verify(businessRoleRepository, times(1)).findAllByIsActiveTrue();
        verify(candidateProfileMapper, times(1)).toBusinessRoleDto(businessEntity1);
        verify(candidateProfileMapper, times(1)).toBusinessRoleDto(businessEntity2);
    }

    @Test
    void findAllBusiness_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(businessRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of());

        // Act
        var result = questionnaireService.findAllBusiness();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(businessRoleRepository, times(1)).findAllByIsActiveTrue();
        verifyNoInteractions(candidateProfileMapper);
    }

    @Test
    void findAllEducative_shouldReturnAllEducativeRolesSuccessfully() {
        // Arrange
        var educativeEntity1 = new CandidateApplicationEducatorRoleEntity();
        var educativeEntity2 = new CandidateApplicationEducatorRoleEntity();

        var educativeDto1 = new CandidateProfileEducatorDto();
        var educativeDto2 = new CandidateProfileEducatorDto();

        when(educativeRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of(educativeEntity1, educativeEntity2));
        when(candidateProfileMapper.toEducativeRoleDto(educativeEntity1)).thenReturn(educativeDto1);
        when(candidateProfileMapper.toEducativeRoleDto(educativeEntity2)).thenReturn(educativeDto2);

        // Act
        var result = questionnaireService.findAllEducative();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(educativeDto1));
        assertTrue(result.contains(educativeDto2));
        verify(educativeRoleRepository, times(1)).findAllByIsActiveTrue();
        verify(candidateProfileMapper, times(1)).toEducativeRoleDto(educativeEntity1);
        verify(candidateProfileMapper, times(1)).toEducativeRoleDto(educativeEntity2);
    }

    @Test
    void findAllEducative_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(educativeRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of());

        // Act
        var result = questionnaireService.findAllEducative();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(educativeRoleRepository, times(1)).findAllByIsActiveTrue();
        verifyNoInteractions(candidateProfileMapper);
    }
}