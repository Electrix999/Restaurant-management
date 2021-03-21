package view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import businessLayer.MenuItem;


@SuppressWarnings("deprecation")
public class ViewChef extends JFrame implements Observer{
	
	JFrame frame;
	JLabel chefLb=new JLabel("TO DO:");
	JTextField chefTF=new JTextField(40);
	
	ArrayList<MenuItem> comand=new ArrayList<MenuItem>();
	
	public ViewChef()
	{
		chefTF.setEditable(false);
		frame=new JFrame("CHEF INTERFACE");
		
		JPanel panel=new JPanel(new FlowLayout());
		panel.add(chefLb);
		panel.add(chefTF);
		
		frame.add(panel);
		frame.setSize(500,100);
		frame.setVisible(true);
		
		
	}


	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
			if(((ArrayList<?>)arg).size()>0)
			{
				comand=(ArrayList<MenuItem>)arg;
				System.out.println(comand.size());
				String afis="";
				for(MenuItem x:comand)
				{
					afis+=x.getName()+", ";
				}
				
				chefTF.setText(afis);
			}
			else
			{
				chefTF.setText("NOTHING NEW");
			}
			
		
		
	}
	

}
