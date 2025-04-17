package uz.zazu.king.info.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoLinkDto {
    @NotBlank(message = "Название ссылки не должно быть пустым")
    private String name;
    @NotBlank(message = "URL ссылки не должен быть пустым")
    private String url;
}