package com.jell.learning.ibeer.interfaces.incomming.manufacturer.mapper;

import com.jell.learning.ibeer.domain.manufacturer.Manufacturer;
import com.jell.learning.ibeer.infrastructure.mapstruct.BaseEntityMapper;
import com.jell.learning.ibeer.infrastructure.mapstruct.BaseMapperConfig;
import com.jell.learning.ibeer.infrastructure.mapstruct.BaseResponseMapper;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.response.ManufacturerResponse;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ManufacturerMapper extends BaseEntityMapper<ManufacturerDTO, Manufacturer>,
                                            BaseResponseMapper<ManufacturerResponse, Manufacturer> {}
