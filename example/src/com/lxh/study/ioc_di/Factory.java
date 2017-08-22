package com.lxh.study.ioc_di;

import java.io.File;
import java.lang.reflect.Constructor;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Factory {
	
	public static Dog getDog(){
		//创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		try{
			//通过reader对象的read方法加载ioc.xml文件，获取document对象
			Document document = reader.read(new File("xml/ioc.xml"));
			//通过document对象获取根节点beans
			Element beans = document.getRootElement();
			//根据标签名获取子标签
			Element bean = beans.element("bean");
			//根据属性名称获取该结点的属性值
			String classValue = bean.attributeValue("class");
			//通过Class.forName(path)获取类
			Class<?> dogClass = Class.forName(classValue);
			//获取构造
			Constructor<?> con = dogClass.getConstructor(new Class[]{});
			//通过构造创建对象
			Dog dog = (Dog)con.newInstance(new Object[]{});
			//返回对象
			return dog;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
