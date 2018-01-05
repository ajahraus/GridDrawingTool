import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingTool {
	
	public static void main (String[] args){
		// I guess the point of this is to run the whole thing in a thread,
		// allowing for other threads to run at the same time. Can't say I
		// Anticipate using this, but there's no reason to remove it. 

		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					createAndShowGUI();
				}
			}				
		);
	}
	// This function does almost all the heavy lifting for now. It starts
	// the GUI, adds the elements and sets up the camera and map panel. 

	private static void createAndShowGUI(){

		System.out.println("Created GUI on EDT? "+ SwingUtilities.isEventDispatchThread());
		JFrame f = new JFrame("Grid Drawing Tool");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Canvas is used to hold the map elements. 
		mapCanvas canvas = new mapCanvas();
		canvas.add(new mapElement(10, 10));

		mapCamera cam = new mapCamera();
		mapPanel mp = new mapPanel(cam);
		mp.setMapCanvas(canvas);

		f.add(mp);
		
		f.pack();		
		f.setVisible(true);

		f.repaint();

	}
}


// I don't know if this belong here or in its own file. It's probably okay
// here, so I'm not going to move it until I have a good reason to. 
class mapPanel extends JPanel{
	mapCanvas canvas;
	mapCamera cam;
	// Main constructor for mapPanel. It always takes a camera, because 
	// the camera is a necessary component
	public mapPanel(mapCamera camera){
		setBorder(BorderFactory.createLineBorder(Color.black));

		cam = camera;

		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				// do something
				//cam.tranformPixelCoords(e.getX(), e.getY());

			canvas.add(new mapElement(e.getX(), e.getY()));

			repaint();

			}
		});

		addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				// I figure I'll use dragging behaviour at some point. 
				// Probably to create a series of connected squares
				// rather than one at a time. Right now dragging does 
				// nothing. 
			}
		});
	}

	public Dimension getPreferredSize(){
		// This is pretty completely arbitrary.
		return new Dimension(1920,1080);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		// Draw map element
		for ( mapElement mapEl : canvas.getElements()){
			mapEl.paintElement(g, this);
		}
	}

	public void setMapCanvas(mapCanvas can){
		canvas = can;
	}
}
