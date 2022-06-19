package com.seogineer.springblockfileextensions.web;

import com.seogineer.springblockfileextensions.dto.extension.ExtensionCreateRequestDto;
import com.seogineer.springblockfileextensions.dto.extension.ExtensionResponseDto;
import com.seogineer.springblockfileextensions.dto.extension.ExtensionUpdateRequestDto;
import com.seogineer.springblockfileextensions.service.ExtensionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WebRestController {
    private ExtensionService extensionService;

    @GetMapping("/list")
    public List<ExtensionResponseDto> select(){
        return extensionService.findAll();
    }

    @PostMapping("/extension")
    public Long create(@RequestBody ExtensionCreateRequestDto dto){
        return extensionService.create(dto);
    }

    @PatchMapping("/extension")
    public Long update(@RequestBody ExtensionUpdateRequestDto dto){
        return extensionService.update(dto);
    }

    @GetMapping("/extension")
    public List<ExtensionResponseDto> findByUseYnTrue(){
        return extensionService.findByUseYnTrue();
    }

    @PutMapping("/extension/fixed/{name}")
    public Long changeFixedYn(@PathVariable String name){
        return extensionService.changeFixedYn(name);
    }
}
