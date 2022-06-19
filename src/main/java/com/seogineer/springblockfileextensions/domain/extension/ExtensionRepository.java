package com.seogineer.springblockfileextensions.domain.extension;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Extension findByName(String name);
    List<Extension> findByUseYnTrue();
}
