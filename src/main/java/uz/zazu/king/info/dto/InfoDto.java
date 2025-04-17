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
public class InfoDto {
    private String id;
    @NotBlank(message = "Название не должно быть пустым")
    private String title;
    @NotBlank(message = "Описание не должно быть пустым")
    private String description;
    @NotBlank(message = "Модуль не должен быть пустым")
    private String module;
    private List<InfoLinkDto> links;
    private int index;
}
