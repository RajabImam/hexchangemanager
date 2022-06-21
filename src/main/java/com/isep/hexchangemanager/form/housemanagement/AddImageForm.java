/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.form.housemanagement;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author RAJAB IMAM
 */
@Data
public class AddImageForm {
    private String id ;

    private Long houseId ;

    //@NotBlank (groups = ValidGroup1.class )
    private String main_image ;
}
