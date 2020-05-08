package com.jell.learning.ibeer.resource;

import com.jell.learning.ibeer.sevice.beer.BeerService;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import javax.validation.Valid;

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
}
