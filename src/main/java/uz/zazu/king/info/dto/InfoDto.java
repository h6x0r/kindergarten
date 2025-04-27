package uz.zazu.king.info.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.zazu.king.info.enums.Module;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoDto {
    private String id;
    @NotBlank(message = "Название не должно быть пустым")
    private String title;
    @NotBlank(message = "Описание не должно быть пустым")
    private String description;
    @NotBlank(message = "Модуль не должен быть пустым")
    private Module module;
    private List<InfoLinkDto> links;
}
