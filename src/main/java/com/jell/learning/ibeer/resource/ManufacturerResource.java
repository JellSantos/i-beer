package com.jell.learning.ibeer.resource;

import com.jell.learning.ibeer.sevice.manufacturer.ManufacturerService;
import com.jell.learning.ibeer.sevice.manufacturer.dto.ManufacturerDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/manufacturers")
@ApiModel(value = "Manufacturer Resource", description = "Provide Manufacturer Operations")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManufacturerResource {

    private final ManufacturerService service;

    @PostMapping
    @ApiOperation("Create a new Manufacturer")
    public ResponseEntity<ManufacturerDTO> create(@RequestBody @Valid ManufacturerDTO manufacturerDTO) {
        return ResponseEntity.created(URI.create("/manufacturers")).body((service.create(manufacturerDTO)));
    }

    @GetMapping
    @ApiOperation("Gets all Manufacturers")
    public ResponseEntity<Page<ManufacturerDTO>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @ApiOperation("Gets a Manufacturer by id")
    public ResponseEntity<ManufacturerDTO> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("Updates a Manufacturer")
    public ResponseEntity<ManufacturerDTO> update(@Valid @RequestBody ManufacturerDTO manufacturerDTO) {
        return ResponseEntity.ok(service.update(manufacturerDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclude a Manufacturer by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }
}
