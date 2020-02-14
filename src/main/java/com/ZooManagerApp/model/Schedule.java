package com.ZooManagerApp.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "3rd lvl data - obligations of employees related to a given group of animals.")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique identification number of the schedule's item.")
    private Long id;

    @ApiModelProperty(notes = "Date of cleaning animals, cages or enclosures.")
    private Date cleaningDate;

    @ApiModelProperty(notes = "Date of feeding animals.")
    private Date feedingDate;

    @ApiModelProperty(notes = "Date of animal performance.")
    private Date performanceDate;

    @ApiModelProperty(notes = "Details of the schedule's item.")
    private String details;

    @ApiModelProperty(notes = "The unique identification number of the species.")
    private Long speciesId;

    @CreationTimestamp
    @ApiModelProperty(notes = "Date of entry in the schedule.")
    private Date createdAt;

    @UpdateTimestamp
    @ApiModelProperty(notes = "Date the entry was updated in the schedule.")
    private Date updatedAt;
}
