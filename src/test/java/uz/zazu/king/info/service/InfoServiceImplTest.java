//package uz.zazu.king.info.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import uz.zazu.king.info.dto.InfoDto;
//import uz.zazu.king.info.dto.ModuleInfoDto;
//import uz.zazu.king.info.entity.InfoEntity;
//import uz.zazu.king.info.enums.Module;
//import uz.zazu.king.info.exception.InfoNotFoundException;
//import uz.zazu.king.info.exception.ModuleNotFoundException;
//import uz.zazu.king.info.mapper.InfoMapper;
//import uz.zazu.king.info.repository.InfoRepository;
//import uz.zazu.king.info.service.impl.InfoServiceImpl;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.Mockito.spy;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class InfoServiceImplTest {
//
//    @Mock
//    private InfoRepository infoRepository;
//
//    @Mock
//    private InfoMapper infoMapper;
//
//    @InjectMocks
//    private InfoServiceImpl infoService;
//
//    @Test
//    public void testGetByModule_noEntitiesFound_returnsEmptyList() {
//        var module = Module.EDUCATOR;
//
//        when(infoRepository.findAllActiveByModule(module.name())).thenReturn(Collections.emptyList());
//
//        var result = infoService.getByModule(module);
//
//        assertEquals(Collections.emptyList(), result);
//        verify(infoRepository, times(1)).findAllActiveByModule(module.name());
//        verify(infoMapper, times(0)).toInfoDtoList(anyList());
//    }
//
//    @Test
//    void getByModule_oneRecord_ok() {
//        // given
//        var module = Module.EDUCATOR;
//
//        var entity = InfoEntity.builder()
//                .id("infoId1")
//                .title("Test Title")
//                .description("Test Description")
//                .module(module)
//                .isActive(true)
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        var dto = InfoDto.builder()
//                .id("infoId1")
//                .title("Test Title")
//                .description("Test Description")
//                .build();
//
//        when(infoRepository.findAllActiveByModule(module.name()))
//                .thenReturn(List.of(entity));
//        when(infoMapper.toInfoDto(entity)).thenReturn(dto);
//
//        // when
//        var result = infoService.getByModule(module);
//
//        // then
//        assertEquals(List.of(dto), result);
//        verify(infoRepository).findAllActiveByModule(module.name());
//        verify(infoMapper).toInfoDto(entity);
//    }
//
//    @Test
//    void getByModule_threeRecords_ok() {
//        var module = Module.EDUCATOR;
//
//        var entities = List.of(
//                InfoEntity.builder()
//                        .id("infoId1").title("Title 1").description("Desc 1")
//                        .module(module).isActive(true)
//                        .createdAt(LocalDateTime.now().minusDays(3)).build(),
//                InfoEntity.builder()
//                        .id("infoId2").title("Title 2").description("Desc 2")
//                        .module(module).isActive(true)
//                        .createdAt(LocalDateTime.now().minusDays(2)).build(),
//                InfoEntity.builder()
//                        .id("infoId3").title("Title 3").description("Desc 3")
//                        .module(module).isActive(true)
//                        .createdAt(LocalDateTime.now().minusDays(1)).build()
//        );
//
//        var expectedDtos = List.of(
//                InfoDto.builder().id("infoId1").title("Title 1").description("Desc 1").build(),
//                InfoDto.builder().id("infoId2").title("Title 2").description("Desc 2").build(),
//                InfoDto.builder().id("infoId3").title("Title 3").description("Desc 3").build()
//        );
//
//        when(infoRepository.findAllActiveByModule(module.name()))
//                .thenReturn(entities);
//
//        when(infoMapper.toInfoDto(any(InfoEntity.class))).thenAnswer(invocation -> {
//            InfoEntity e = invocation.getArgument(0);
//            return InfoDto.builder()
//                    .id(e.getId())
//                    .title(e.getTitle())
//                    .description(e.getDescription())
//                    .build();
//        });
//
//        var result = infoService.getByModule(module);
//
//        assertEquals(expectedDtos, result);
//        verify(infoRepository).findAllActiveByModule(module.name());
//        verify(infoMapper, times(3)).toInfoDto(any(InfoEntity.class));
//    }
//
//    @Test
//    public void testGetByModule_nullModule_throwsException() {
//        assertThrows(ModuleNotFoundException.class, () -> infoService.getByModule(null));
//        verify(infoRepository, times(0)).findAllActiveByModule("");
//        verify(infoMapper, times(0)).toInfoDtoList(anyList());
//    }
//
//    @Test
//    void testGetModule_returnsModuleInfoDto() {
//        var module = Module.MANAGER;
//        var entity1 = InfoEntity.builder()
//                .id("id1")
//                .title("Title1")
//                .description("Desc1")
//                .module(module)
//                .isActive(true)
//                .createdAt(LocalDateTime.now().minusDays(2))
//                .build();
//
//        var entity2 = InfoEntity.builder()
//                .id("id2")
//                .title("Title2")
//                .description("Desc2")
//                .module(module)
//                .isActive(true)
//                .createdAt(LocalDateTime.now().minusDays(1))
//                .build();
//
//        var entities = List.of(entity1, entity2);
//
//        var dto1 = InfoDto.builder()
//                .id("id1")
//                .title("Title1")
//                .description("Desc1")
//                .build();
//
//        var dto2 = InfoDto.builder()
//                .id("id2")
//                .title("Title2")
//                .description("Desc2")
//                .build();
//
//        var infoList = List.of(dto1, dto2);
//
//        var expectedModuleInfoDto = ModuleInfoDto.builder()
//                .moduleName(module.name())
//                .tableLink("https://youtube.com")
//                .infoList(infoList)
//                .build();
//
//        when(infoRepository.findAllActiveByModule(module.name()))
//                .thenReturn(entities);
//
//        when(infoMapper.toInfoDto(entity1)).thenReturn(dto1);
//        when(infoMapper.toInfoDto(entity2)).thenReturn(dto2);
//
//        when(infoMapper.toModuleInfoDto(module.name(), "https://youtube.com", infoList))
//                .thenReturn(expectedModuleInfoDto);
//
//        var result = infoService.getModule(module);
//
//        assertEquals(expectedModuleInfoDto, result);
//    }
//
//
//    @Test
//    public void testGetModule_moduleNotFound_exception() {
//        assertThrows(ModuleNotFoundException.class, () -> infoService.getModule(null));
//    }
//
//    @Test
//    public void testCreate_returnsCreatedInfoDto() {
//        var inputDto = new InfoDto();
//        var entity = new InfoEntity();
//        var savedEntity = new InfoEntity();
//        var expectedDto = new InfoDto();
//
//        when(infoMapper.toInfoEntity(inputDto)).thenReturn(entity);
//        when(infoRepository.save(entity)).thenReturn(savedEntity);
//        when(infoMapper.toInfoDto(savedEntity)).thenReturn(expectedDto);
//
//        var result = infoService.create(inputDto);
//        assertEquals(expectedDto, result);
//    }
//
//    @Test
//    public void testGetById_success() {
//        var id = "123";
//        var entity = new InfoEntity();
//        var expectedDto = new InfoDto();
//
//        when(infoRepository.findActiveById(id)).thenReturn(entity);
//        when(infoMapper.toInfoDto(entity)).thenReturn(expectedDto);
//
//        InfoDto result = infoService.getById(id);
//        assertEquals(expectedDto, result);
//    }
//
//    @Test
//    public void testGetById_notFound_exception() {
//        var id = "123";
//        when(infoRepository.findActiveById(id)).thenReturn(null);
//        assertThrows(InfoNotFoundException.class, () -> infoService.getById(id));
//    }
//
//    @Test
//    public void testUpdate_success() {
//        var id = "123";
//        var inputDto = new InfoDto();
//        var entity = new InfoEntity();
//        var updatedEntity = new InfoEntity();
//        var expectedDto = new InfoDto();
//
//        when(infoRepository.findActiveById(id)).thenReturn(entity);
//        when(infoRepository.save(entity)).thenReturn(updatedEntity);
//        when(infoMapper.toInfoDto(updatedEntity)).thenReturn(expectedDto);
//
//        InfoDto result = infoService.update(id, inputDto);
//        assertEquals(expectedDto, result);
//        verify(infoMapper, times(1)).updateEntityFromDto(inputDto, entity);
//    }
//
//    @Test
//    public void testUpdate_notFound_exception() {
//        var id = "123";
//        var inputDto = new InfoDto();
//        when(infoRepository.findActiveById(id)).thenReturn(null);
//        assertThrows(InfoNotFoundException.class, () -> infoService.update(id, inputDto));
//    }
//
//    @Test
//    public void testRemove_success() {
//        var id = "123";
//        var entity = spy(new InfoEntity());
//        entity.setActive(true); // исходное состояние
//        when(infoRepository.findActiveById(id)).thenReturn(entity);
//
//        infoService.remove(id);
//
//        verify(entity, times(1)).setActive(false);
//        verify(infoRepository, times(1)).save(entity);
//    }
//
//    @Test
//    public void testRemove_notFound_exception() {
//        var id = "123";
//        when(infoRepository.findActiveById(id)).thenReturn(null);
//        assertThrows(InfoNotFoundException.class, () -> infoService.remove(id));
//    }
//
//}
