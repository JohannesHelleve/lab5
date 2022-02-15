package labyrinth;

import java.util.Random;

import datastructure.ILabyrinthTileGrid;
import datastructure.LabyrinthTileGrid;
import datastructure.Location;

public class LabyrinthHelper {

	public static ILabyrinthTileGrid loadGrid(char[][] source) {
		int rows = source.length;
		int cols = source[0].length;

		ILabyrinthTileGrid grid = new LabyrinthTileGrid(rows, cols, LabyrinthTile.OPEN);
		for(int col = 0; col < cols; col++) {
			for(int row = 0; row < rows; row++) {
				Location loc = new Location(row, col);
				char symbol = source[row][col];
				LabyrinthTile tile = LabyrinthTile.fromSymbol(symbol);

				if(tile == null) {
					throw new LabyrinthParseException("Incorrect symbol '" + symbol + "' in labyrinth at row " + row + ", col " + col);
				}
				grid.set(loc, tile);
			}
		}

		return grid;
	}

	public static ILabyrinthTileGrid makeRandomGrid(int width, int height) {
		Random random = new Random();

		ILabyrinthTileGrid grid = new LabyrinthTileGrid(width, height, LabyrinthTile.OPEN);
		for(Location loc : grid.locations()) {
			int r = random.nextInt(20);
			LabyrinthTile tile;
			if(r < 15) {
				tile = LabyrinthTile.OPEN;
			} else if(r < 19) {
				tile = LabyrinthTile.WALL;
			} else {
				tile = LabyrinthTile.GOLD;
			}
			grid.set(loc, tile);
		}

		grid.set(new Location(random.nextInt(width), random.nextInt(height)), LabyrinthTile.PLAYER);

		return grid;
	}
}
