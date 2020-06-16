package com.jell.learning.ibeer.domain.beer;

import com.jell.learning.ibeer.domain.common.NameDuplicatedException;
import com.jell.learning.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.jell.learning.ibeer.interfaces.incomming.beer.mapper.BeerMapper;
import com.jell.learning.ibeer.interfaces.incomming.beer.response.BeerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository repository;
    @Mock
    private BeerMapper mapper;

    @InjectMocks
    private BeerService service;

    @Test
    void shouldSuccessfullyCreateABeer_whenCreateIsCalled() {
        final var beer = Beer.builder().name("Brahma").build();
        final var dto = new BeerDTO(null, "Brahma", null, null, null, null, null);
        final var beerResponse = new BeerResponse("Brahma", null, null, null, null, null);

        when(repository.findByName(beer.getName())).thenReturn(Optional.empty());
        when(mapper.toEntity(dto)).thenReturn(beer);
        when(repository.save(beer)).thenReturn(beer);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.create(dto);

        assertSame(response, beerResponse);
        verify(repository).findByName(beer.getName());
        verify(mapper).toEntity(dto);
        verify(repository).save(beer);
        verify(mapper).toResponse(beer);
    }

    @Test
    void shouldThrowsBeerNameDuplicatedException_whenCreateIsCalled() {
        final var beer = Beer.builder().name("Brahma").build();
        final var dto = new BeerDTO(null, "Brahma", null, null, null, null, null);
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var beerCaptor = ArgumentCaptor.forClass(Beer.class);

        when(repository.findByName(anyString())).thenReturn(Optional.of(beer));
        when(mapper.toDTO(any(Beer.class))).thenReturn(dto);

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.create(dto)
        );

        assertThat(expectedException, instanceOf(NameDuplicatedException.class));
        assertTrue(expectedException.getMessage().contains("Name Duplicated!"));
        verify(repository).findByName(beerNameCaptor.capture());
        verify(mapper).toDTO(beerCaptor.capture());
        assertSame(beer, beerCaptor.getValue());
        assertSame(dto.name(), beerNameCaptor.getValue());
    }

    @Test
    public void shouldReturnAllBeersSuccessfully_whenGetAllIsCalled() {
        final var beer = Beer.builder().build();
        final var beerResponse = new BeerResponse( "Brahma", null, null, null, null, null);
        final var beers = new PageImpl<>(singletonList(beer));
        final var pageRequest = PageRequest.of(1, 1);

        when(repository.findAll(pageRequest)).thenReturn(beers);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.getAll(pageRequest);

        assertThat(response.getContent(), hasItem(beerResponse));
        verify(repository).findAll(pageRequest);
        verify(mapper).toDTO(beer);
    }

    @Test
    public void shouldReturnABeerSuccessfully_whenGetByIdIsCalled() {
        final var id = 10L;
        final var beer = Beer.builder().build();
        final var beerResponse = new BeerResponse( "Brahma", null, null, null, null, null);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.of(beer));
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.getById(id);

        assertSame(response, beerResponse);
        verify(repository).findById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
        verify(mapper).toDTO(beer);
    }


    @Test
    void shouldThrowsEntityNotFoundException_whenGetByIdIsCalled() {
        final var id = 10L;
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        final var actualException = assertThrows(
                EntityNotFoundException.class,
                () -> service.getById(id)
        );

        assertThat(actualException, instanceOf(EntityNotFoundException.class));
        assertTrue(actualException.getMessage().contains("Entity not found."));
        verify(repository).findById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }

    @Test
    void shouldSuccessfullyUpdateABeer_whenUpdateIsCalled() {
        final var beer = Beer.builder().id(1L).name("Heineken").build();
        final var dto = new BeerDTO(1L, "Heineken", null, null, null, null, null);
        final var beerResponse = new BeerResponse("Heineken", null, null, null, null, null);
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.empty());
        when(repository.findByName(anyString())).thenReturn(Optional.empty());
        when(mapper.toEntity(dto)).thenReturn(beer);
        when(repository.save(beer)).thenReturn(beer);
        when(mapper.toResponse(beer)).thenReturn(beerResponse);

        final var response = service.update(dto);

        assertSame(response, beerResponse);
        verify(repository).findByNameAndIdNot(beerNameCaptor.capture(), idCaptor.capture());
        verify(repository).findByName(beerNameCaptor.capture());
        assertSame(dto.name(), beerNameCaptor.getValue());
        assertSame(dto.id(), idCaptor.getValue());
        verify(mapper).toEntity(dto);
        verify(repository).save(beer);
        verify(mapper).toResponse(beer);
    }

    @Test
    void shouldThrowsBeerNameDuplicatedException_whenUpdateIsCalled() {
        final var beer = Beer.builder().id(1L).name("Heineken").build();
        final var dto = new BeerDTO(1L, "Heineken", null, null, null, null, null);
        final var beerNameCaptor = ArgumentCaptor.forClass(String.class);
        final var idCaptor = ArgumentCaptor.forClass(Long.class);
        final var beerCaptor = ArgumentCaptor.forClass(Beer.class);

        when(repository.findByNameAndIdNot(anyString(), anyLong())).thenReturn(Optional.of(beer));
        when(mapper.toDTO(any(Beer.class))).thenReturn(dto);

        final var expectedException = assertThrows(
                NameDuplicatedException.class,
                () -> service.update(dto)
        );
        assertThat(expectedException, instanceOf(NameDuplicatedException.class));
        assertTrue(expectedException.getMessage().contains("Name Duplicated!"));
        verify(repository).findByNameAndIdNot(beerNameCaptor.capture(), idCaptor.capture());
        verify(mapper).toDTO(beerCaptor.capture());
        assertSame(dto.name(), beerNameCaptor.getValue());
        assertSame(dto.id(), idCaptor.getValue());
        assertSame(beer, beerCaptor.getValue());
    }

    @Test
    void shouldSuccessfullyDelete_whenDeleteByIdIsCalled() {
        final var id = 10L;
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(id);

        verify(repository).deleteById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }
}
