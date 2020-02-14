package com.ZooManagerApp.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Division")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "1st lvl data - all animals in the zoo are grouped into divisions.")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique identification number of the division.")
    private Long id;

    @Column
    @ApiModelProperty(notes = "Name of the division.")
    private String divisionName;

    @Column
    @ApiModelProperty(notes = "Description of the division.")
    private String description;

    @Column
    @ApiModelProperty(notes = "Employee responsible for a given group of animals, the supervisor of animal keepers.")
    private String supervisor;

}
