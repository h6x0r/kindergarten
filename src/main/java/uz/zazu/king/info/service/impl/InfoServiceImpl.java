package uz.zazu.king.info.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.entity.InfoLinkEntity;
import uz.zazu.king.info.enums.Module;
import uz.zazu.king.info.exception.ModuleNotFoundException;
import uz.zazu.king.info.mapper.InfoMapper;
import uz.zazu.king.info.repository.InfoRepository;
import uz.zazu.king.info.repository.ModuleRepository;
import uz.zazu.king.info.service.InfoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;
    private final ModuleRepository moduleRepository;
    private final InfoMapper infoMapper;

    // todo.. переписать в мапперы
    @Override
    public List<InfoDto> getInfoByModule(Module module) {
        var entities = infoRepository.findAllByModule(module.name());
        return infoMapper.toInfoDtoList(entities);
    }

    @Override
    public ModuleInfoDto getModuleInfo(Module module) {
        var infoList = getInfoByModule(module);
        var moduleEntity = moduleRepository.findAllByModuleName(module.name())
                .stream()
                .filter(m -> m.getModuleName() == module)
                .findFirst()
                .orElseThrow(ModuleNotFoundException::new);

        return infoMapper.toModuleInfoDto(moduleEntity, infoList);
    }


    @Override
    public InfoDto createInfo(InfoDto infoDto) {
        var entity = infoMapper.toInfoEntity(infoDto);
        var savedEntity = infoRepository.save(entity);
        return infoMapper.toInfoDto(savedEntity);
    }

    @Override
    public InfoDto getInfoById(String id) {
        var entity = infoRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
        return infoMapper.toInfoDto(entity);
    }

    // todo.. mapping
    @Override
    public InfoDto updateInfo(String id, InfoDto infoDto) {
        var entity = infoRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException("Info not found with ID: " + id));
        entity.setTitle(infoDto.getTitle());
        entity.setDescription(infoDto.getDescription());
        entity.setLinks(infoDto.getLinks().stream()
                .map(link -> {
                    var newLink = new InfoLinkEntity();
                    newLink.setName(link.getName());
                    newLink.setUrl(link.getUrl());
                    return newLink;
                })
                .toList());
        var updatedEntity = infoRepository.save(entity);
        return infoMapper.toInfoDto(updatedEntity);
    }

    @Override
    public void deleteInfo(String id) {
        var entity = infoRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
        entity.setActive(false);
        infoRepository.save(entity);
    }
}
