package uz.zazu.king.info.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.entity.InfoEntity;
import uz.zazu.king.info.entity.ModuleEntity;
import uz.zazu.king.info.enums.Module;
import uz.zazu.king.info.exception.ModuleNotFoundException;
import uz.zazu.king.info.mapper.InfoMapper;
import uz.zazu.king.info.repository.InfoRepository;
import uz.zazu.king.info.repository.ModuleRepository;
import uz.zazu.king.info.service.impl.InfoServiceImpl;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InfoServiceImplTest {

    @Mock
    private InfoRepository infoRepository;

    @Mock
    private ModuleRepository moduleRepository;

    @Mock
    private InfoMapper infoMapper;

    @InjectMocks
    private InfoServiceImpl infoService;

    @Test
    public void testGetByModule_returnsDtoList() {
        var module = Module.EDUCATOR;
        var entities = new ArrayList<InfoEntity>();
        var dtoList = Collections.singletonList(new InfoDto());

        when(infoRepository.findAllActiveByModule(module.name())).thenReturn(entities);
        when(infoMapper.toInfoDtoList(entities)).thenReturn(dtoList);

        var result = infoService.getByModule(module);

        assertEquals(dtoList, result);
        verify(infoRepository, times(1)).findAllActiveByModule(module.name());
        verify(infoMapper, times(1)).toInfoDtoList(entities);
    }

    @Test
    public void testGetModule_returnsModuleInfoDto() {
        var entities = new ArrayList<InfoEntity>();
        var infoList = Collections.singletonList(new InfoDto());

        when(infoRepository.findAllActiveByModule(Module.MANAGER.name())).thenReturn(entities);
        when(infoMapper.toInfoDtoList(entities)).thenReturn(infoList);

        var dummyModuleEntity = new ModuleEntity();
        dummyModuleEntity.setModuleName(Module.MANAGER);
        when(moduleRepository.findAllByModuleName(Module.MANAGER.name()))
                .thenReturn(Collections.singletonList(dummyModuleEntity));

        var expectedModuleInfoDto = new ModuleInfoDto();
        when(infoMapper.toModuleInfoDto(dummyModuleEntity, infoList)).thenReturn(expectedModuleInfoDto);

        var result = infoService.getModule(Module.MANAGER);

        assertEquals(expectedModuleInfoDto, result);
    }

    @Test
    public void testGetModule_moduleNotFound_exception() {
        var entities = new ArrayList<InfoEntity>();
        when(infoRepository.findAllActiveByModule(Module.BUSINESS_ASSISTANT.name())).thenReturn(entities);
        when(infoMapper.toInfoDtoList(entities)).thenReturn(Collections.singletonList(new InfoDto()));

        when(moduleRepository.findAllByModuleName(Module.BUSINESS_ASSISTANT.name())).thenReturn(Collections.emptyList());

        assertThrows(ModuleNotFoundException.class, () -> infoService.getModule(Module.BUSINESS_ASSISTANT));
    }

    @Test
    public void testCreate_returnsCreatedInfoDto() {
        var inputDto = new InfoDto();
        var entity = new InfoEntity();
        var savedEntity = new InfoEntity();
        var expectedDto = new InfoDto();

        when(infoMapper.toInfoEntity(inputDto)).thenReturn(entity);
        when(infoRepository.save(entity)).thenReturn(savedEntity);
        when(infoMapper.toInfoDto(savedEntity)).thenReturn(expectedDto);

        var result = infoService.create(inputDto);
        assertEquals(expectedDto, result);
    }

    @Test
    public void testGetById_success() {
        var id = "123";
        var entity = new InfoEntity();
        var expectedDto = new InfoDto();

        when(infoRepository.findActiveById(id)).thenReturn(entity);
        when(infoMapper.toInfoDto(entity)).thenReturn(expectedDto);

        InfoDto result = infoService.getById(id);
        assertEquals(expectedDto, result);
    }

    @Test
    public void testGetById_notFound_exception() {
        var id = "123";
        when(infoRepository.findActiveById(id)).thenReturn(null);
        assertThrows(ModuleNotFoundException.class, () -> infoService.getById(id));
    }

    @Test
    public void testUpdate_success() {
        var id = "123";
        var inputDto = new InfoDto();
        var entity = new InfoEntity();
        var updatedEntity = new InfoEntity();
        var expectedDto = new InfoDto();

        when(infoRepository.findActiveById(id)).thenReturn(entity);
        when(infoRepository.save(entity)).thenReturn(updatedEntity);
        when(infoMapper.toInfoDto(updatedEntity)).thenReturn(expectedDto);

        InfoDto result = infoService.update(id, inputDto);
        assertEquals(expectedDto, result);
        verify(infoMapper, times(1)).updateEntityFromDto(inputDto, entity);
    }

    @Test
    public void testUpdate_notFound_exception() {
        var id = "123";
        var inputDto = new InfoDto();
        when(infoRepository.findActiveById(id)).thenReturn(null);
        assertThrows(ModuleNotFoundException.class, () -> infoService.update(id, inputDto));
    }

    @Test
    public void testRemove_success() {
        var id = "123";
        var entity = spy(new InfoEntity());
        entity.setActive(true); // исходное состояние
        when(infoRepository.findActiveById(id)).thenReturn(entity);

        infoService.remove(id);

        verify(entity, times(1)).setActive(false);
        verify(infoRepository, times(1)).save(entity);
    }

    @Test
    public void testRemove_notFound_exception() {
        var id = "123";
        when(infoRepository.findActiveById(id)).thenReturn(null);
        assertThrows(ModuleNotFoundException.class, () -> infoService.remove(id));
    }

}
