package uz.zazu.king.info.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.info.dto.InfoDto;
import uz.zazu.king.info.dto.ModuleInfoDto;
import uz.zazu.king.info.enums.Module;
import uz.zazu.king.info.service.InfoService;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/hr")
    public String getHrInfo() {
        return "HR";
    }

    @GetMapping("/hr2")
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String getHrInfo2() {
        return "HR";
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public InfoDto create(@RequestBody InfoDto infoDto) {
        return infoService.create(infoDto);
    }

    @PutMapping("/update/{id}")
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public InfoDto update(@PathVariable String id, @RequestBody InfoDto infoDto) {
        return infoService.update(id, infoDto);
    }

    @DeleteMapping("/remove/{id}")
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public void remove(@NotBlank @PathVariable String id) {
        infoService.remove(id);
    }

    @GetMapping("/main")
    public ModuleInfoDto getMainInfo() {
        return infoService.getModule(Module.MAIN);
//        return getModuleInfo("Main", "https://apple.com");
    }

    @GetMapping("/base")
    public ModuleInfoDto getBaseInfo() {
        return infoService.getModule(Module.BASE);
//        return getModuleInfo("Base", "https://google.com");
    }

    @GetMapping("/educator")
    public ModuleInfoDto getEducatorInfo() {
        return infoService.getModule(Module.EDUCATOR);
//        return getModuleInfo("Educator", "https://google.com");
    }

    @GetMapping("/manager")
    public ModuleInfoDto getManagerInfo() {
        return infoService.getModule(Module.MANAGER);
//        return getModuleInfo("Manager", "https://amazon.com");
    }

    @GetMapping("/assistant")
    public ModuleInfoDto getAssistantInfo() {
        return infoService.getModule(Module.BUSINESS_ASSISTANT);
//        return getModuleInfo("Assistant", "https://whoop.com");
    }

//    private ModuleInfoDto getModuleInfo(String title, String tableLink) {
//        int i = 1;
//        InfoDto sample1 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 1")
//                .description("Sample description for title 1")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Google").url("https://google.com").build(),
//                        InfoLinkDto.builder().name("GitHub").url("https://github.com").build()
//                ))
//                .build();
//
//        InfoDto sample2 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 2")
//                .description("Sample description for title 2")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Spring").url("https://spring.io").build(),
//                        InfoLinkDto.builder().name("Jakarta EE").url("https://jakarta.ee").build()
//                ))
//                .build();
//
//        InfoDto sample3 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 3")
//                .description("Sample description for title 3")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Facebook").url("https://facebook.com").build(),
//                        InfoLinkDto.builder().name("Twitter").url("https://twitter.com").build()
//                ))
//                .build();
//
//        InfoDto sample4 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 4")
//                .description("Sample description for title 4")
//                .links(List.of(
//                        InfoLinkDto.builder().name("LinkedIn").url("https://linkedin.com").build(),
//                        InfoLinkDto.builder().name("Medium").url("https://medium.com").build()
//                ))
//                .build();
//
//        InfoDto sample5 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 5")
//                .description("Sample description for title 5")
//                .links(List.of(
//                        InfoLinkDto.builder().name("YouTube").url("https://youtube.com").build(),
//                        InfoLinkDto.builder().name("Reddit").url("https://reddit.com").build()
//                ))
//                .build();
//
//        InfoDto sample6 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 6")
//                .description("Sample description for title 6")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Stack Overflow").url("https://stackoverflow.com").build(),
//                        InfoLinkDto.builder().name("Kotlin").url("https://kotlinlang.org").build()
//                ))
//                .build();
//
//        InfoDto sample7 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 7")
//                .description("Sample description for title 7")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Apache").url("https://apache.org").build(),
//                        InfoLinkDto.builder().name("Linux").url("https://linux.org").build()
//                ))
//                .build();
//
//        InfoDto sample8 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 8")
//                .description("Sample description for title 8")
//                .links(List.of(
//                        InfoLinkDto.builder().name("AWS").url("https://aws.amazon.com").build(),
//                        InfoLinkDto.builder().name("Azure").url("https://azure.microsoft.com").build()
//                ))
//                .build();
//
//        InfoDto sample9 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 9")
//                .description("Sample description for title 9")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Oracle").url("https://oracle.com").build(),
//                        InfoLinkDto.builder().name("GCP").url("https://cloud.google.com").build()
//                ))
//                .build();
//
//        InfoDto sample10 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 10")
//                .description("Sample description for title 10")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Python").url("https://python.org").build(),
//                        InfoLinkDto.builder().name("Java").url("https://oracle.com/java").build()
//                ))
//                .build();
//
//        InfoDto sample11 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 11")
//                .description("Sample description for title 11")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Docker").url("https://docker.com").build(),
//                        InfoLinkDto.builder().name("Kubernetes").url("https://kubernetes.io").build()
//                ))
//                .build();
//
//        InfoDto sample12 = InfoDto.builder()
//                .id(i++)
//                .title("Sample Title 12")
//                .description("Sample description for title 12")
//                .links(List.of(
//                        InfoLinkDto.builder().name("Bing").url("https://bing.com").build(),
//                        InfoLinkDto.builder().name("Yahoo").url("https://yahoo.com").build()
//                ))
//                .build();
//
//        var list = List.of(sample1, sample2, sample3, sample4, sample5, sample6, sample7, sample8, sample9, sample10, sample11, sample12);
//
//        return ModuleInfoDto.builder()
//                .moduleName(title)
//                .tableLink(tableLink)
//                .infoList(list)
//                .build();
//    }


}
