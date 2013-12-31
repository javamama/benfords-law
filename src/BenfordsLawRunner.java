import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * BenfordsLawRunner serves as a graphics runner class that can help visualize Benford's Law. This program can allow you to view and test the theory of Benford's
 * Law using random numbers, random numbers with random ranges, or your own data set. 
 */
public class BenfordsLawRunner extends JComponent{
	
	double[] perc = new double[9];
	
	JPanel buttonPanel = new JPanel();
	
	JMenuBar mb = new JMenuBar();
	
	int numRand = 100000;
	
	int numRandRange = 9999999;
	
	JButton rand = new JButton("Random Numbers");
	
	JButton randRange = new JButton("Random Numbers with Random Ranges");
	
	Random numGen = new Random();
	
	JTextField tf = new JTextField("Enter Own Sample",17);
	
	public BenfordsLawRunner(){
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		int barWidth = 50;
		int barSpace = 25;
		int graphHeight = 600;
		int horizGap = 50;
		int bottomGap = 30;
		
		g2.setColor(Color.GRAY);
		g2.drawRect(0,0,getWidth(),getHeight());
		g2.fillRect(0,0,getWidth(),getHeight());
		// draw rectangles
		for(int i = 0; i < perc.length; i++){
			
			int w = horizGap + barWidth*i;
			int h = (int) (perc[i] * graphHeight);
			g2.setColor(Color.cyan);	
			g2.draw(new Rectangle2D.Double(barSpace*i + w,getHeight() - h - bottomGap,barWidth,h));
			g2.fill(new Rectangle2D.Double(barSpace*i + w,getHeight() - h - bottomGap,barWidth,h));
		}
		g2.setColor(Color.WHITE);
		// draw axis
		// x - axis
		int w1 = 0;
		int h1 = getHeight() - bottomGap;
		int w2 = horizGap + perc.length*(barWidth + barSpace) + 50;
		int h2 = getHeight() - bottomGap;
		
		g2.drawLine(w1,h1,w2,h2);
		
		// y - axis
		int w3 = horizGap;
		int h3 = getHeight();
		int w4 = horizGap;
		int h4 = getHeight() - bottomGap - graphHeight - 50;
		
		g2.drawLine(w3,h3,w4,h4);
		
		// add labels
		// x - axis labels (numbers 1-9)
		for(int i = 0; i < perc.length; i++){
			
			int w = horizGap + barWidth*i;
			int h = (int) (perc[i] * graphHeight);
			g2.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
			g2.drawString("      " + (i+1), barSpace*i + w, getHeight() - bottomGap +15);
			
		}
		// add actual percentages
		for(int i = 0; i < perc.length; i++){
			
			int w = horizGap + barWidth*i;
			int h = (int) (perc[i] * graphHeight);
			double num = perc[i]*100;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);            
			nf.setGroupingUsed(false);
			
			g2.drawString("  " + nf.format(num) + "%", barSpace*i + w, getHeight() - h - bottomGap);
			
		}
	}
	
	public void parseString(String s){
		double[] percCopy = new double[9];
		StringTokenizer stx = new StringTokenizer(s," ");
		int size = 0;
		while(stx.hasMoreTokens()){
			String j = stx.nextToken();
			Double f = new Double(j);
			Integer first = new Integer((f.toString()).charAt(0) + "");
			percCopy[first.intValue()-1]++;
			size++;
		}
		for(int i = 0; i < percCopy.length; i++){
			
			percCopy[i] = percCopy[i]/size;
			
		}
		
		perc = percCopy;
		
	}
	
	public void init(){
		setLayout(new BorderLayout());
		tf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = tf.getText();
				try {
					parseString(text);
				} catch (Exception e) {
					tf.setText("Positive numbers only, please!");
				}
				repaint();
			}
			
			
		});
		randRange.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	int [] val = new int[numRand];
                for(int i = 0; i < numRand; i++){
                	
                	val[i] = numGen.nextInt(numGen.nextInt(numGen.nextInt(9999999)+1)+ 1) + 1;;
                	
                }
        		for(int a: val){
        			String s = (new Integer(a)).toString();
        			String first= s.charAt(0) + "";
        			int f = (new Integer(first)).intValue();
        			perc[f-1]++;	
        		}
        		for(int i = 0; i < perc.length; i++){
        			perc[i] = perc[i]/val.length;
        		}
        		repaint();
            }
        });
        //Add action listener to button
        rand.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	int [] val = new int[numRand];
                for(int i = 0; i < numRand; i++){
                	
                	val[i] = numGen.nextInt(1000000000) + 1;
                	
                }
        		for(int a: val){
        			String s = (new Integer(a)).toString();
        			String first= s.charAt(0) + "";
        			int f = (new Integer(first)).intValue();
        			perc[f-1]++;	
        		}
        		for(int i = 0; i < perc.length; i++){
        			perc[i] = perc[i]/val.length;
        		}
        		repaint();
            }
        });
        rand.setVisible(true);
        buttonPanel.add(rand);
        buttonPanel.add(randRange);
        buttonPanel.add(tf);
        this.add(buttonPanel, BorderLayout.NORTH);
        repaint();
		
	}
	
	public static void main(String[] args){
		SplashScreen s = new SplashScreen();
		s.excecute();
		BenfordsLawRunner d = new BenfordsLawRunner();
		d.init();
		JFrame f = new JFrame("Benford's Law");
		f.add(d);
		f.setVisible(true);
		f.setSize(770,929);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	

}
