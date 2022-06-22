package com.seogineer.springblockfileextensions.service;

import com.seogineer.springblockfileextensions.common.exception.ExtensionNumberExceedException;
import com.seogineer.springblockfileextensions.domain.extension.Extension;
import com.seogineer.springblockfileextensions.domain.extension.ExtensionRepository;
import com.seogineer.springblockfileextensions.dto.extension.ExtensionCreateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExtensionServiceTest {
    @Autowired
    ExtensionRepository extensionRepository;

    @Autowired
    ExtensionService extensionService;

    @AfterEach
    public void cleanup() {
        extensionRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_extension테이블에_저장된다 () {
        ExtensionCreateRequestDto dto = ExtensionCreateRequestDto.builder()
                .name("test")
                .build();

        extensionService.create(dto);

        Extension extension = extensionRepository.findByName("test");
        assertThat(extension.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void FixedYn이_변경(){
        ExtensionCreateRequestDto dto = ExtensionCreateRequestDto.builder()
                .name("test")
                .build();
        extensionService.create(dto);

        Extension beforeExtension = extensionRepository.findByName("test");

        extensionService.changeFixedYn("test");

        Extension afterExtension = extensionRepository.findByName("test");
        assertThat(beforeExtension.getFixedYn()).isNotEqualTo(afterExtension.getFixedYn());
    }

    @Test
    public void 확장자_개수_200개_초과(){
        Assertions.assertThrows(ExtensionNumberExceedException.class, () -> {
            for(int i = 0; i < 201; i++){
                extensionService.create(new ExtensionCreateRequestDto("ext" + i));
            }
        });
    }
}
