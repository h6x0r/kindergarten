package uz.zazu.king.employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileNannyDto;
import uz.zazu.king.employee.entity.CandidateProfileBusinessRoleEntity;
import uz.zazu.king.employee.entity.CandidateProfileEducatorRoleEntity;
import uz.zazu.king.employee.entity.CandidateProfileNannyRoleEntity;
import uz.zazu.king.employee.mapper.CandidateProfileMapper;
import uz.zazu.king.employee.repository.CandidateProfileBusinessRoleRepository;
import uz.zazu.king.employee.repository.CandidateProfileEducatorRoleRepository;
import uz.zazu.king.employee.repository.CandidateProfileNannyRoleRepository;
import uz.zazu.king.employee.service.impl.CandidateProfileServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateProfileServiceImplTest {

    @Mock
    private CandidateProfileMapper candidateProfileMapper;

    @Mock
    private CandidateProfileBusinessRoleRepository businessRoleRepository;

    @Mock
    private CandidateProfileEducatorRoleRepository educativeRoleRepository;

    @Mock
    private CandidateProfileNannyRoleRepository nannyRoleRepository;

    @InjectMocks
    private CandidateProfileServiceImpl questionnaireService;

    @Test
    void create_shouldSaveBusinessRoleSuccessfully() {
        // Arrange
        var businessRoleDto = new CandidateProfileBusinessDto();
        var businessRoleEntity = new CandidateProfileBusinessRoleEntity();
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
        var educativeRoleEntity = new CandidateProfileEducatorRoleEntity();
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
        var opt = Optional.of(new CandidateProfileBusinessRoleEntity());
        var businessRoleEntity = opt.get();
        var businessRoleDto = new CandidateProfileBusinessDto();

        when(businessRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(opt);
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
        var opt = Optional.of(new CandidateProfileEducatorRoleEntity());
        var educativeRoleEntity = opt.get();
        var educativeRoleDto = new CandidateProfileEducatorDto();

        when(educativeRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(opt);
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
        when(businessRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());
        when(educativeRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(QuestionnaireNotFoundException.class, () -> questionnaireService.findById(id));
        assertEquals("Анкета с идентификатором не найдена: " + id, exception.getMessage());

        verify(businessRoleRepository, times(1)).findByIdAndIsActiveTrue(id);
        verify(educativeRoleRepository, times(1)).findByIdAndIsActiveTrue(id);
    }

    @Test
    void findById_shouldThrowExceptionWhenIdIsNullOrEmpty() {
        // Act & Assert
        assertThrows(QuestionnaireNotFoundException.class, () -> questionnaireService.findById(null));
        assertThrows(QuestionnaireNotFoundException.class, () -> questionnaireService.findById(""));
    }

    @Test
    void findAll_shouldReturnAllQuestionnairesSuccessfully() {
        // Arrange
        var businessEntity = CandidateProfileBusinessRoleEntity.builder()
                .isActive(true)
                .build();
        var educativeEntity = CandidateProfileEducatorRoleEntity.builder()
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
        var businessEntity1 = new CandidateProfileBusinessRoleEntity();
        var businessEntity2 = new CandidateProfileBusinessRoleEntity();

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
        var educativeEntity1 = new CandidateProfileEducatorRoleEntity();
        var educativeEntity2 = new CandidateProfileEducatorRoleEntity();

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


    @Test
    void create_shouldSaveNannyRoleSuccessfully() {
        // Arrange
        var nannyDto = new CandidateProfileNannyDto();
        var nannyEntity = new CandidateProfileNannyRoleEntity();

        // Act
        when(candidateProfileMapper.toNannyRoleEntity(nannyDto)).thenReturn(nannyEntity);
        when(nannyRoleRepository.save(nannyEntity)).thenReturn(nannyEntity);
        when(candidateProfileMapper.toNannyRoleDto(nannyEntity)).thenReturn(nannyDto);

        var result = questionnaireService.create(nannyDto);

        // Assert
        assertNotNull(result);
        assertSame(nannyDto, result);
        verify(candidateProfileMapper).toNannyRoleEntity(nannyDto);
        verify(nannyRoleRepository).save(nannyEntity);
        verify(candidateProfileMapper).toNannyRoleDto(nannyEntity);
    }

    @Test
    void findById_shouldReturnNannyRoleSuccessfully() {
        // Arrange
        var id = "nanny123";
        var opt = Optional.of(new CandidateProfileNannyRoleEntity());
        var nannyEntity = opt.get();
        var nannyDto = new CandidateProfileNannyDto();

        // Act
        when(nannyRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(opt);
        when(candidateProfileMapper.toNannyRoleDto(nannyEntity)).thenReturn(nannyDto);

        var result = questionnaireService.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(nannyDto, result);
        verify(nannyRoleRepository).findByIdAndIsActiveTrue(id);
        verify(candidateProfileMapper).toNannyRoleDto(nannyEntity);
    }

    @Test
    void findAllNanny_shouldReturnAllNannyRolesSuccessfully() {
        // Arrange
        var nannyEntity1 = new CandidateProfileNannyRoleEntity();
        var nannyEntity2 = new CandidateProfileNannyRoleEntity();

        var nannyDto1 = new CandidateProfileNannyDto();
        var nannyDto2 = new CandidateProfileNannyDto();

        // Act
        when(nannyRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of(nannyEntity1, nannyEntity2));
        when(candidateProfileMapper.toNannyRoleDto(nannyEntity1)).thenReturn(nannyDto1);
        when(candidateProfileMapper.toNannyRoleDto(nannyEntity2)).thenReturn(nannyDto2);

        var result = questionnaireService.findAllNanny();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(nannyDto1));
        assertTrue(result.contains(nannyDto2));
        verify(nannyRoleRepository).findAllByIsActiveTrue();
        verify(candidateProfileMapper).toNannyRoleDto(nannyEntity1);
        verify(candidateProfileMapper).toNannyRoleDto(nannyEntity2);
    }

    @Test
    void findAllNanny_shouldReturnEmptyListWhenNoRecords() {
        // Arrange & Act
        when(nannyRoleRepository.findAllByIsActiveTrue()).thenReturn(List.of());

        var result = questionnaireService.findAllNanny();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(nannyRoleRepository).findAllByIsActiveTrue();
        verifyNoInteractions(candidateProfileMapper);
    }

    @Test
    void updateNanny_shouldUpdateSuccessfully() {
        // Arrange
        var id = "nannyId";
        var dto = CandidateProfileNannyDto.builder()
                .fullName("Обновлённая Няня")
                .handlingChildRefusalToEat("Объяснить и показать пример")
                .build();

        var opt = Optional.of(CandidateProfileNannyRoleEntity.builder()
                .id(id)
                .fullName("Старая Няня")
                .isActive(true)
                .build()
        );
        var existingEntity = opt.get();

        // Act
        when(nannyRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(opt);
        when(nannyRoleRepository.save(existingEntity)).thenReturn(existingEntity);
        when(candidateProfileMapper.toNannyRoleDto(existingEntity)).thenReturn(dto);

        var updated = questionnaireService.update(id, dto);

        // Assert
        assertEquals("Обновлённая Няня", updated.getFullName());
        assertEquals("Объяснить и показать пример", ((CandidateProfileNannyDto) updated).getHandlingChildRefusalToEat());
        verify(nannyRoleRepository).save(existingEntity);
        verify(candidateProfileMapper).toNannyRoleDto(existingEntity);
    }

    @Test
    void removeNanny_shouldDeactivateSuccessfully() {
        // Arrange
        var id = "nannyToRemove";
        var opt = Optional.of(CandidateProfileNannyRoleEntity.builder().id(id).isActive(true).build());
        var entity = opt.get();
        // Act
        when(nannyRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(opt);

        questionnaireService.remove(id);

        // Assert
        assertFalse(entity.isActive());
        verify(nannyRoleRepository).save(entity);
    }

    @Test
    void findById_shouldThrowExceptionWhenNannyNotFound() {
        // Arrange
        var id = "notFound";

        // Act
        when(nannyRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());
        when(businessRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());
        when(educativeRoleRepository.findByIdAndIsActiveTrue(id)).thenReturn(Optional.empty());

        // Assert
        var exception = assertThrows(QuestionnaireNotFoundException.class,
                () -> questionnaireService.findById(id));

        assertEquals("Анкета с идентификатором не найдена: " + id, exception.getMessage());
    }

}