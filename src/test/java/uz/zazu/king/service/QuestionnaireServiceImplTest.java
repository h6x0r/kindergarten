package uz.zazu.king.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import uz.zazu.king.employee.dto.QuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.QuestionnaireDto;
import uz.zazu.king.employee.dto.QuestionnaireEducativeRoleDto;
import uz.zazu.king.employee.entity.QuestionnaireBusinessRoleEntity;
import uz.zazu.king.employee.entity.QuestionnaireEducativeRoleEntity;
import uz.zazu.king.employee.mapper.QuestionnaireMapper;
import uz.zazu.king.employee.repository.QuestionnaireBusinessRoleRepository;
import uz.zazu.king.employee.repository.QuestionnaireEducativeRoleRepository;
import uz.zazu.king.service.impl.QuestionnaireServiceImpl;

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
public class QuestionnaireServiceImplTest {

    @Mock
    private QuestionnaireMapper questionnaireMapper;

    @Mock
    private QuestionnaireBusinessRoleRepository businessRoleRepository;

    @Mock
    private QuestionnaireEducativeRoleRepository educativeRoleRepository;

    @InjectMocks
    private QuestionnaireServiceImpl questionnaireService;

    @Test
    void create_shouldSaveBusinessRoleSuccessfully() {
        // Arrange
        QuestionnaireBusinessRoleDto businessRoleDto = new QuestionnaireBusinessRoleDto();
        QuestionnaireBusinessRoleEntity businessRoleEntity = new QuestionnaireBusinessRoleEntity();
        when(questionnaireMapper.toBusinessRoleEntity(businessRoleDto)).thenReturn(businessRoleEntity);
        when(businessRoleRepository.save(businessRoleEntity)).thenReturn(businessRoleEntity);
        when(questionnaireMapper.toBusinessRoleDto(businessRoleEntity)).thenReturn(businessRoleDto);

        // Act
        QuestionnaireDto result = questionnaireService.create(businessRoleDto);

        // Assert
        assertNotNull(result);
        assertSame(businessRoleDto, result);
        verify(questionnaireMapper, times(1)).toBusinessRoleEntity(businessRoleDto);
        verify(businessRoleRepository, times(1)).save(businessRoleEntity);
        verify(questionnaireMapper, times(1)).toBusinessRoleDto(businessRoleEntity);
    }

    @Test
    void create_shouldSaveEducativeRoleSuccessfully() {
        // Arrange
        QuestionnaireEducativeRoleDto educativeRoleDto = new QuestionnaireEducativeRoleDto();
        QuestionnaireEducativeRoleEntity educativeRoleEntity = new QuestionnaireEducativeRoleEntity();
        when(questionnaireMapper.toEducativeRoleEntity(educativeRoleDto)).thenReturn(educativeRoleEntity);
        when(educativeRoleRepository.save(educativeRoleEntity)).thenReturn(educativeRoleEntity);
        when(questionnaireMapper.toEducativeRoleDto(educativeRoleEntity)).thenReturn(educativeRoleDto);

        // Act
        QuestionnaireDto result = questionnaireService.create(educativeRoleDto);

        // Assert
        assertNotNull(result);
        assertSame(educativeRoleDto, result);
        verify(questionnaireMapper, times(1)).toEducativeRoleEntity(educativeRoleDto);
        verify(educativeRoleRepository, times(1)).save(educativeRoleEntity);
        verify(questionnaireMapper, times(1)).toEducativeRoleDto(educativeRoleEntity);
    }

    @Test
    void create_shouldThrowExceptionForUnknownDtoType() {
        // Arrange
        QuestionnaireDto unknownDto = mock(QuestionnaireDto.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.create(unknownDto));
        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, questionnaireMapper);
    }

    @Test
    void create_shouldThrowExceptionWhenDtoIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionnaireService.create(null));
        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, questionnaireMapper);
    }

    @Test
    void findById_shouldReturnBusinessRoleSuccessfully() {
        // Arrange
        String id = "business123";
        QuestionnaireBusinessRoleEntity businessRoleEntity = new QuestionnaireBusinessRoleEntity();
        QuestionnaireBusinessRoleDto businessRoleDto = new QuestionnaireBusinessRoleDto();

        when(businessRoleRepository.findById(id)).thenReturn(Optional.of(businessRoleEntity));
        when(questionnaireMapper.toBusinessRoleDto(businessRoleEntity)).thenReturn(businessRoleDto);

        // Act
        QuestionnaireDto result = questionnaireService.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(businessRoleDto, result);
        verify(businessRoleRepository, times(1)).findById(id);
        verify(questionnaireMapper, times(1)).toBusinessRoleDto(businessRoleEntity);
        verifyNoInteractions(educativeRoleRepository);
    }

    @Test
    void findById_shouldReturnEducativeRoleSuccessfully() {
        // Arrange
        String id = "educative456";
        QuestionnaireEducativeRoleEntity educativeRoleEntity = new QuestionnaireEducativeRoleEntity();
        QuestionnaireEducativeRoleDto educativeRoleDto = new QuestionnaireEducativeRoleDto();

        when(educativeRoleRepository.findById(id)).thenReturn(Optional.of(educativeRoleEntity));
        when(questionnaireMapper.toEducativeRoleDto(educativeRoleEntity)).thenReturn(educativeRoleDto);

        // Act
        QuestionnaireDto result = questionnaireService.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(educativeRoleDto, result);
        verify(educativeRoleRepository, times(1)).findById(id);
        verify(questionnaireMapper, times(1)).toEducativeRoleDto(educativeRoleEntity);
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

        verifyNoInteractions(businessRoleRepository, educativeRoleRepository, questionnaireMapper);
    }

    @Test
    void findAll_shouldReturnAllQuestionnairesSuccessfully() {
        // Arrange
        QuestionnaireBusinessRoleEntity businessEntity = new QuestionnaireBusinessRoleEntity();
        QuestionnaireEducativeRoleEntity educativeEntity = new QuestionnaireEducativeRoleEntity();

        QuestionnaireBusinessRoleDto businessDto = new QuestionnaireBusinessRoleDto();
        QuestionnaireEducativeRoleDto educativeDto = new QuestionnaireEducativeRoleDto();

        when(businessRoleRepository.findAll()).thenReturn(List.of(businessEntity));
        when(educativeRoleRepository.findAll()).thenReturn(List.of(educativeEntity));

        when(questionnaireMapper.toBusinessRoleDto(businessEntity)).thenReturn(businessDto);
        when(questionnaireMapper.toEducativeRoleDto(educativeEntity)).thenReturn(educativeDto);

        // Act
        List<QuestionnaireDto> result = questionnaireService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(businessDto));
        assertTrue(result.contains(educativeDto));
        verify(businessRoleRepository, times(1)).findAll();
        verify(educativeRoleRepository, times(1)).findAll();
        verify(questionnaireMapper, times(1)).toBusinessRoleDto(businessEntity);
        verify(questionnaireMapper, times(1)).toEducativeRoleDto(educativeEntity);
    }

    @Test
    void findAll_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(businessRoleRepository.findAll()).thenReturn(List.of());
        when(educativeRoleRepository.findAll()).thenReturn(List.of());

        // Act
        List<QuestionnaireDto> result = questionnaireService.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(businessRoleRepository, times(1)).findAll();
        verify(educativeRoleRepository, times(1)).findAll();
        verifyNoInteractions(questionnaireMapper);
    }

    @Test
    void findAllBusiness_shouldReturnAllBusinessRolesSuccessfully() {
        // Arrange
        QuestionnaireBusinessRoleEntity businessEntity1 = new QuestionnaireBusinessRoleEntity();
        QuestionnaireBusinessRoleEntity businessEntity2 = new QuestionnaireBusinessRoleEntity();

        QuestionnaireBusinessRoleDto businessDto1 = new QuestionnaireBusinessRoleDto();
        QuestionnaireBusinessRoleDto businessDto2 = new QuestionnaireBusinessRoleDto();

        when(businessRoleRepository.findAll()).thenReturn(List.of(businessEntity1, businessEntity2));
        when(questionnaireMapper.toBusinessRoleDto(businessEntity1)).thenReturn(businessDto1);
        when(questionnaireMapper.toBusinessRoleDto(businessEntity2)).thenReturn(businessDto2);

        // Act
        List<QuestionnaireBusinessRoleDto> result = questionnaireService.findAllBusiness();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(businessDto1));
        assertTrue(result.contains(businessDto2));
        verify(businessRoleRepository, times(1)).findAll();
        verify(questionnaireMapper, times(1)).toBusinessRoleDto(businessEntity1);
        verify(questionnaireMapper, times(1)).toBusinessRoleDto(businessEntity2);
    }

    @Test
    void findAllBusiness_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(businessRoleRepository.findAll()).thenReturn(List.of());

        // Act
        List<QuestionnaireBusinessRoleDto> result = questionnaireService.findAllBusiness();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(businessRoleRepository, times(1)).findAll();
        verifyNoInteractions(questionnaireMapper);
    }

    @Test
    void findAllEducative_shouldReturnAllEducativeRolesSuccessfully() {
        // Arrange
        QuestionnaireEducativeRoleEntity educativeEntity1 = new QuestionnaireEducativeRoleEntity();
        QuestionnaireEducativeRoleEntity educativeEntity2 = new QuestionnaireEducativeRoleEntity();

        QuestionnaireEducativeRoleDto educativeDto1 = new QuestionnaireEducativeRoleDto();
        QuestionnaireEducativeRoleDto educativeDto2 = new QuestionnaireEducativeRoleDto();

        when(educativeRoleRepository.findAll()).thenReturn(List.of(educativeEntity1, educativeEntity2));
        when(questionnaireMapper.toEducativeRoleDto(educativeEntity1)).thenReturn(educativeDto1);
        when(questionnaireMapper.toEducativeRoleDto(educativeEntity2)).thenReturn(educativeDto2);

        // Act
        List<QuestionnaireEducativeRoleDto> result = questionnaireService.findAllEducative();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(educativeDto1));
        assertTrue(result.contains(educativeDto2));
        verify(educativeRoleRepository, times(1)).findAll();
        verify(questionnaireMapper, times(1)).toEducativeRoleDto(educativeEntity1);
        verify(questionnaireMapper, times(1)).toEducativeRoleDto(educativeEntity2);
    }

    @Test
    void findAllEducative_shouldReturnEmptyListWhenNoRecords() {
        // Arrange
        when(educativeRoleRepository.findAll()).thenReturn(List.of());

        // Act
        List<QuestionnaireEducativeRoleDto> result = questionnaireService.findAllEducative();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(educativeRoleRepository, times(1)).findAll();
        verifyNoInteractions(questionnaireMapper);
    }
}