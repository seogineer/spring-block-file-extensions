package com.seogineer.springblockfileextensions.service;

import com.seogineer.springblockfileextensions.common.exception.ExtensionNotFoundException;
import com.seogineer.springblockfileextensions.common.exception.ExtensionNumberExceedException;
import com.seogineer.springblockfileextensions.domain.extension.Extension;
import com.seogineer.springblockfileextensions.domain.extension.ExtensionRepository;
import com.seogineer.springblockfileextensions.dto.extension.ExtensionCreateRequestDto;
import com.seogineer.springblockfileextensions.dto.extension.ExtensionResponseDto;
import com.seogineer.springblockfileextensions.dto.extension.ExtensionUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExtensionService {
    private final int EXT_MAX = 200;
    private ExtensionRepository extensionRepository;

    @Transactional(readOnly = true)
    public List<ExtensionResponseDto> findAll(){
        return extensionRepository.findAll().stream()
                .map(ExtensionResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long create(ExtensionCreateRequestDto dto){
        if(extensionRepository.findByUseYnTrue().size() == EXT_MAX){
            throw new ExtensionNumberExceedException();
        }

        Extension extension = extensionRepository.findByName(dto.getName());

        if(extension == null){
            return extensionRepository.save(dto.toEntity()).getId();
        } else {
            extension.update(dto.getName(), true);
            return extension.getId();
        }
    }

    @Transactional
    public Long update(ExtensionUpdateRequestDto dto){
        Extension extension = extensionRepository.findByName(dto.getName());
        if(extension == null){
            throw new ExtensionNotFoundException();
        }

        extension.update(dto.getName(), dto.getUseYn());
        return extension.getId();
    }

    @Transactional(readOnly = true)
    public List<ExtensionResponseDto> findByUseYnTrue() {
        return extensionRepository.findByUseYnTrue().stream()
                .map(ExtensionResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long changeFixedYn(String name){
        Extension extension = extensionRepository.findByName(name);
        if(extension == null){
            throw new ExtensionNotFoundException();
        }

        extension.changeFixedYn();
        return extension.getId();
    }
}
