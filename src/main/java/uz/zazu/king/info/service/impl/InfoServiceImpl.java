package uz.zazu.king.info.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.entity.InfoEntity;
import uz.zazu.king.info.enums.Module;
import uz.zazu.king.info.exception.InfoNotFoundException;
import uz.zazu.king.info.exception.ModuleNotFoundException;
import uz.zazu.king.info.mapper.InfoMapper;
import uz.zazu.king.info.repository.InfoRepository;
import uz.zazu.king.info.service.InfoService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private static final Map<Module, String> moduleLinksMap = new HashMap<>() {{
        put(Module.MAIN, "https://google.com");
        put(Module.BASE, "https://amazon.com");
        put(Module.EDUCATOR, "https://uber.com");
        put(Module.MANAGER, "https://youtube.com");
        put(Module.BUSINESS_ASSISTANT, "https://linkedin.com");
    }};
    private final InfoRepository infoRepository;
    private final InfoMapper infoMapper;

    @Override
    public List<InfoDto> getByModule(Module module) {
        if (module == null) {
            throw new ModuleNotFoundException();
        }
        var entities = infoRepository.findAllActiveByModule(module.name());
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }

        return entities
                .stream()
                .sorted(Comparator.comparing(InfoEntity::getCreatedAt))
                .map(infoMapper::toInfoDto)
                .toList();
    }

    @Override
    public ModuleInfoDto getModule(Module module) {
        var infoList = getByModule(module);
        var moduleName = module.name();
        var moduleLink = moduleLinksMap.get(module);
        return infoMapper.toModuleInfoDto(moduleName, moduleLink, infoList);
    }


    @Override
    public InfoDto create(InfoDto infoDto) {
        var entity = infoMapper.toInfoEntity(infoDto);
        var savedEntity = infoRepository.save(entity);
        return infoMapper.toInfoDto(savedEntity);
    }

    @Override
    public InfoDto getById(String id) {
        var entity = infoRepository.findActiveById(id);
        if (entity == null) {
            throw new InfoNotFoundException(id);
        }

        return infoMapper.toInfoDto(entity);
    }

    @Override
    public InfoDto update(String id, InfoDto infoDto) {
        var entity = infoRepository.findActiveById(id);
        if (entity == null) {
            throw new InfoNotFoundException(id);
        }

        infoMapper.updateEntityFromDto(infoDto, entity);
        var updatedEntity = infoRepository.save(entity);
        return infoMapper.toInfoDto(updatedEntity);
    }

    @Override
    public void remove(String id) {
        var entity = infoRepository.findActiveById(id);
        if (entity == null) {
            throw new InfoNotFoundException(id);
        }
        entity.setActive(false);
        infoRepository.save(entity);
    }
}
