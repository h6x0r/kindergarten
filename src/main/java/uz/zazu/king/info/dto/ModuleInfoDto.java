package uz.zazu.king.info.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleInfoDto {
    private String moduleName;
    private String tableLink;
    private List<InfoDto> infoList;
}
