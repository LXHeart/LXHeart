package com.lxh.thinkinjava.interfaces.interfaceprocessor;

import com.lxh.thinkinjava.interfaces.filters.BandPass;
import com.lxh.thinkinjava.interfaces.filters.Filter;
import com.lxh.thinkinjava.interfaces.filters.HighPass;
import com.lxh.thinkinjava.interfaces.filters.LowPass;
import com.lxh.thinkinjava.interfaces.filters.Waveform;

//适配器
class FilterAdapter implements Processor{
	Filter filter;
	public FilterAdapter(Filter filter){
		this.filter = filter;
	}
	public String name(){
		return filter.name();
	}
	public Waveform process(Object input){
		return filter.process((Waveform)input);
	}
}


public class FilterProcessor {
	public static void main(String[] args) {
		Waveform w = new Waveform();
		Apply.process(new FilterAdapter(new LowPass(1.0)), w);
		Apply.process(new FilterAdapter(new HighPass(2.0)), w);
		Apply.process(new FilterAdapter(new BandPass(3.0, 4.0)), w);
		Apply.process(new Upcase(), w.toString());
	}
	
}
