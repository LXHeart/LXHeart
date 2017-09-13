package com.lxh.thinkinjava.interfaces.interfaceprocessor;

import com.lxh.thinkinjava.interfaces.newClass.Change;
import com.lxh.thinkinjava.interfaces.newClass.NewClass;


class NewClassAdapter implements Processor{
	NewClass newClass;

	public NewClassAdapter(NewClass newClass){
		this.newClass = newClass;
	}
	
	@Override
	public String name() {
		return newClass.name();
	}

	@Override
	public Object process(Object input) {
		return newClass.process(input);
	}
	
	
}

public class NewClassProcessor {
	public static void main(String[] args) {
		Apply.process(new NewClassAdapter(new Change()), "你好啊");
	}

}
