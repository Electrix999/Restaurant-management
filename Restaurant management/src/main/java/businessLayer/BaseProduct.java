package businessLayer;

public class BaseProduct implements MenuItem{
	private String name;
	private double price;
	
	

	public BaseProduct(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public void setPrice(double price) {
		this.price=price;;
	}



	public double computePrice() {
		return price;
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
		BaseProduct bp=(BaseProduct)o;
		return name.equals(bp.name);
	}
	
	

}
