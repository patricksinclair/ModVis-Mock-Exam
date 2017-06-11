
public class Point {

	double phi;

	public Point(double phi){
		this.phi = phi;
	}

	public double getPhi(){
		return phi;
	}
	public void setPhi(double phi){
		this.phi = phi;
	}





	public static Point[][] Lattice2D(int N, int R){

		Point[][] lattice = new Point[N][N];

		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){

				if(Math.sqrt((i-N/2)*(i-N/2) + (j-N/2)*(j-N/2)) <= R) lattice[i][j] = new Point(1.);
				else lattice[i][j] = new Point(0.);
			}
		}

		return lattice;
	}
	
	
	public static Point[] Lattice1D(int N){
		Point[] lattice = new Point[N];
		int lim = N/10;
		
		for(int i = 0; i < N; i++){
			if(i < lim) lattice[i] = new Point(1.);
			else lattice[i] = new Point(0.);
		}
		return lattice;
	}

	public static Point[][] Lattice2DZero(int N){

		Point[][] lattice = new Point[N][N];

		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){

				lattice[i][j] = new Point(0.);
			}
		}

		return lattice;
	}
	
	public static Point[] Lattice1DZero(int N){
		
		Point[] lattice = new Point[N];
		
		for(int i = 0; i < N; i++){
			lattice[i] = new Point(0.);
		}
		
		return lattice;
	}


}
