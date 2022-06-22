package com.seogineer.springblockfileextensions.web;

import com.seogineer.springblockfileextensions.dto.extension.ExtensionCreateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebRestControllerTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void 확장자명_20자_초과() {
        ExtensionCreateRequestDto extensionCreateRequestDto = new ExtensionCreateRequestDto();
        extensionCreateRequestDto.setName("abcdefghijklmnopqrstuvwxyz");
        Set<ConstraintViolation<ExtensionCreateRequestDto>> violations = validator.validate(extensionCreateRequestDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void 확장자명_대문자_입력() {
        ExtensionCreateRequestDto extensionCreateRequestDto = new ExtensionCreateRequestDto();
        extensionCreateRequestDto.setName("ABC");
        Set<ConstraintViolation<ExtensionCreateRequestDto>> violations = validator.validate(extensionCreateRequestDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void 확장자명_알파벳_이외_다른_문자_입력() {
        ExtensionCreateRequestDto extensionCreateRequestDto = new ExtensionCreateRequestDto();
        extensionCreateRequestDto.setName("가나다");
        Set<ConstraintViolation<ExtensionCreateRequestDto>> violations = validator.validate(extensionCreateRequestDto);
        assertFalse(violations.isEmpty());
    }
}
