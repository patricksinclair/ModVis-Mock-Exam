import java.util.ArrayList;


public class FisherLattice {

	public Point[][] lattice;
	public Point[] lattice1D;

	double D = 1.;
	double a = 1.;
	double dx = 1;
	double dt = 0.01;
	double tolerance = 0.0001;

	int N;
	int N1;

	public FisherLattice(Point[][] lattice){
		this.lattice = lattice;
		N = lattice.length;
	}

	public FisherLattice(Point[] lattice1D){
		this.lattice1D = lattice1D;
		N1 = lattice1D.length;
	}

	public Point[][] getLattice(){
		return lattice;
	}
	public void setLattice(Point[][] lattice){
		this.lattice = lattice;
	}

	public Point getPoint(int i, int j){
		i = (i+N)%N;
		j = (j+N)%N;
		return getLattice()[i][j];
	}
	public void setPoint(int i, int j, Point point){
		i = (i+N)%N;
		j = (j+N)%N;
		getLattice()[i][j] = point;
	}

	public double getPhi(int i, int j){
		i = (i+N)%N;
		j = (j+N)%N;
		return getPoint(i, j).getPhi();
	}
	public void setPhi(int i, int j, double phi){
		i = (i+N)%N;
		j = (j+N)%N;
		getPoint(i , j).setPhi(phi);
	}


	public Point[] getLattice1D(){
		return lattice1D;
	}
	public void setLattice1D(Point[] lattice1D){
		this.lattice1D = lattice1D;
	}

	public Point getPoint1D(int i){
		i = (i+N1)%N1;
		return getLattice1D()[i];
	}
	public void setPoint(int i, Point point){
		i = (i+N1)%N1;
		getLattice1D()[i] = point;
	}

	public double getPhi(int i){
		i = (i+N1)%N1;
		return getPoint1D(i).getPhi();
	}
	public void setPhi(int i, double phi){
		i = (i+N1)%N1;
		getPoint1D(i).setPhi(phi);
	}


	public void diffuse(){

		Point[][] tempLattice = Point.Lattice2DZero(N);

		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){

				double newPhi = getPhi(i, j) + D*dt*(1/(dx*dx))*(getPhi(i+1, j) + getPhi(i-1, j) + getPhi(i, j+1) + getPhi(i, j-1) - 4.*getPhi(i, j))
						+a*dt*getPhi(i, j)*(1. - getPhi(i, j));
				tempLattice[i][j].setPhi(newPhi);

			}
		}
		setLattice(tempLattice);
	}



	public void diffuse1D(){

		Point[] tempLattice = Point.Lattice1DZero(N1);

		for(int i = 0; i < N1-1; i++){
			double newPhi = getPhi(i) + D*dt*(1/(dx*dx))*(getPhi(i+1) + getPhi(i-1) - 2.*getPhi(i)) + a*dt*getPhi(i)*(1-getPhi(i));
			//System.out.println(getPhi(i));
			tempLattice[i].setPhi(newPhi);
		}

		setLattice1D(tempLattice);
	}
	
	public double sum1DLattice(){
		double runningTotal = 0.;
		for(int i = 0; i < N1; i++){
			runningTotal += getPhi(i);
		}
		return runningTotal;
	}


	public static FisherLattice cellsInCentre(int N, int R){

		Point[][] lattice = Point.Lattice2D(N, R);

		return new FisherLattice(lattice);
	}


	public static void FisherWaves(int N){

		Point[] points = Point.Lattice1D(N);
		FisherLattice lattice = new FisherLattice(points);
		String filename = "fisherWaves";
		
		ArrayList<Double> xData = new ArrayList<Double>();
		ArrayList<Double> yData = new ArrayList<Double>();
		int iterations = 10000;
		int interval = iterations/50;
		
		for(int i = 0; i < iterations; i++){
			
			lattice.diffuse1D();
			
			if(i%interval == 0){
				xData.add((double)i);
				yData.add(lattice.sum1DLattice());
			//System.out.println(lattice.getPhi(8));
			System.out.println(i);
			}
		}

		FisherToolbox.printDataToFile(xData, yData, filename);
		
	}







}
