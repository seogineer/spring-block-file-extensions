package com.seogineer.springblockfileextensions.dto.extension;

import com.seogineer.springblockfileextensions.domain.extension.Extension;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ExtensionCreateRequestDto {
    @Size(max = 20, message = "글자수 20자를 초과할 수 없습니다.")
    private String name;

    @Builder
    public ExtensionCreateRequestDto(String name){
        this.name = name;
    }

    public Extension toEntity(){
        return Extension.builder()
                .name(name)
                .useYn(true)
                .fixedYn(false)
                .build();
    }
}
