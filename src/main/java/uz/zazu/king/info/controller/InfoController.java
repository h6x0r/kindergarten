package uz.zazu.king.info.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

//    @GetMapping("/hr")
//    public String getHrInfo() {
//        return "HR";
//    }
//
//    @GetMapping("/hr2")

    /// /    @PreAuthorize("hasRole('SUPER_ADMIN')")
//    public String getHrInfo2() {
//        return "HR";
//    }
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
        try {
            return infoService.getModule(Module.MAIN);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/base")
    public ModuleInfoDto getBaseInfo() {
        return infoService.getModule(Module.BASE);
    }

    @GetMapping("/educator")
    public ModuleInfoDto getEducatorInfo() {
        return infoService.getModule(Module.EDUCATOR);
    }

    @GetMapping("/manager")
    public ModuleInfoDto getManagerInfo() {
        return infoService.getModule(Module.MANAGER);
    }

    @GetMapping("/assistant")
    public ModuleInfoDto getAssistantInfo() {
        return infoService.getModule(Module.BUSINESS_ASSISTANT);
    }

}
