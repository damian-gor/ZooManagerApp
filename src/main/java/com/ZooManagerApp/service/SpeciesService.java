package com.ZooManagerApp.service;

import com.ZooManagerApp.model.Species;

import java.util.List;

public interface SpeciesService {
    List<Species> getAllSpecies();
    Species getSpecies(long speciesId);
    Species updateSpecies(Species species);
    Species addSpecies(Species species);
    void removeSpecies(long speciesId);
}
