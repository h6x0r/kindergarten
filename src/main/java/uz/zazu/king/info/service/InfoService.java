package uz.zazu.king.info.service;

import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.enums.Module;

import java.util.List;

public interface InfoService {
    List<InfoDto> getByModule(Module module);

    ModuleInfoDto getModule(Module module);

    InfoDto create(InfoDto infoDto);

    InfoDto getById(String id);

    InfoDto update(String id, InfoDto infoDto);

    void remove(String id);
}
