package com.isep.hexchangemanager.model.viewmodel;

import com.isep.hexchangemanager.model.House;
import lombok.Data;

@Data
public class AdvertViewModel {
    private Long houseId;
    private String houseName;
    private String houseType;
    private String houseDescription;
    private String houseImage;
    private Integer houseImageId;
}
