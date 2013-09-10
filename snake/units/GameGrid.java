package snake.units;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class GameGrid implements Drawable{

	public enum TileState{
		OPEN(Color.GRAY),
		FOOD(Color.GREEN),
		SNAKE(Color.BLACK);
		
		TileState(Color color){
			this.color = color;
		}
		
		private Color color;
		
		public Color getColor(){
			return color;
		}
	}
		
	public GameGrid(int px_x, int px_y, int px_width, int px_height){
		this.px_x = px_x;
		this.px_y = px_y;
		this.px_width = px_width;
		this.px_height = px_height;
		this.px_midpoint_x = (px_x + px_width) / 2;
		this.px_midpoint_y = (px_y + px_height) / 2;
		this.field = new Rectangle(px_x, px_y, px_width, px_height);
		tile_width = px_width / TILE_SIZE;
		tile_height = px_height / TILE_SIZE;
		initGrid();
		snake = new Snake(new Point(tile_width / 2, tile_height / 2), field);
		spawnFood();
	}
	
	private void initGrid(){
		grid = new TileState[tile_width][tile_height];
		for(int ix = 0; ix < grid.length; ix++){
			for(int iy = 0; iy < grid[0].length; iy++){
				grid[ix][iy] = TileState.OPEN;
			}
		}
	}
	
	private int px_x;
	private int px_y;
	private int px_width;
	private int px_height;
	private int px_midpoint_x;
	private int px_midpoint_y;
	private int tile_width;
	private int tile_height;
	private Rectangle field;
	private Snake snake;
	private TileState[][] grid;
	
	public static final int TILE_SIZE = 10;
	
	public Rectangle getFieldRectangle(){
		return field;
	}
	
	public Snake getSnake(){
		return snake;
	}
	
	public TileState[][] getGrid(){
		return grid;
	}
	
	public void setStateAt(Point p, TileState state){
		grid[p.x][p.y] = state;
	}
	
	public TileState getStateAt(Point p){
		return grid[p.x][p.y];
	}
	
	public int getPXX(){
		return px_x;
	}
	public int getPXY(){
		return px_y;
	}
	public int getPXWidth(){
		return px_width;
	}
	public int getPXHeight(){
		return px_height;
	}
	public int getPXMidpointX(){
		return px_midpoint_x;
	}
	public int getPXMidpointY(){
		return px_midpoint_y;
	}
	public int getPXMaxX(){
		return px_x + px_width;
	}
	public int getPXMaxY(){
		return px_y + px_height;
	}
	public int getTileWidth(){
		return tile_width;
	}
	public int getTileHeight(){
		return tile_height;
	}
	
	public void spawnFood(){
		//try 100 random tiles
		Point tile = null;
		int x = 0, y = 0;
		for(int i = 0; i < 100; i++){
			x = (int)(Math.random() * tile_width);
			y = (int)(Math.random() * tile_height);
			tile = new Point(x,y);
			if(getStateAt(tile) == TileState.OPEN){
				setStateAt(tile, TileState.FOOD);
				return;
			}
		}
		for(int ix = 0; ix < tile_width; ix++){
			for(int iy = 0; iy < tile_height; iy++){
				if(grid[ix][iy] == TileState.OPEN){
					grid[ix][iy] = TileState.FOOD;
					return;
				}
			}
		}
	}

	@Override
	public void update() {
		snake.update();
		for(Point p : snake.getPoints()){
			setStateAt(p, TileState.SNAKE);
		}
	}

	@Override
	public void draw(Graphics g) {
		snake.draw(g);
		//shouldn't be necassary to redraw every tile every time
		for(int ix = 0; ix < tile_width; ix++){
			for(int iy = 0; iy < tile_height; iy++){
				g.setColor(grid[ix][iy].getColor());
				g.fillRect(px_x + (ix * 10), px_y + (iy * 10), TILE_SIZE, TILE_SIZE);
			}
		}
	}
}

