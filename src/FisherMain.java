
public class FisherMain {

	public static void main(String args[]){
		
		int N = 100;
		int R = 10;
		
		int N1D = 1000;
		
		FisherLattice Lattice2D = FisherLattice.cellsInCentre(N, R);
		FisherFrame fFrame = new FisherFrame(Lattice2D); fFrame.setVisible(true);
		
		//FisherLattice.FisherWaves(N1D);
	}
}
