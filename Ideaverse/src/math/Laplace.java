package math;
//based on Daniel Shiffman's reaction diffusion vid
public class Laplace {
	private Laplace [][] grid;
	private double height;
	private double part;
	private static int width;
	private static int gridLength;
	
	public Laplace(double item){
		part = item;
	}
	
	void setup(){
		grid = new Laplace [(int) width][(int) height];
		for (int i = 0; i<width; i++) {
			for (int j = 0; j<width; j++) {
				double w=1;
				double l=0;
				grid[i][j] = new Laplace(w);
			}
		}
	}
	
	//3x3
	double laplace(int x,int y){
		Laplace spot = grid[x][y];
		double part = spot.part;
		double sum=0;
		//center
		sum+=part*-1;
		//adjacent
		sum+=grid[x+1][y].part;
		sum+=grid[x-1][y].part;
		sum+=grid[x][y+1].part;
		sum+=grid[x][y-1].part;
		//diagonals
		sum+=grid[x-1][y-1].part;
		sum+=grid[x+1][y-1].part;
		sum+=grid[x-1][y+1].part;
		sum+=grid[x+1][y+1].part;
		return sum;
		
	}
}