package com.isep.hexchangemanager.form.housemanagement;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddServiceForm {
    private String id ;

    private Long houseId ;

    @NotBlank (groups = ValidGroup1.class )
    private String description ;
}
