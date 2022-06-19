package com.seogineer.springblockfileextensions.dto.extension;

import com.seogineer.springblockfileextensions.domain.extension.Extension;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtensionUpdateRequestDto {
    private String name;
    private Boolean useYn;

    @Builder
    public ExtensionUpdateRequestDto(String name, Boolean useYn){
        this.name = name;
        this.useYn = useYn;
    }

    public Extension toEntity(){
        return Extension.builder()
                .name(name)
                .useYn(useYn)
                .fixedYn(false)
                .build();
    }
}
