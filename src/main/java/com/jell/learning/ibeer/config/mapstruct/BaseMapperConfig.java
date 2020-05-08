package com.jell.learning.ibeer.config.mapstruct;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@MapperConfig(
        componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        nullValuePropertyMappingStrategy = IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface BaseMapperConfig {
}
