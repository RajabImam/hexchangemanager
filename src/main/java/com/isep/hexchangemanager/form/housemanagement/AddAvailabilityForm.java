package com.isep.hexchangemanager.form.housemanagement;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class AddAvailabilityForm {
    private String id ;

    private Long houseId ;

    @DateTimeFormat (pattern = "dd/MM/yyyy" )
    private Date startDate ;

    @DateTimeFormat (pattern = "dd/MM/yyyy" )
    private Date endDate ;
}
