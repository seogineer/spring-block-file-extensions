package com.seogineer.springblockfileextensions.dto.extension;

import com.seogineer.springblockfileextensions.domain.extension.Extension;
import lombok.Getter;

@Getter
public class ExtensionResponseDto {
    private Long id;
    private String name;
    private Boolean useYn;
    private Boolean fixedYn;

    public ExtensionResponseDto(Extension entity){
        id = entity.getId();
        name = entity.getName();
        useYn = entity.getUseYn();
        fixedYn = entity.getFixedYn();
    }
}
