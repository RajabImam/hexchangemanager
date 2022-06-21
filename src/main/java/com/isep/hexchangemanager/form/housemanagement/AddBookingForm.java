package com.isep.hexchangemanager.form.housemanagement;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddBookingForm {
    @NotBlank(groups = ValidGroup1.class )
    private String houseId;

    @NotBlank (groups = ValidGroup1.class )
    public String availabilityId;
}
