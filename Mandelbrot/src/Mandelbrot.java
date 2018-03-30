
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
	public   int PREF_W = 800; 
	public   int PREF_H = 600;
	private static  int PRECISION=255; 
	public  double ZOOM=100.0;
	
	public double zoom;
	public int precision;
	public double cen, ter;
	
	Mandelbrot() throws HeadlessException {
		super();
	}
	
	private void init() {
		
		zoom=ZOOM;
		cen=0;
		ter=0;
		precision=(int)Math.floor(zoom*PRECISION/100);
		
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
		
		Color[] colors = new Color[precision];
		int rgb=0;
		for (int i=0;i<precision;i++) {
			rgb=255-i*255/precision;
			colors[i]=new Color(rgb,rgb/2,rgb/3);
		}
		
		// explores
		for (int x=0;x<getWidth();x++) {
			for (int y=0;y<getHeight();y++) { 
				zx=0.0;
				zy=0.0;
				
				cx=(double)(x+cen*zoom-getWidth()/2)/zoom;
				cy=(double)(y+ter*zoom-getHeight()/2)/zoom;
				p=0;
				while (zx*zx+zy*zy<4 && p<precision) {
					aux=zx*zx-zy*zy+cx;
					zy=2.0*zx*zy+cy;
					zx=aux;
					p++;
				}
				
				
				//if(p<precision)	g2.drawLine(x, y,x, y);// sin colores
				g2.setColor(colors[p-1]);
				g2.drawLine(x, y,x, y);
			}
		}
		System.out.println("centro=("+cen+","+ter+") zoom="+zoom);
		
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
			double ant=((Mandelbrot)e.getComponent()).cen;
			double erior=((Mandelbrot)e.getComponent()).ter;
			Mandelbrot M=((Mandelbrot)e.getComponent());
			
			double mx=(double)(e.getX()+ant*zoom-M.getWidth()/2)/zoom;
			double my=(double)(e.getY()+erior*zoom-M.getHeight()/2)/zoom;

			System.out.println("mouse ["+mx+","+my+"]");
			M.zoom=M.zoom*2;
			M.cen=mx;
			M.ter=my;
			((Mandelbrot)e.getComponent()).repaint();
		} else if(e.getButton()==MouseEvent.BUTTON3) {
			double zoom =((Mandelbrot)e.getComponent()).zoom;
			double ant=((Mandelbrot)e.getComponent()).cen;
			double erior=((Mandelbrot)e.getComponent()).ter;
			Mandelbrot M=((Mandelbrot)e.getComponent());
			
			double mx=(double)(e.getX()+ant*zoom-M.getWidth()/2)/zoom;
			double my=(double)(e.getY()+erior*zoom-M.getHeight()/2)/zoom;

			System.out.println("mouse ["+mx+","+my+"]");
			M.zoom=M.zoom/2;
			M.cen=mx;
			M.ter=my;
			((Mandelbrot)e.getComponent()).repaint();
		} else {
			((Mandelbrot)e.getComponent()).cen=0;
			((Mandelbrot)e.getComponent()).ter=0;
			((Mandelbrot)e.getComponent()).zoom=((Mandelbrot)e.getComponent()).ZOOM;
			((Mandelbrot)e.getComponent()).repaint();
			System.out.println("reset");
			
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

