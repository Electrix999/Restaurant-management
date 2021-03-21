package businessLayer;

import java.util.*;

public class CompositeProduct implements MenuItem{
	
	private List<MenuItem> itemList=new ArrayList<MenuItem>();
	private String name;
	

	public CompositeProduct(String name) {
		super();
		this.name=name;
	}

	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		String description="";
		for(MenuItem x: itemList)
		{
			description+=x.getName()+",";
			
		}
		return description;
	}
	
	
	public double computePrice() {
		// TODO Auto-generated method stub
		double p=0.0;
		for(MenuItem x: itemList)
		{
			p+=x.computePrice();
		}
		return p;
		
	}
	
	
	
	
	public List<MenuItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MenuItem> itemList) {
		this.itemList = itemList;
	}

	public void addProduct(MenuItem m)
	{
		
		if(!itemList.contains(m))
		{
			itemList.add(m);
		}
	}
	
	public boolean removeProduct(MenuItem m)
	{
		if(itemList.contains(m))
		{
			System.out.println("Produsul "+m.getName()+" a fost sters cu succes!");
			itemList.remove(m);
			return true;
		}
		return false;
	}
	
	public boolean equals(Object o)
	{
		if(this==o)
		{
			return true;
		}
		if(o==null)
		{
			return false;
		}
		if(this.getClass()!=o.getClass())
		{
			return false;
		}
		CompositeProduct cp=(CompositeProduct)o;
		return name.equals(cp.name);
	}
	
	public String toString()
	{
		return "Name:"+name+"\nPrice:"+computePrice();
	}
	
}
