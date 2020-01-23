package com.ZooManagerApp.service;

import com.ZooManagerApp.exception.ResourceNotFoundException;
import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DivisionServiceImpl implements DivisionService{

    @Autowired
    DivisionRepository divisionRepository;

    @Override
    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    @Override
    public Division getDivision(long divisionId) {
        Optional<Division> divisionDb = divisionRepository.findById(divisionId);

        if (divisionDb.isPresent())
        {
            return divisionDb.get();
        }
        else{
            throw new ResourceNotFoundException ("Have not found division with id : " + divisionId);
        }
    }

    @Override
    public Division updateDivision(Division division) {
        Optional<Division> divisionDb = divisionRepository.findById(division.getId());
        if (divisionDb.isPresent())
        {
            Division updatedDivision = divisionDb.get();
            updatedDivision.setId(division.getId());
            updatedDivision.setDivisionName(division.getDivisionName());
            updatedDivision.setDescription(division.getDescription());
            updatedDivision.setSupervisor(division.getSupervisor());
            divisionRepository.save(updatedDivision);
            return updatedDivision;
        }
        else{
            throw new ResourceNotFoundException ("Have not found division with id : " + division.getId());
        }
    }

    @Override
    public Division addDivision(Division division) {
        divisionRepository.save(division);
        return division;
    }

    @Override
    public void removeDivision(long divisionId) {
        Optional <Division> divisionDb = divisionRepository.findById(divisionId);

        if (divisionDb.isPresent()){
            divisionRepository.deleteById(divisionId);
        }
        else{
            throw new ResourceNotFoundException("Have not found division with id : " + divisionId);
        }
    }
}
