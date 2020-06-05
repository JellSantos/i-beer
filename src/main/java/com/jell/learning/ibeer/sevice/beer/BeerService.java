package com.jell.learning.ibeer.sevice.beer;

import com.jell.learning.ibeer.repository.BeerRepository;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.beer.mapper.BeerMapper;
import com.jell.learning.ibeer.sevice.beer.validator.BeerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository repository;
    private final BeerMapper mapper;

    @Transactional
    public BeerDTO create(BeerDTO dto) {
        applyValidations(dto);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    private void applyValidations(BeerDTO dto) {
        final BeerDTO duplicatedBeer = repository.findByName(dto.getName())
                .map(mapper::toDTO)
                .orElse(null);
        new BeerValidator()
                .accept(duplicatedBeer);
    }

    @Transactional(readOnly = true)
    public Page<BeerDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public BeerDTO getById(long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> {
                    final var e = new EntityNotFoundException("Entity not found.");
                    log.error("m=getById, status=error, message={}", e.getMessage(), e);
                    return e;
                });
    }

    @Transactional
    public BeerDTO update(BeerDTO beerDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(beerDTO)));
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
