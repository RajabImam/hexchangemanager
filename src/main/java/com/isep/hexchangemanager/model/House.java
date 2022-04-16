/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.model;

import java.util.Date;

/**
 *
 * @author RAJAB IMAM
 */
public class House {
    private Long id;
    private String name;
    private String location;
    private String type;
    private String description;
    private Integer status;
    private Date availability_start;
    private Date availability_end;
    private Date created_on;
    
    private User user;
}
