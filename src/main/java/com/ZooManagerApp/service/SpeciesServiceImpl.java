package com.ZooManagerApp.service;

import com.ZooManagerApp.exception.ResourceNotFoundException;
import com.ZooManagerApp.model.Species;
import com.ZooManagerApp.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpeciesServiceImpl implements SpeciesService{

    @Autowired
    SpeciesRepository speciesRepository;

    @Override
    public List<Species> getAllSpecies() {
        return speciesRepository.findAll();
    }

    @Override
    public Species getSpecies(long speciesId) {
        Optional<Species> speciesDb = speciesRepository.findById(speciesId);

        if (speciesDb.isPresent())
        {
            return speciesDb.get();
        }
        else{
            throw new ResourceNotFoundException ("Have not found species with id : " + speciesId);
        }
    }

    @Override
    public Species updateSpecies(Species species) {
        Optional<Species> speciesDb = speciesRepository.findById(species.getId());
        if (speciesDb.isPresent())
        {
            Species updatedSpecies = speciesDb.get();
            updatedSpecies.setId(species.getId());
            updatedSpecies.setSpeciesName(species.getSpeciesName());
            updatedSpecies.setDescription(species.getDescription());
            updatedSpecies.setZookeeper(species.getZookeeper());
            updatedSpecies.setNumber(species.getNumber());
            updatedSpecies.setDivisionId(species.getDivisionId());
            speciesRepository.save(updatedSpecies);
            return updatedSpecies;
        }
        else{
            throw new ResourceNotFoundException ("Have not found species with id : " + species.getId());
        }
    }

    @Override
    public Species addSpecies(Species species) {
        speciesRepository.save(species);
        return species;
    }

    @Override
    public void removeSpecies(long speciesId) {
        Optional <Species> speciesDb = speciesRepository.findById(speciesId);

        if (speciesDb.isPresent()){
            speciesRepository.deleteById(speciesId);
        }
        else{
            throw new ResourceNotFoundException("Have not found species with id : " + speciesId);
        }
    }
}
