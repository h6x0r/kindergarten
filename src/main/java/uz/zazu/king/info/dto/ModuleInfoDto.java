package uz.zazu.king.info.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleInfoDto {
    private String moduleName;
    private String tableLink;
    List<InfoDto> infoList;
}
