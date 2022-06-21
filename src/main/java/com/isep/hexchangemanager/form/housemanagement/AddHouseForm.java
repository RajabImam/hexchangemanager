package com.isep.hexchangemanager.form.housemanagement;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddHouseForm {
    @NotBlank(groups = ValidGroup1.class )
    private String name ;

    @NotBlank (groups = ValidGroup1.class )
    private String address ;

    @NotBlank (groups = ValidGroup1.class )
    private String postalcode ;

    @NotBlank (groups = ValidGroup1.class )
    private String city ;

    @NotBlank (groups = ValidGroup1.class )
    private String country ;

    @NotBlank (groups = ValidGroup1.class )
    private String type ;

    @NotBlank (groups = ValidGroup1.class )
    private String description ;
}
