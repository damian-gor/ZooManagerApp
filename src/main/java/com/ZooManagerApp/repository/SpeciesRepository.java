package com.ZooManagerApp.repository;

import com.ZooManagerApp.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {

}
