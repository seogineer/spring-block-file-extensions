package com.seogineer.springblockfileextensions.domain.extension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ExtensionRepositoryTest {
    @Autowired
    ExtensionRepository extensionRepository;

    @AfterEach
    public void cleanup() {
        extensionRepository.deleteAll();
    }

    @Test
    public void 확장자_저장_불러오기(){
        extensionRepository.save(Extension.builder()
                .name("test")
                .useYn(true)
                .fixedYn(true)
                .build());

        Extension extension = extensionRepository.findByName("test");

        assertThat(extension.getName(), is("test"));
    }

    @Test
    public void 사용중인_확장자만_불러오기() {
        extensionRepository.save(Extension.builder()
                .name("aaa")
                .useYn(true)
                .fixedYn(true)
                .build());

        extensionRepository.save(Extension.builder()
                .name("bbb")
                .useYn(false)
                .fixedYn(true)
                .build());

        List<Extension> extensionList = extensionRepository.findByUseYnTrue();

        assertThat(extensionList.size(), is(1));
    }
}
