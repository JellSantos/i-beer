package com.jell.learning.ibeer.domain.manufacturer;

import com.jell.learning.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.mapper.ManufacturerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired), access = PRIVATE)
public class ManufacturerService {

    private final ManufacturerRepository repository;
    private final ManufacturerMapper mapper;

    @Transactional
    public ManufacturerDTO create(ManufacturerDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public Page<ManufacturerDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ManufacturerDTO getById(long id) {
        return mapper.toDTO(repository.getOne(id));
    }

    @Transactional
    public ManufacturerDTO update(ManufacturerDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
