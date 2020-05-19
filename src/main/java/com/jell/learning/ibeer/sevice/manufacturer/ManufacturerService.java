package com.jell.learning.ibeer.sevice.manufacturer;

import com.jell.learning.ibeer.repository.ManufacturerRepository;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.manufacturer.dto.ManufacturerDTO;
import com.jell.learning.ibeer.sevice.manufacturer.mapper.ManufacturerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManufacturerService {

    private final ManufacturerRepository repository;
    private final ManufacturerMapper mapper;

    @Transactional
    public ManufacturerDTO create(ManufacturerDTO manufacturerDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(manufacturerDTO)));
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
    public ManufacturerDTO update(ManufacturerDTO manufacturerDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(manufacturerDTO)));
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
