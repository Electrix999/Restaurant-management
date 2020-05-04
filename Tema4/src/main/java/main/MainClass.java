package main;

import Control.ControlAdmin;
import Control.ControlWaiter;
import businessLayer.Restaurant;
import view.ViewAdmin;
import view.ViewChef;
import view.ViewWaiter;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length!=1)
		{
			System.out.println("Ai uitat sa adaugi argumentul!");
		}
		else
		{
			Restaurant rest=new Restaurant(args[0]);
			
			ViewAdmin admin=new ViewAdmin();
			ControlAdmin control=new ControlAdmin(rest,admin);
			
		    ViewWaiter v=new ViewWaiter(rest.getMenu());
			ControlWaiter waiter=new ControlWaiter(rest,v);
			
			ViewChef chef=new ViewChef();
			
			
			rest.addObserver(chef);

		}
		
	}

}
