package businessLayer;

import java.io.Serializable;

public interface MenuItem extends Serializable{
	public double computePrice();
	public boolean equals(Object o);
	public String getName();

}
