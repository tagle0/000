/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tagle
 */
@Controller
public class AccountsApplicationController {

    @RequestMapping(path="/ClientsApplication")
    public String index(){
        return "accounts_application/index";
    }
    
}
