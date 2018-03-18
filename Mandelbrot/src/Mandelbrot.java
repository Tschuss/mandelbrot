import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mandelbrot extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  final int PREF_W = 800; 
	public  final int PREF_H = 600;
	private static final int PRECISION=255; // max 255
	public  final double ZOOM=150.0;
	
	public double zoom;
	public int cen, ter;
	
	Mandelbrot() throws HeadlessException {
		super();
	}
	
	private void init() {
		
		zoom=ZOOM;
		cen=-2;
		ter=0;
		
		JFrame frame = new JFrame("Mandelbrot");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		this.addMouseListener(new MandelbrotListener());
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Math.round(PREF_W), Math.round(PREF_H));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D) g;
		g2.clearRect(0, 0, this.getWidth(),this.getHeight());

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double zx, zy, aux, cx, cy;
		int p;
		
		
		//colors
		int c;
		Color[] colors = new Color[PRECISION];
		for (int i=0;i<PRECISION;i++){
			c = 255-i*(255/PRECISION);
			colors[i]= new Color(c,c,c);
		}
			
		
		// explores
		for (int x=0;x<PREF_W;x++) {
			for (int y=0;y<PREF_H;y++) { 
				zx=0.0;
				zy=0.0;
				
				cx=(double)(x+cen*zoom-PREF_W/2)/zoom;
				cy=(double)(y+ter*zoom-PREF_H/2)/zoom;
				p=0;
				while (zx*zx+zy*zy<4 && p<PRECISION) {
					aux=zx*zx-zy*zy+cx;
					zy=2.0*zx*zy+cy;
					zx=aux;
					p++;
				}
				
				g2.setColor(colors[p-1]);
				g2.drawLine(x, y,x, y);
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mandelbrot m= new Mandelbrot();
		m.init();
	}
	
	
}

class MandelbrotListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			double zoom =((Mandelbrot)e.getComponent()).zoom;
			int ant=((Mandelbrot)e.getComponent()).cen;
			
	//		double ncx=(double)(e.getX()+ant*zoom-PREF_W/2)/zoom;

			int erior=((Mandelbrot)e.getComponent()).ter;
			e.getY();
			((Mandelbrot)e.getComponent()).zoom=((Mandelbrot)e.getComponent()).zoom*2;
			((Mandelbrot)e.getComponent()).repaint();
		} else if(e.getButton()==MouseEvent.BUTTON3) {
			((Mandelbrot)e.getComponent()).cen=e.getX();
			((Mandelbrot)e.getComponent()).ter=e.getY();
			((Mandelbrot)e.getComponent()).zoom=((Mandelbrot)e.getComponent()).zoom/2;
			((Mandelbrot)e.getComponent()).repaint();
			
		} else {
			((Mandelbrot)e.getComponent()).cen=0;
			((Mandelbrot)e.getComponent()).ter=0;
			((Mandelbrot)e.getComponent()).zoom=((Mandelbrot)e.getComponent()).ZOOM;
			((Mandelbrot)e.getComponent()).repaint();
			
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

