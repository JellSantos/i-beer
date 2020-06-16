package com.jell.learning.ibeer.domain.manufacturer;

import com.jell.learning.ibeer.domain.common.DuplicationValidator;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.mapper.ManufacturerMapper;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.response.ManufacturerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired), access = PRIVATE)
public class ManufacturerService {

    private final ManufacturerRepository repository;
    private final ManufacturerMapper mapper;

    @Transactional
    public ManufacturerResponse create(ManufacturerDTO dto) {
        applyValidations(dto);
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    private void applyValidations(@NonNull ManufacturerDTO dto) {
        final var duplicatedManufacturer = Optional.of(dto)
                .filter(d -> nonNull(d.id()))
                .flatMap(d -> repository.findByNameAndIdNot(d.name(), d.id()).map(mapper::toDTO))
                .orElseGet(() -> repository.findByName(dto.name()).map(mapper::toDTO).orElse(null));

        new DuplicationValidator()
                .accept(duplicatedManufacturer);
    }

    @Transactional(readOnly = true)
    public Page<ManufacturerResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public ManufacturerResponse getById(long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> {
                    final var e = new EntityNotFoundException("Entity not found.");
                    log.error("m=getById, status=error, message={}", e.getMessage(), e);
                    return e;
                });
    }

    @Transactional
    public ManufacturerResponse update(ManufacturerDTO dto) {
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
