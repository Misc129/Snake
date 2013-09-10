package snake;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import snake.animations.State;
import snake.calculations.Cardinal;
import snake.units.GameGrid;

public class Game extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	/*
	 * TODO
	 * 	bugs:
	 * 		- Turning twice in quick succession will reverse snake back onto itself without actually turning
	 */
	
	private static Image image;
	private static Graphics _second;
	private static URL base;
	private static long startTime,lastFrameTime,deltaTime;
	
	public static int frameInterval = 100;
	public static GameGrid gameGrid;
	public static State gameState;
	
	@Override
	public void init() {
		int x = 0;
		int y = 0;
		int width = 600;
		int height = 400;
		gameGrid = new GameGrid(x, y, width, height);
		
		setSize(gameGrid.getPXWidth(), gameGrid.getPXHeight());
		setBackground(Color.gray);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Game");
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		try{
			base = getDocumentBase();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Thread thread = new Thread(this);
		thread.start();
		startTime = lastFrameTime = System.currentTimeMillis();
		gameState = State.INIT_GAME;
		gameState.start();
	}

	@Override
	public void start() {}
	@Override
	public void stop() {}
	@Override
	public void destroy() {}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			_second = image.getGraphics();
		}

		_second.setColor(getBackground());
		_second.fillRect(0, 0, getWidth(), getHeight());
		_second.setColor(getForeground());
		paint(_second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void run() {
		while(true){
			repaint();
			deltaTime = frameInterval - (System.currentTimeMillis() - lastFrameTime);
			if(deltaTime > 0)
				try{
					Thread.sleep(deltaTime);
				}catch(Exception e){
					e.printStackTrace();
				}
			lastFrameTime = System.currentTimeMillis();
		}
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		gameState.registerKeyEvent(keyEvent);
	}
	@Override
	public void keyReleased(KeyEvent keyEvent) {}
	@Override
	public void keyTyped(KeyEvent keyEvent) {}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		System.out.println("click");
		gameState.registerMouseEvent(mouseEvent);
	}
	
	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
		System.out.println("move");
		gameState.registerMouseEvent(mouseEvent);
	}
	
	@Override
	public void mouseEntered(MouseEvent mouseEvent) {}
	@Override
	public void mouseExited(MouseEvent mouseEvent) {}
	@Override
	public void mouseReleased(MouseEvent mouseEvent) {}
	@Override
	public void mousePressed(MouseEvent mouseEvent) {}
	@Override
	public void mouseDragged(MouseEvent mouseEvent) {}

	
	
	@Override
	public void paint(Graphics g) {
		gameState.update();
		gameState.draw(g);
	}
}
