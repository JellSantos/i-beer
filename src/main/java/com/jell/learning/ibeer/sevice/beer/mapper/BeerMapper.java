package com.jell.learning.ibeer.sevice.beer.mapper;

import com.jell.learning.ibeer.config.mapstruct.BaseEntityMapper;
import com.jell.learning.ibeer.config.mapstruct.BaseMapperConfig;
import com.jell.learning.ibeer.domain.beer.Beer;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.manufacturer.mapper.ManufacturerMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class, uses = ManufacturerMapper.class)
public interface BeerMapper extends BaseEntityMapper<BeerDTO, Beer> {
}
