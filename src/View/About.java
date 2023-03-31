package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class About {
	private JFrame frameEror;
	public boolean ktName(boolean kt, String a)
	{
		for(int i=0; i< a.length();i++)
		{
			if(a.charAt(i)>=65 && a.charAt(i)<=90 || a.charAt(i)>=97 && a.charAt(i)<=122 || a.charAt(i)!=160)
			{
				
			}	
			else
			{
				kt=false;
				return kt;
			}
		}
		return kt;
	}
	public boolean ktAge(boolean kt,String a)
	{
		try {
			int num = Integer.parseInt(a);
		}catch (Exception e2) {
			kt=false;
			return kt;
		}
		if(Integer.parseInt(a)<1 || Integer.parseInt(a)>100)
		{
			kt=false;
			return kt;
		}
		return kt;
	}
	public void showTitle(String a,boolean x)
	{
		if(x==true)
		{
			frameEror = new JFrame();
			JOptionPane.showMessageDialog(frameEror,a,"Error Title",JOptionPane.ERROR_MESSAGE);			
		}
		else
		{
			frameEror = new JFrame();
			JOptionPane.showMessageDialog(frameEror,a,"Title",JOptionPane.INFORMATION_MESSAGE);			
		}
	}
}
