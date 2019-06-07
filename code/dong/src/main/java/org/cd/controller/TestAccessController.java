package org.cd.controller;

import org.cd.config.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: TestAccessController
 * @description:
 * @author: Danny Chen
 * @create: 2019-05-12 18:42
 */
@RestController
@RequestMapping(value = "/test")
public class TestAccessController {

    @Autowired
    private UserAccess userAccess;

    @PreAuthorize("@userAccess.hasRole('Read')")
    @RequestMapping(value = "/readaccess", method = RequestMethod.POST)
    public String testRead() {
        userAccess.hasRole("Read");
        System.out.println("Comming in read access");
        return " ---- read access";
    }

    @PreAuthorize("@userAccess.hasRole('Write')")
    @RequestMapping(value = "/writeaccess", method = RequestMethod.POST)
    public String testWrite() {
        System.out.println("Comming in write access");
        return " ---- write access";
    }

    @RequestMapping(value = "/useraccess", method = RequestMethod.GET)
    public String testUserAccess() {
        System.out.println("Comming in user access");
        return " ---- user access";
    }

    @RequestMapping(value = "/adminaccess", method = RequestMethod.GET)
    public String testAdminAccess() {
        System.out.println("Comming in admin access");
        return " ---- admin access";
    }


}