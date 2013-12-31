import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
/*
 * SplashScreen is a JFrame that shows up before a program starts. Although the spash screen says "Loading.." there really isn't any loading
 * going on. Rather the splash screen is programmed to show up for 5 seconds, regardless after which the program actually begins to load. This 
 * is therefore nothing more than eye candy. 
 */
public class SplashScreen extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image splash;
	public SplashScreen(){
		initImages();
	}
	public void initImages(){
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream input = cl.getResourceAsStream("BenfordsLawSplash2.jpg");
		try {
			splash = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		if(splash != null){
			g2.drawImage(splash,0,0,null);
		}
	}
	
	public void excecute(){
		setSize(585,379);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int widthWindow = getWidth();
		int heightWindow = getHeight();
		int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
		int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.

		setBounds(X,Y , widthWindow,heightWindow);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(false);
	}
	public static void main(String[] args){
		SplashScreen s = new SplashScreen();
		s.excecute();
	}
	
}