import java.awt.*;
import javax.swing.*;

/*
* A map element is anything that has an X and Y location as defined by the
* mapCanvas. Many map elements will also have a rotation (and maybe a
* center of rotation), but that is not a requirement.
* 
*/
public class mapElement{
	
	// Maybe this should be an int? I'm not sure, the int could represent its
	// location in terms of grid coordinates, but a double easily lets you turn
	// of grid snapping. I suspect a double is the way to go, and enforce grid
	// snapping elsewhere in the code.
	private double x;
	private double y;
	// Whether or not the element is able to be rendered, there needs to be some
	// sort of sprite associated with the element, so that it can be seen while
	// it is being placed is the canvas.
	private Image img = new ImageIcon("missingImage.png").getImage();
	// Some mapElements may not be renderable, such as light objects (i.e. for
	// use with dynamic lighting). This would mean that they would need to be
	// able to be "turned off" when creating the final image of the map. This
	// flag is to determine which elements are to be rednered or not.
	boolean isRenderable = true;
	
	/** 
    * Class constructor
	* @param x the x coordinates
	* @param y the y coordinates
    */
	public mapElement(int x, int y){
		X = x;
		Y = y;
	}

	/** 
    * Class constructor with Image
	* @param x the x coordinates
	* @param y the y coordinates
	* @param image the image for the element
    */
	public mapElement(int x, int y, Image image){
		X = x;
		Y = y;
		img = image;
	}	

	public double getX(){
		return this.x;
	}

	public void setX( double newX ){
		this.x = newX;
	}

	public double getY(){
		return this.y;
	}
	
	public void setY( double newY ){
		this.y = newY;
	}

	public boolean getRenderable(){
		return this.isRenderable;
	}

	public void setRenderable(boolean newRend){
		this.isRenderable = newRend;
	}

	public Image getImg(){
		return this.img;
	}

	public void setImg(Image image){
		this.img = image;
	}


	public void paintElement(Graphics g, mapPanel mp){
		g.drawImage(this.img, (int) X, (int) Y, mp);
	}

}