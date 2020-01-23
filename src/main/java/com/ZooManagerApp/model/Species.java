package com.ZooManagerApp.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Species")
@Getter
@Setter
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String speciesName;
    @Column
    private String description;
    @Column
    private String zookeeper;
    @Column
    private int number;
    @Column
    private Long divisionId;


}
