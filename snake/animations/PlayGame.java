package snake.animations;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import snake.Game;
import snake.calculations.Cardinal;

public class PlayGame implements Animation {

	@Override
	public void start() {}

	@Override
	public void handleMouseEvent(MouseEvent mouseEvent) {}

	@Override
	public void handleKeyEvent(KeyEvent keyEvent) {
		if(keyEvent.getID() == KeyEvent.KEY_PRESSED){
			switch(keyEvent.getKeyCode()){
			case KeyEvent.VK_LEFT:
				if(Game.gameGrid.getSnake().getDirection() != Cardinal.EAST)
					Game.gameGrid.getSnake().setDirection(Cardinal.WEST);
				break;
			case KeyEvent.VK_UP:
				if(Game.gameGrid.getSnake().getDirection() != Cardinal.SOUTH)
					Game.gameGrid.getSnake().setDirection(Cardinal.NORTH);
				break;
			case KeyEvent.VK_RIGHT:
				if(Game.gameGrid.getSnake().getDirection() != Cardinal.WEST)
					Game.gameGrid.getSnake().setDirection(Cardinal.EAST);
				break;
			case KeyEvent.VK_DOWN:
				if(Game.gameGrid.getSnake().getDirection() != Cardinal.NORTH)
					Game.gameGrid.getSnake().setDirection(Cardinal.SOUTH);
				break;
			}
		}
	}

	@Override
	public void update() {
		Game.gameGrid.update();
	}

	@Override
	public void draw(Graphics g) {
		Game.gameGrid.draw(g);
	}

}
