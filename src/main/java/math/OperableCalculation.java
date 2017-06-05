package math;

import modelo.Enterprise;
import modelo.Indicator;

public class OperableCalculation implements Operable{
	private String name;
	
	public OperableCalculation(String name){
		this.name = name;
	}
	
	public double reduce(Enterprise enterprise, int year){
		return enterprise.getCalculationOnYear(name, year).getValue();
	}
	
	public String toString(){
		return "#" + name;
	}
	
	public Boolean includes(Indicator indicator){
		return false;
	}
}
