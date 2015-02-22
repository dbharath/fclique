/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.fclique.controller;

import net.fclique.service.AmazonProductApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author devesh.bharathan
 */
@Controller
@RequestMapping("/amazon")
public class AmazonApiController {
    
    @Autowired
    private AmazonProductApi amazonProductApi;
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search() throws Exception {
        this.amazonProductApi.testItemSearch();
        return null;
        }
    
}
