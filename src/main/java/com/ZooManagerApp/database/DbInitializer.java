package com.ZooManagerApp.database;

import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.model.Schedule;
import com.ZooManagerApp.model.Species;
import com.ZooManagerApp.repository.DivisionRepository;
import com.ZooManagerApp.repository.ScheduleRepository;
import com.ZooManagerApp.repository.SpeciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@AllArgsConstructor
public class DbInitializer implements CommandLineRunner {

    private DivisionRepository divisionRepository;
    private SpeciesRepository speciesRepository;
    private ScheduleRepository scheduleRepository;

    @Override
    public void run(String... strings) throws Exception {
        this.divisionRepository.deleteAll();
        this.speciesRepository.deleteAll();
        this.scheduleRepository.deleteAll();

        Division division1 = new Division(1l, "mammals", "Mammals description", "Kate B");
        Division division2 = new Division(2l, "reptiles", "Reptiles description", "Tom H");

        Species species1 = new Species(1l, "elephant", "Elephant's description", "Marry J", 4, 1l);
        Species species2 = new Species(2l, "giraffe", "Giraffe's description", "Andrew G", 7, 1l);
        Species species3 = new Species(3l, "kangaroo", "Kangaroo's description", "Paul K", 11, 1l);
        Species species4 = new Species(4l, "crocodile", "Crocodile's description", "Barry D", 3, 2l);
        Species species5 = new Species(5l, "tortoise", "Tortoise's description", "Mark B", 11, 2l);
        Species species6 = new Species(6l, "lizard", "Lizard's description", "Peter H", 18, 2l);

        Schedule schedule1 = new Schedule(1l, null, new Date(120,1,1), null, "Use more aggressive cleaning supplies", 1l, null, null);
        Schedule schedule2 = new Schedule(2l, new Date(120, 2, 12),null,null, "Remember to add supplements", 1l, null, null);
        Schedule schedule3 = new Schedule(3l, new Date(120, 2, 14),null,null, "Remember to add supplements", 2l, null, null);
        Schedule schedule4 = new Schedule(4l, null,null,new Date(120, 2, 14), "Performance for kids", 3l, null, null);
        Schedule schedule5 = new Schedule(5l, null, new Date(120, 2, 8),null, "Details", 3l, null, null);
        Schedule schedule6 = new Schedule(6l, null, null,new Date(120, 3, 8), "Details", 4l, null, null);
        Schedule schedule7 = new Schedule(7l, null, new Date(120, 2, 8),null, "Details", 4l, null, null);
        Schedule schedule8 = new Schedule(8l, new Date(120, 2, 15), null,null, "Details", 5l, null, null);
        Schedule schedule9 = new Schedule(9l, null, new Date(120, 2, 9),null, "Details", 5l, null, null);
        Schedule schedule10 = new Schedule(10l, null, new Date(120, 2, 10),null, "Details", 6l, null, null);


        this.divisionRepository.save(division1);
        this.divisionRepository.save(division2);
        this.speciesRepository.save(species1);
        this.speciesRepository.save(species2);
        this.speciesRepository.save(species3);
        this.speciesRepository.save(species4);
        this.speciesRepository.save(species5);
        this.speciesRepository.save(species6);
        this.scheduleRepository.save(schedule1);
        this.scheduleRepository.save(schedule2);
        this.scheduleRepository.save(schedule3);
        this.scheduleRepository.save(schedule4);
        this.scheduleRepository.save(schedule5);
        this.scheduleRepository.save(schedule6);
        this.scheduleRepository.save(schedule7);
        this.scheduleRepository.save(schedule8);
        this.scheduleRepository.save(schedule9);
        this.scheduleRepository.save(schedule10);

        System.out.println(" -- Database has been initialized");
    }
}
