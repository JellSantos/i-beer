package com.jell.learning.ibeer.sevice.beer;

import com.jell.learning.ibeer.repository.BeerRepository;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.beer.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository repository;
    private final BeerMapper mapper;

    @Transactional
    public BeerDTO create(BeerDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }
}
