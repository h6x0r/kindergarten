package uz.zazu.king.info.service;

import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.enums.Module;

import java.util.List;

public interface InfoService {
    List<InfoDto> getInfoByModule(Module module);

    ModuleInfoDto getModuleInfo(Module module);

    InfoDto createInfo(InfoDto infoDto);

    InfoDto getInfoById(String id);

    InfoDto updateInfo(String id, InfoDto infoDto);

    void deleteInfo(String id);
}
