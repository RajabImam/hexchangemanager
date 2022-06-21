/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isep.hexchangemanager.util;

import lombok.Data;

/**
 *
 * @author RAJAB IMAM
 */
@Data
public class Message {
    private String content;
    private String type;

    public Message(String content, String type) {
        this.content = content;
        this.type = type;
    }
    
    
    
}
