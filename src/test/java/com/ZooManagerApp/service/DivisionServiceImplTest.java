package com.ZooManagerApp.service;

import com.ZooManagerApp.exception.ResourceNotFoundException;
import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.repository.DivisionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("DivisionServiceImplementation Tests")
class DivisionServiceImplTest {

    @InjectMocks
    DivisionServiceImpl divisionService;

    @Mock
    DivisionRepository divisionRepository;

    Division division1;
    Division division2;
    Division updatedDivision;
    Division editedDivision;
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

        postedDivision = new Division();
        postedDivision.setId(3L);
        postedDivision.setDivisionName("birds");
        postedDivision.setDescription("Birds description");
        postedDivision.setSupervisor("John R");

        editedDivision = new Division();
        editedDivision.setId(1L);
        editedDivision.setDescription("Mammals description123");
        editedDivision.setSupervisor("Peter P");
    }


    @Nested
    @DisplayName("GET tests")
    class GetTests {

        @Nested
        @DisplayName("GET one test")
        class GetOneTest {

            @Test
            @DisplayName("Method test")
            void testGetDivision() {

                when(divisionRepository.findById(anyLong())).thenReturn(Optional.of(division1));

                Division testedDivision = divisionService.getDivision(1L);

                assertEquals(1L, testedDivision.getId());
                assertEquals("mammals", testedDivision.getDivisionName());
                assertEquals("Mammals description", testedDivision.getDescription());
                assertEquals("Kate B", testedDivision.getSupervisor());

                verify(divisionRepository).findById(1L);
            }

            @Test
            @DisplayName("Exception test")
            final void testGetDivision_ResourceNotFoundException() {

                when(divisionRepository.findById(anyLong())).thenReturn(Optional.empty());

                assertThrows(ResourceNotFoundException.class,
                        () -> divisionService.getDivision(1L)
                );
            }
        }

        @Nested
        @DisplayName("GET all test")
        class GetAllTest {
            @Test
            @DisplayName("Method test")
            void testGetAllDivisions() {

                when(divisionRepository.findAll()).thenReturn(divisions);
                List<Division> divisionList = divisionService.getAllDivisions();

                assertNotNull(divisionList);
                assertTrue(divisionList.size()==2);
                assertIterableEquals(divisions, divisionList);

                verify(divisionRepository).findAll();
            }
        }
    }

    @Nested
    @DisplayName("POST tests")
    class PostTests {
        @Test
        @DisplayName("Method test")
        void testAddDivision() {
            when(divisionRepository.save(postedDivision)).thenReturn(postedDivision);

            Division addedDivision = divisionService.addDivision(postedDivision);

            assertNotNull(addedDivision);
            assertEquals(postedDivision, addedDivision);
            verify(divisionRepository).save(postedDivision);
        }
    }

    @Nested
    @DisplayName("PUT tests")
    class PutTests {

        @Test
        @DisplayName("Method test")
        void testUpdateDivision() {

            when(divisionRepository.findById(1L)).thenReturn(Optional.of(division1));
            when(divisionRepository.save(updatedDivision)).thenReturn(updatedDivision);

            updatedDivision = divisionService.updateDivision(editedDivision);

            assertNotNull(updatedDivision);
            assertEquals(1L, updatedDivision.getId());
            assertEquals(null, updatedDivision.getDivisionName());
            assertEquals("Mammals description123", updatedDivision.getDescription());
            assertEquals("Peter P", updatedDivision.getSupervisor());

            verify(divisionRepository).findById(1L);
            verify(divisionRepository).save(updatedDivision);
        }

        @Test
        @DisplayName("Exception test")
        final void testUpdateDivision_ResourceNotFoundException() {

            when(divisionRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class,
                    () -> divisionService.updateDivision(editedDivision)
            );

            verify(divisionRepository).findById(editedDivision.getId());
        }
    }

    @Nested
    @DisplayName("REMOVE tests")
    class RemoveTests {
        @Test
        @DisplayName("Method test")
        void testRemoveDivision() {

            when(divisionRepository.findById(anyLong())).thenReturn(Optional.of(division1));

            divisionService.removeDivision(1L);

            assertNotNull(division1);

            verify(divisionRepository).findById(1L);
            verify(divisionRepository).deleteById(1L);
        }

        @Test
        @DisplayName("Exception test")
        final void testRemoveDivision_ResourceNotFoundException() {

            when(divisionRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class,
                    () -> divisionService.removeDivision(1L)
            );

            verify(divisionRepository).findById(1L);
        }
    }
}




















