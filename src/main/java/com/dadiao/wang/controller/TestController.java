package com.dadiao.wang.controller;

import com.dadiao.wang.model.Account;
import com.dadiao.wang.service.ITestService;
import com.dadiao.wang.service.ITestXmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhong on 2016/11/10.
 */
@RestController
@Controller
public class TestController {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestController.class);
    @Autowired
    private ITestService testService;

    @Autowired
    private ITestXmlService testXmlService;

    private String name ;
    @ResponseBody
    @RequestMapping("/test")
    public List<Account> test(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = this.testService.findAccountsById(3);
        logger.info(accountList);
        return accountList;
    }
    @ResponseBody
    @RequestMapping("/testXml")
    public List<Account> testXml(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = this.testXmlService.findAccountsById(3);
        logger.info(accountList);
        return accountList;
    }

    @ResponseBody
    @RequestMapping("/testField")
    public String testField( @RequestParam("name")String name) throws InterruptedException {
        this.name = name;
        System.out.println("--------------"+this.name);
        Thread.sleep(3000L);
        System.out.println("++++++++++++++"+this.name);
        return this.name;
    }
    @ResponseBody
    @RequestMapping("/testField2")
    public String  testField2( @RequestParam("name")String name) {
        this.name = name;
        System.out.println("--------------2"+this.name);
        System.out.println("++++++++++++++2"+this.name);
        return this.name;
    }

}
