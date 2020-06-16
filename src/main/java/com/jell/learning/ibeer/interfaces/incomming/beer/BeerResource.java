package com.jell.learning.ibeer.interfaces.incomming.beer;

import com.jell.learning.ibeer.domain.beer.BeerService;
import com.jell.learning.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.jell.learning.ibeer.interfaces.incomming.beer.response.BeerResponse;
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
@RequestMapping("/beers")
@ApiModel(value = "Beer Resource", description = "Allows you to perform operations on the beer resource")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerResource {

    private final BeerService service;

    @PostMapping
    @ApiOperation("Creates a new Beer")
    public ResponseEntity<BeerResponse> create(@RequestBody @Valid BeerDTO dto) {
        return ResponseEntity.created(URI.create("/beers")).body((service.create(dto)));
    }

    @GetMapping
    @ApiOperation("Gets all Beers")
    public ResponseEntity<Page<BeerResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @ApiOperation("Gets a Beer by id")
    public ResponseEntity<BeerResponse> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping
    @ApiOperation("Updates a Beer")
    public ResponseEntity<BeerResponse> update(@Valid @RequestBody BeerDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclude a Beer by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }
}