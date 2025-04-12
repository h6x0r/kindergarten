package uz.zazu.king.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uz.zazu.king.employee.dto.EmployeeEmployeeQuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireEducativeRoleDto;
import uz.zazu.king.employee.entity.EmployeeQuestionnaireBusinessRoleEntity;
import uz.zazu.king.employee.entity.QuestionnaireEducativeRoleEntity;
import uz.zazu.king.employee.mapper.EmployeeQuestionnaireMapper;
import uz.zazu.king.employee.repository.EmployeeQuestionnaireBusinessRoleRepository;
import uz.zazu.king.employee.repository.EmployeeQuestionnaireEducativeRoleRepository;
import uz.zazu.king.service.impl.EmployeeEmployeeQuestionnaireServiceImpl;

import java.util.List;
import java.util.Optional;

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

@SpringBootTest
public class EmployeeEmployeeQuestionnaireServiceImplTest {

    @Mock
    private EmployeeQuestionnaireMapper employeeQuestionnaireMapper;

    @Mock
    private EmployeeQuestionnaireBusinessRoleRepository businessRoleRepository;

    @Mock
    private EmployeeQuestionnaireEducativeRoleRepository educativeRoleRepository;

    @InjectMocks
    private EmployeeEmployeeQuestionnaireServiceImpl questionnaireService;

    @Test
    void create_shouldSaveBusinessRoleSuccessfully() {
        // Arrange
        EmployeeEmployeeQuestionnaireBusinessRoleDto businessRoleDto = new EmployeeEmployeeQuestionnaireBusinessRoleDto();
        EmployeeQuestionnaireBusinessRoleEntity businessRoleEntity = new EmployeeQuestionnaireBusinessRoleEntity();
        when(employeeQuestionnaireMapper.toBusinessRoleEntity(businessRoleDto)).thenReturn(businessRoleEntity);
        when(businessRoleRepository.save(businessRoleEntity)).thenReturn(businessRoleEntity);
        when(employeeQuestionnaireMapper.toBusinessRoleDto(businessRoleEntity)).thenReturn(businessRoleDto);

        // Act
        EmployeeQuestionnaireDto result = questionnaireService.create(businessRoleDto);

        // Assert
        assertNotNull(result);
        assertSame(businessRoleDto, result);
        verify(employeeQuestionnaireMapper, times(1)).toBusinessRoleEntity(businessRoleDto);
        verify(businessRoleRepository, times(1)).save(businessRoleEntity);
        verify(employeeQuestionnaireMapper, times(1)).toBusinessRoleDto(businessRoleEntity);
    }

    @Test
    void create_shouldSaveEducativeRoleSuccessfully() {
        // Arrange
        EmployeeQuestionnaireEducativeRoleDto educativeRoleDto = new EmployeeQuestionnaireEducativeRoleDto();
        QuestionnaireEducativeRoleEntity educativeRoleEntity = new QuestionnaireEducativeRoleEntity();
        when(employeeQuestionnaireMapper.toEducativeRoleEntity(educativeRoleDto)).thenReturn(educativeRoleEntity);
        when(educativeRoleRepository.save(educativeRoleEntity)).thenReturn(educativeRoleEntity);
        when(employeeQuestionnaireMapper.toEducativeRoleDto(educativeRoleEntity)).thenReturn(educativeRoleDto);

        // Act
        EmployeeQuestionnaireDto result = questionnaireService.create(educativeRoleDto);

        // Assert
        assertNotNull(result);
        assertSame(educativeRoleDto, result);
        verify(employeeQuestionnaireMapper, times(1)).toEducativeRoleEntity(educativeRoleDto);
        verify(educativeRoleRepository, times(1)).save(educativeRoleEntity);
        verify(employeeQuestionnaireMapper, times(1)).toEducativeRoleDto(educativeRoleEntity);
    }

    @Test
    void create_shouldThrowExceptionForUnknownDtoType() {
        // Arrange
        EmployeeQuestionnaireDto unknownDto = mock(EmployeeQuestionnaireDto.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.create(unknownDto));
        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, employeeQuestionnaireMapper);
    }

    @Test
    void create_shouldThrowExceptionWhenDtoIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.create(null));
        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, employeeQuestionnaireMapper);
    }

    @Test
    void findById_shouldReturnBusinessRoleSuccessfully() {
        // Arrange
        String id = "business123";
        EmployeeQuestionnaireBusinessRoleEntity businessRoleEntity = new EmployeeQuestionnaireBusinessRoleEntity();
        EmployeeEmployeeQuestionnaireBusinessRoleDto businessRoleDto = new EmployeeEmployeeQuestionnaireBusinessRoleDto();

        when(businessRoleRepository.findById(id)).thenReturn(Optional.of(businessRoleEntity));
        when(employeeQuestionnaireMapper.toBusinessRoleDto(businessRoleEntity)).thenReturn(businessRoleDto);

        // Act
        EmployeeQuestionnaireDto result = questionnaireService.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(businessRoleDto, result);
        verify(businessRoleRepository, times(1)).findById(id);
        verify(employeeQuestionnaireMapper, times(1)).toBusinessRoleDto(businessRoleEntity);
        verifyNoInteractions(educativeRoleRepository);
    }

    @Test
    void findById_shouldReturnEducativeRoleSuccessfully() {
        // Arrange
        String id = "educative456";
        QuestionnaireEducativeRoleEntity educativeRoleEntity = new QuestionnaireEducativeRoleEntity();
        EmployeeQuestionnaireEducativeRoleDto educativeRoleDto = new EmployeeQuestionnaireEducativeRoleDto();

        when(educativeRoleRepository.findById(id)).thenReturn(Optional.of(educativeRoleEntity));
        when(employeeQuestionnaireMapper.toEducativeRoleDto(educativeRoleEntity)).thenReturn(educativeRoleDto);

        // Act
        EmployeeQuestionnaireDto result = questionnaireService.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(educativeRoleDto, result);
        verify(educativeRoleRepository, times(1)).findById(id);
        verify(employeeQuestionnaireMapper, times(1)).toEducativeRoleDto(educativeRoleEntity);
    }

    @Test
    void findById_shouldThrowExceptionWhenNotFound() {
        // Arrange
        String id = "unknownId";
        when(businessRoleRepository.findById(id)).thenReturn(Optional.empty());
        when(educativeRoleRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> questionnaireService.findById(id));
        assertEquals("Опросник с указанным ID не найден.", exception.getMessage());

        verify(businessRoleRepository, times(1)).findById(id);
        verify(educativeRoleRepository, times(1)).findById(id);
    }

    @Test
    void findById_shouldThrowExceptionWhenIdIsNullOrEmpty() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.findById(null));
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.findById(""));

        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, employeeQuestionnaireMapper);
    }

    @Test
    void findAll_shouldReturnAllQuestionnairesSuccessfully() {
        // Arrange
        EmployeeQuestionnaireBusinessRoleEntity businessEntity = new EmployeeQuestionnaireBusinessRoleEntity();
        QuestionnaireEducativeRoleEntity educativeEntity = new QuestionnaireEducativeRoleEntity();

        EmployeeEmployeeQuestionnaireBusinessRoleDto businessDto = new EmployeeEmployeeQuestionnaireBusinessRoleDto();
        EmployeeQuestionnaireEducativeRoleDto educativeDto = new EmployeeQuestionnaireEducativeRoleDto();

        when(businessRoleRepository.findAll()).thenReturn(List.of(businessEntity));
        when(educativeRoleRepository.findAll()).thenReturn(List.of(educativeEntity));

        when(employeeQuestionnaireMapper.toBusinessRoleDto(businessEntity)).thenReturn(businessDto);
        when(employeeQuestionnaireMapper.toEducativeRoleDto(educativeEntity)).thenReturn(educativeDto);

        // Act
        List<EmployeeQuestionnaireDto> result = questionnaireService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(businessDto));
        assertTrue(result.contains(educativeDto));
        verify(businessRoleRepository, times(1)).findAll();
        verify(educativeRoleRepository, times(1)).findAll();
        verify(employeeQuestionnaireMapper, times(1)).toBusinessRoleDto(businessEntity);
        verify(employeeQuestionnaireMapper, times(1)).toEducativeRoleDto(educativeEntity);
    }

    @Test
    void findAll_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(businessRoleRepository.findAll()).thenReturn(List.of());
        when(educativeRoleRepository.findAll()).thenReturn(List.of());

        // Act
        List<EmployeeQuestionnaireDto> result = questionnaireService.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(businessRoleRepository, times(1)).findAll();
        verify(educativeRoleRepository, times(1)).findAll();
        verifyNoInteractions(employeeQuestionnaireMapper);
    }

    @Test
    void findAllBusiness_shouldReturnAllBusinessRolesSuccessfully() {
        // Arrange
        EmployeeQuestionnaireBusinessRoleEntity businessEntity1 = new EmployeeQuestionnaireBusinessRoleEntity();
        EmployeeQuestionnaireBusinessRoleEntity businessEntity2 = new EmployeeQuestionnaireBusinessRoleEntity();

        EmployeeEmployeeQuestionnaireBusinessRoleDto businessDto1 = new EmployeeEmployeeQuestionnaireBusinessRoleDto();
        EmployeeEmployeeQuestionnaireBusinessRoleDto businessDto2 = new EmployeeEmployeeQuestionnaireBusinessRoleDto();

        when(businessRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of(businessEntity1, businessEntity2));
        when(employeeQuestionnaireMapper.toBusinessRoleDto(businessEntity1)).thenReturn(businessDto1);
        when(employeeQuestionnaireMapper.toBusinessRoleDto(businessEntity2)).thenReturn(businessDto2);

        // Act
        List<EmployeeEmployeeQuestionnaireBusinessRoleDto> result = questionnaireService.findAllBusiness();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(businessDto1));
        assertTrue(result.contains(businessDto2));
        verify(businessRoleRepository, times(1)).findAllByIsActiveTrue();
        verify(employeeQuestionnaireMapper, times(1)).toBusinessRoleDto(businessEntity1);
        verify(employeeQuestionnaireMapper, times(1)).toBusinessRoleDto(businessEntity2);
    }

    @Test
    void findAllBusiness_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(businessRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of());

        // Act
        List<EmployeeEmployeeQuestionnaireBusinessRoleDto> result = questionnaireService.findAllBusiness();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(businessRoleRepository, times(1)).findAllByIsActiveTrue();
        verifyNoInteractions(employeeQuestionnaireMapper);
    }

    @Test
    void findAllEducative_shouldReturnAllEducativeRolesSuccessfully() {
        // Arrange
        QuestionnaireEducativeRoleEntity educativeEntity1 = new QuestionnaireEducativeRoleEntity();
        QuestionnaireEducativeRoleEntity educativeEntity2 = new QuestionnaireEducativeRoleEntity();

        EmployeeQuestionnaireEducativeRoleDto educativeDto1 = new EmployeeQuestionnaireEducativeRoleDto();
        EmployeeQuestionnaireEducativeRoleDto educativeDto2 = new EmployeeQuestionnaireEducativeRoleDto();

        when(educativeRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of(educativeEntity1, educativeEntity2));
        when(employeeQuestionnaireMapper.toEducativeRoleDto(educativeEntity1)).thenReturn(educativeDto1);
        when(employeeQuestionnaireMapper.toEducativeRoleDto(educativeEntity2)).thenReturn(educativeDto2);

        // Act
        List<EmployeeQuestionnaireEducativeRoleDto> result = questionnaireService.findAllEducative();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(educativeDto1));
        assertTrue(result.contains(educativeDto2));
        verify(educativeRoleRepository, times(1)).findAllByIsActiveTrue();
        verify(employeeQuestionnaireMapper, times(1)).toEducativeRoleDto(educativeEntity1);
        verify(employeeQuestionnaireMapper, times(1)).toEducativeRoleDto(educativeEntity2);
    }

    @Test
    void findAllEducative_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(educativeRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of());

        // Act
        List<EmployeeQuestionnaireEducativeRoleDto> result = questionnaireService.findAllEducative();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(educativeRoleRepository, times(1)).findAllByIsActiveTrue();
        verifyNoInteractions(employeeQuestionnaireMapper);
    }
}