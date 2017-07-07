package modelo.indicator;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.uqbar.commons.utils.Observable;
import exceptions.EmptyFieldException;
import modelo.enterprise.Enterprise;
import modelo.indicator.math.Operable;
import modelo.indicator.parser.IndicatorParser;

@Observable
public class Indicator implements Operable
{
	private String name;
	private String formula;
	private Operable value;

	public Indicator(String name, String formula){
		if(name == null) throw new EmptyFieldException("Nombre");
		if(formula == null) throw new EmptyFieldException("Formula");
		this.name = name;
		this.formula = formula;
		this.value = IndicatorParser.parseIndicator(formula);
	}
	
	public String getName() 
	{
		return name;
	}

	public String getFormula() {
		return formula;
	}
	
	public Operable getValue() {
		return value;
	}

	public BigDecimal reduce(Enterprise enterprise, int year){
		return value.reduce(enterprise, year);
	}
	
	public boolean tryReduce(Enterprise enterprise, int year)
	{
		try
		{
			reduce(enterprise, year);
			return true;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	public String normalize(){
		return value.toString();
	}
	
	@Override
	public String toString(){
		return "@" + name;
	}
	
	public Boolean uses(Indicator indicator){
		return value.includes(indicator);
	}
	
	public Boolean includes(Indicator indicator){
		return this == indicator || value.includes(indicator);
	}
}