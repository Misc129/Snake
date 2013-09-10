package snake.animations;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import snake.units.Drawable;

public interface Animation extends Drawable{

	public abstract void start();
	public abstract void update();
	public abstract void handleMouseEvent(MouseEvent mouseEvent);
	public abstract void handleKeyEvent(KeyEvent keyEvent);
	public abstract void draw(Graphics g);
	
}
