package snake.units;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import snake.Game;
import snake.calculations.Cardinal;
import snake.units.GameGrid.TileState;

public class Snake implements Drawable{

	public Snake(Point p, Rectangle field){
		if(p.x < field.x || p.y < field.y || p.x >= (field.x + field.width) || p.y >= (field.y + field.height))
			throw new IllegalArgumentException();
		points = new ArrayList<Point>();
		points.add(p);
		p = new Point(p.x - 1, p.y);
		points.add(p);
		p = new Point(p.x - 1, p.y);
		points.add(p);
		p = new Point(p.x - 1, p.y);
		points.add(p);
		p = new Point(p.x - 1, p.y);
		points.add(p);
		direction = Cardinal.EAST;
	}
	
	private Cardinal direction;
	private ArrayList<Point> points;
	
	public void move(){
		Point last = points.get(0);
		Point temp = points.get(0);
		Point next = getNextStep(last, direction);
		if(Game.gameGrid.getStateAt(next) == TileState.FOOD){
			//add arbitrary point to end of arraylist (it will be over-written in the shifting)
			//spawn new food
			points.add(new Point(points.get(points.size() - 1)));
			Game.gameGrid.spawnFood();
		}
		else if(Game.gameGrid.getStateAt(next) == TileState.SNAKE){
			//game over
			System.out.println("Game over");
		}
		points.set(0, next);
		for(int i = 1; i < points.size(); i++){
			temp = points.get(i);
			points.set(i, last);
			last = temp;
		}
		Game.gameGrid.setStateAt(last, TileState.OPEN);
	}
	
	public Point getNextStep(Point p, Cardinal direction){
		switch(direction){
		case WEST:
			if(p.x == Game.gameGrid.getPXX())
				return new Point(Game.gameGrid.getTileWidth() - 1, p.y);
			else
				return new Point(p.x - 1, p.y);
		case NORTH:
			if(p.y == Game.gameGrid.getPXY())
				return new Point(p.x, Game.gameGrid.getTileHeight() - 1);
			else
				return new Point(p.x, p.y - 1);
		case EAST:
			if(p.x == Game.gameGrid.getTileWidth() - 1)
				return new Point(Game.gameGrid.getPXX(), p.y);
			else
				return new Point(p.x + 1, p.y);
		case SOUTH:
			if(p.y == Game.gameGrid.getTileHeight() - 1)
				return new Point(p.x, Game.gameGrid.getPXY());
			else
				return new Point(p.x, p.y + 1);
		default:
			return p;
		}
	}
	
	public void setDirection(Cardinal newValue){
		direction = newValue;
	}
	
	public Cardinal getDirection(){
		return direction;
	}
	
	public List<Point> getPoints(){
		return points;
	}

	@Override
	public void update() {
		move();
	}

	@Override
	public void draw(Graphics g) {
		for(Point p : points){
			g.fillRect(Game.gameGrid.getPXX() + (p.x * 10), Game.gameGrid.getPXY() + (p.y * 10), GameGrid.TILE_SIZE, GameGrid.TILE_SIZE);
		}
	}
}
