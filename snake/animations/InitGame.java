package snake.animations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import snake.Game;

public final class InitGame implements Animation {

	public static final Rectangle EASY_BTN = new Rectangle((int)(Game.gameGrid.getPXMaxX() * .2), (int)(Game.gameGrid.getPXMaxY() * .7), (int)(Game.gameGrid.getPXWidth() * .1), (int)(Game.gameGrid.getPXWidth() * .1));
	public static final Rectangle AVG_BTN = new Rectangle((int)(Game.gameGrid.getPXMaxX() * .45), (int)(Game.gameGrid.getPXMaxY() * .7), (int)(Game.gameGrid.getPXWidth() * .1), (int)(Game.gameGrid.getPXWidth() * .1));
	public static final Rectangle HARD_BTN = new Rectangle((int)(Game.gameGrid.getPXMaxX() * .7), (int)(Game.gameGrid.getPXMaxY() * .7), (int)(Game.gameGrid.getPXWidth() * .1), (int)(Game.gameGrid.getPXWidth() * .1));

	private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);

	public boolean easyBtnMouseover, avgBtnMouseover, hardBtnMouseover;

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseEvent(MouseEvent mouseEvent) {
		switch(mouseEvent.getID()){
		case MouseEvent.MOUSE_MOVED:
			easyBtnMouseover = EASY_BTN.contains(mouseEvent.getPoint());
			avgBtnMouseover = AVG_BTN.contains(mouseEvent.getPoint());
			hardBtnMouseover = HARD_BTN.contains(mouseEvent.getPoint());
			break;
		case MouseEvent.MOUSE_CLICKED:
			break;
		}
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
		if(easyBtnMouseover){
			g.setColor(Color.GREEN);
			g.fillRoundRect(EASY_BTN.x - 5, EASY_BTN.y - 5, EASY_BTN.width + 10, EASY_BTN.height + 10, 15, 15);
		}
		else if(avgBtnMouseover){
			g.setColor(Color.GREEN);
			g.fillRoundRect(AVG_BTN.x - 5, AVG_BTN.y - 5, AVG_BTN.width + 10, AVG_BTN.height + 10, 15, 15);
		}
		else if(hardBtnMouseover){
			g.setColor(Color.GREEN);
			g.fillRoundRect(HARD_BTN.x - 5, HARD_BTN.y - 5, HARD_BTN.width + 10, HARD_BTN.height + 10, 15, 15);
		}
		g.setColor(Color.BLACK);
		g.setFont(FONT);
		drawCenteredString("Snake", Game.gameGrid.getPXMidpointX(), (int)(Game.gameGrid.getPXMaxY() * .3), g);
		drawCenteredString("Choose difficulty", Game.gameGrid.getPXMidpointX(), (int)(Game.gameGrid.getPXMaxY() * .65), g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(EASY_BTN.x, EASY_BTN.y, EASY_BTN.width, EASY_BTN.height, 15, 15);
		g.fillRoundRect(AVG_BTN.x, AVG_BTN.y, AVG_BTN.width, AVG_BTN.height, 15, 15);
		g.fillRoundRect(HARD_BTN.x, HARD_BTN.y, HARD_BTN.width, HARD_BTN.height, 15, 15);
	}

	private void drawCenteredString(String s, int xPos, int yPos, Graphics g){
		g.drawString(s, xPos - g.getFontMetrics().stringWidth(s) / 2, yPos);
	}

}
