package uz.zazu.king.info.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String moduleName;
    private String tableLink;
    private List<InfoDto> infoList;
}
