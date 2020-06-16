package com.jell.learning.ibeer.interfaces.incomming.beer.mapper;

import com.jell.learning.ibeer.domain.beer.Beer;
import com.jell.learning.ibeer.infrastructure.mapstruct.BaseEntityMapper;
import com.jell.learning.ibeer.infrastructure.mapstruct.BaseMapperConfig;
import com.jell.learning.ibeer.infrastructure.mapstruct.BaseResponseMapper;
import com.jell.learning.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.jell.learning.ibeer.interfaces.incomming.beer.response.BeerResponse;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.mapper.ManufacturerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, uses = ManufacturerMapper.class)
public interface BeerMapper extends BaseEntityMapper<BeerDTO, Beer>, BaseResponseMapper<BeerResponse, Beer> {

    @Mapping(target = "manufacturer", source = "manufacturer.name")
    BeerResponse toResponse(Beer entity);
}
