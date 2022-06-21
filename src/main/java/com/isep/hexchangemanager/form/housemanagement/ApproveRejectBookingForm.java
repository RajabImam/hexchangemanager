package com.isep.hexchangemanager.form.housemanagement;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ApproveRejectBookingForm {
    @NotBlank(groups = ValidGroup1.class)
    private String bookingId;

    private String AvailabilityId;

    private String HouseId;
}
