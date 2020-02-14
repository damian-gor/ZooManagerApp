package com.ZooManagerApp.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Species")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "2nd lvl data - all animal species living in the zoo, grouped in divisions.")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique identification number of the species.")
    private Long id;

    @Column
    @ApiModelProperty(notes = "Name of the species.")
    private String speciesName;

    @Column
    @ApiModelProperty(notes = "Description of the species.")
    private String description;

    @Column
    @ApiModelProperty(notes = "Employee responsible for a given specious.")
    private String zookeeper;

    @Column
    @ApiModelProperty(notes = "Number of animals of the species living in the zoo.")
    private int number;

    @Column
    @ApiModelProperty(notes = "The unique identification number of the division.")
    private Long divisionId;

}
