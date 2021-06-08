package com.example.demo.controller;

import com.example.demo.entity.Element;
import com.example.demo.utils.ElementList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 此类是一个调用示例，调用方式不限于此；
 */
@Controller
@RequestMapping("send")
public class SendEmailController {

    @Autowired
    private ElementList elements;

    @GetMapping("email")
    public void sendEmail(){
        //文件路径
        String filePath = "";
        List<Element> elementList = elements.getElementList(filePath);
        elementList.forEach(i->{
            elements.sendBlessing(i);
        });
    }
}
