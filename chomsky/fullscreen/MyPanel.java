import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	Random r;
	public MyPanel(int w, int h)
	{
		setBackground(Color.BLACK);
		r = new Random();
		this.setSize(w, h);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
		//g.clearRect(0, 0,getWidth(), getHeight());
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.fillOval(r.nextInt(getWidth()), r.nextInt(getHeight()), r.nextInt(400)+100, r.nextInt(400)+100);
	}
}
