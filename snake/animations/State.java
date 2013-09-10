package snake.animations;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import snake.Game;
import snake.units.Drawable;

public enum State implements Drawable{
	INIT_GAME(),//option screen
	PLAY_GAME(),//start and play the game
	GAME_OVER();//game over animation
	
	private Animation animation;
	
	public void start() {
		switch(this){
		case INIT_GAME:
			animation = new InitGame();
			animation.start();
			break;
		case PLAY_GAME:
			animation = new PlayGame();
			animation.start();
			break;
		case GAME_OVER:
			animation = new GameOver();
			animation.start();
			break;
		}
	}
	
	public void registerMouseEvent(MouseEvent mouseEvent){
		if(animation != null){
			animation.handleMouseEvent(mouseEvent);
		}
	}
	
	public void registerKeyEvent(KeyEvent keyEvent){
		if(animation != null){
			animation.handleKeyEvent(keyEvent);
		}
	}
	
	@Override
	public void update() {
		if(animation != null)
			animation.update();
	}
	
	@Override
	public void draw(Graphics g){
		if(animation != null)
			animation.draw(g);
	}

}
