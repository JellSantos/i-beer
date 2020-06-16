package com.jell.learning.ibeer.resource.manufacturer;

import com.jell.learning.ibeer.domain.manufacturer.ManufacturerService;
import com.jell.learning.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import javax.validation.Valid;

@RestController
@RequestMapping("/manufacturers")
@ApiModel(value = "Manufacturer Resource", description = "Allows you to perform operations on the manufacturer resource")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManufacturerResource {

    private final ManufacturerService service;

    @PostMapping
    @ApiOperation("Creates a new Manufacturer")
    public ResponseEntity<ManufacturerDTO> create(@RequestBody @Valid ManufacturerDTO dto) {
        return ResponseEntity.created(URI.create("/manufactures")).body((service.create(dto)));
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

    @PutMapping
    @ApiOperation("Updates a Manufacturer")
    public ResponseEntity<ManufacturerDTO> update(@Valid @RequestBody ManufacturerDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclude a Manufacturer by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }
}