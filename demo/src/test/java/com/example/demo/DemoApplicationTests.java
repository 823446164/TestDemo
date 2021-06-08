package com.example.demo;

import com.example.demo.entity.Element;
import com.example.demo.utils.ElementList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	ElementList elementList;

	@Test
	void contextLoads() {
		String path = "C:\\Users\\82344\\Desktop\\新建文本文档.txt";
		//解析文件并返回所愿员工信息
		List<Element> elementList = this.elementList.getElementList(path);
		//返回需要过生日的员工
		List<Element> elementsFlag = this.elementList.getElementsFlag(elementList);
		for(Element element:elementsFlag){
			this.elementList.sendBlessing(element);
		}
	}

}
