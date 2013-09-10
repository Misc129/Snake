package snake.animations;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import snake.Game;

public final class GameOver implements Animation {

	private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void handleMouseEvent(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyEvent(KeyEvent keyEvent) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setFont(FONT);
		g.drawString("Game Over", Game.gameGrid.getPXMidpointX() - 95, Game.gameGrid.getPXMidpointY());
	}

}
