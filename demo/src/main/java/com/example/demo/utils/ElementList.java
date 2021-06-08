package com.example.demo.utils;

import com.example.demo.entity.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ElementList {
    //邮件发送实体类
    @Autowired
    JavaMailSenderImpl javaMailSenderImpl;
    //邮件发送者邮箱
    private  String emailNoe = "823446164@qq.com";

    public List<Element> getElementList(String filepath){
        List<Element> listArray = new ArrayList<>();
        try{
            File file = new File(filepath);
            if(file.isFile()&&file.exists()){
                //创建文件流
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
                BufferedReader br = new BufferedReader(isr);
                String line = null;//读取一行
                while((line = br.readLine())!=null){
                    String[] arr = line.split(",");
                    Element element = new Element();
                    element.setEno(arr[0]);
                    element.setName(arr[1]);
                    element.setEmail(arr[2]);
                    element.setCardId(arr[3]);
                    listArray.add(element);
                }
            }
        }catch (Exception e){
            System.err.println("文件解析错误");
        }
            return listArray;
    }
    /*
    * 给过申请员工发送邮件
    * */
    public void sendBlessing(Element element){

        if(element!=null){
            //创建一个发送邮件实体类
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("生日邮件");
            simpleMailMessage.setText("亲爱的"+element.getName()+"先生！祝您生日快乐，身体健康！");
            simpleMailMessage.setFrom(emailNoe);
            simpleMailMessage.setTo(element.getEmail());
            javaMailSenderImpl.send(simpleMailMessage);
        }
    }
    /*
    * 判断当前日期是否有员工过生日，并返回过生日员工
    * */
    public List<Element> getElementsFlag(List<Element> elements){
        //获取当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String format = dateFormat.format(new Date());
        List<Element> list = new ArrayList<>();//保存需要过生日的员工
        for(Element element : elements){
            String cardId = element.getCardId();
            int i = cardId.indexOf("/");
            //根据日期截取月份和天数
            String date = cardId.substring(i+1);
            if(format.equals(date)){
                list.add(element);
            }
        }
        return list;
    }

}
