package com.jell.learning.ibeer.resource;

import com.jell.learning.ibeer.sevice.beer.BeerService;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import io.swagger.annotations.Api;
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
@RequestMapping("/beers")
@Api(value = "Beer Resource", hidden = true)
@ApiModel(value = "Beer Resource", description = "Provide Beer Operations")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerResource {

    private final BeerService service;

    @PostMapping
    @ApiOperation("Perform one Beer")
    public ResponseEntity<BeerDTO> createBeer(@RequestBody @Valid BeerDTO dto) {
        return ResponseEntity.created(URI.create("/beers")).body((service.create(dto)));
    }

    @GetMapping
    @ApiOperation("Gets all Beers")
    public ResponseEntity<Page<BeerDTO>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @ApiOperation("Gets one Beer by id")
    public ResponseEntity<BeerDTO> getOne(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/beer/{id}")
    @ApiOperation("Exclude one Beer by id")
    public ResponseEntity<Void> excludeBeer(@PathVariable long id) {
        service.excludeById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("beer/{id}")
    @ApiOperation("Change Beer")
    public ResponseEntity<BeerDTO> change(@PathVariable long id, @Valid @RequestBody BeerDTO beerDTO) {
        BeerDTO beerOldDto = service.getById(id);
        beerOldDto.setName(beerDTO.getName());
        return ResponseEntity.ok(service.change(beerOldDto));
    }
}