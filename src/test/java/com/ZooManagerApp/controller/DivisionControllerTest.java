package com.ZooManagerApp.controller;

import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.service.DivisionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("DivisionController Tests")
class DivisionControllerTest {

    @InjectMocks
    DivisionController divisionController;

    @Mock
    DivisionService divisionService;

    Division division1;
    Division division2;
    Division updatedDivision;
    Division postedDivision;
    List<Division> divisions;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        division1 = new Division();
        division1.setId(1L);
        division1.setDivisionName("mammals");
        division1.setDescription("Mammals description");
        division1.setSupervisor("Kate B");

        division2 = new Division();
        division2.setId(2L);
        division2.setDivisionName("reptiles");
        division2.setDescription("Reptiles description");
        division2.setSupervisor("Tom H");

        divisions = new ArrayList<>();
        divisions.add(division1);
        divisions.add(division2);

        updatedDivision = new Division();
        updatedDivision.setId(1L);
        updatedDivision.setDescription("Mammals description123");
        updatedDivision.setSupervisor("Peter P");

        postedDivision = new Division();
        postedDivision.setId(3L);
        postedDivision.setDivisionName("birds");
        postedDivision.setDescription("Birds description");
        postedDivision.setSupervisor("John R");
    }

    @Test
    @DisplayName("GET all test")
    void testGetAllDivisions() {
        when(divisionService.getAllDivisions()).thenReturn(divisions);

        ResponseEntity<List<Division>> divisionsResponseEntity = divisionController.getAllDivisions();

        assertNotNull(divisionsResponseEntity);
        assertTrue(divisionsResponseEntity.getStatusCode().is2xxSuccessful());
        assertIterableEquals(divisionsResponseEntity.getBody(), divisions);

        verify(divisionService).getAllDivisions();
    }

    @Test
    @DisplayName("GET one test")
    void testGetDivision() {
        when(divisionService.getDivision(anyLong())).thenReturn(division1);

        ResponseEntity<Division> divisionResponseEntity = divisionController.getDivision(1L);

        assertNotNull(divisionResponseEntity);
        assertEquals(1L, divisionResponseEntity.getBody().getId());
        assertEquals("mammals", divisionResponseEntity.getBody().getDivisionName());
        assertEquals("Mammals description", divisionResponseEntity.getBody().getDescription());
        assertEquals("Kate B", divisionResponseEntity.getBody().getSupervisor());
        assertEquals(200, divisionResponseEntity.getStatusCodeValue());

        verify(divisionService).getDivision(1L);
    }

    @Test
    @DisplayName("POST test")
    void testAddDivision() {
        when(divisionService.addDivision(postedDivision)).thenReturn(postedDivision);

        ResponseEntity<Division> divisionResponseEntity = divisionController.addDivision(postedDivision);

        assertNotNull(divisionResponseEntity);
        assertTrue(divisionResponseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(postedDivision, divisionResponseEntity.getBody());

        verify(divisionService).addDivision(postedDivision);
    }

    @Test
    @DisplayName("PUT test")
    void testUpdateDivision() {
        when(divisionService.updateDivision(updatedDivision)).thenReturn(updatedDivision);

        ResponseEntity<Division> divisionResponseEntity = divisionController.updateDivision(updatedDivision.getId(), updatedDivision);

        assertNotNull(divisionResponseEntity);
        assertEquals(1L, divisionResponseEntity.getBody().getId());
        assertEquals(null, divisionResponseEntity.getBody().getDivisionName());
        assertEquals("Mammals description123", divisionResponseEntity.getBody().getDescription());
        assertEquals("Peter P", divisionResponseEntity.getBody().getSupervisor());
        assertEquals(200, divisionResponseEntity.getStatusCodeValue());

        verify(divisionService).updateDivision(updatedDivision);
    }

    @Test
    @DisplayName("REMOVE test")
    void testDeleteDivision() {

        HttpStatus httpStatus = divisionController.removeDivision(1L);

        assertTrue(httpStatus.is2xxSuccessful());

        verify(divisionService).removeDivision(anyLong());
    }
}