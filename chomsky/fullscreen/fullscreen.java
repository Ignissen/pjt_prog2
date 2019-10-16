import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class fullscreen extends JFrame implements KeyListener{
	public MyPanel p;
	boolean isRunning;
	public fullscreen()
	{
		super("FullScreen Java App");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int w = (int) width;
		int h = (int) height;
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(w, h);
		p = new MyPanel(w,h);
		add(p);
		this.addKeyListener(this);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//setUndecorated(true);
		
		isRunning = true;
		
		//setUndecorated(true);
        setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyChar() == 'q')
		{
			//setEnabled(false);
			isRunning = false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public static void main(String[] args) {
		
		java.awt.GraphicsEnvironment graphicsEnvironment = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		fullscreen f = new fullscreen();
		graphicsDevice.setFullScreenWindow(f);
		//device.setFullScreenWindow(f);
		//f.setUndecorated(false);
		while(f.isRunning)
		{
			f.p.update(f.p.getGraphics());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		f.dispose();
	}
	

}
