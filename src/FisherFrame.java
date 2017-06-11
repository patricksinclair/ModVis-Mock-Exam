import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class FisherPanel extends JPanel{

	FisherLattice fLat;
	int N;

	public FisherPanel(FisherLattice fLat){
		this.fLat = fLat;
		this.N = fLat.getLattice().length;
		setBackground(Color.BLACK);
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		int w = getWidth()/N;
		int h = getHeight()/N;

		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				g.setColor(new Color((int)(fLat.getPhi(i, j)*255), 0, 0));
				g.fillRect(w*i, h*j, w, h);
				//System.out.println(fLat.getPhi(i, j));
			}
		}
	}


	public void diffuseAnimate(){
		//for(int i = 0; i < N*N; i++){
		fLat.diffuse();
		//}
		repaint();
	}


}

public class FisherFrame extends JFrame{

	FisherPanel fPan;
	FisherLattice fLat;

	Timer diffuseTimer;

	JButton goButton = new JButton("Go");

	public FisherFrame(FisherLattice fLat){

		this.fLat = fLat;
		fPan = new FisherPanel(fLat);
		fPan.setPreferredSize(new Dimension(500, 500));

		JPanel controlPanel = new JPanel();
		controlPanel.add(goButton);

		getContentPane().add(fPan, BorderLayout.CENTER);
		getContentPane().add(controlPanel, BorderLayout.SOUTH);
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
				System.exit(0);
			}
		});

		setTitle("Diffusion");
		setLocation(100, 20);
		setVisible(true);
		setBackground(Color.LIGHT_GRAY);

		go();
	}


	public void go(){

		diffuseTimer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update action
				fPan.diffuseAnimate();
			}
		});

		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				if(!diffuseTimer.isRunning()){
					diffuseTimer.start();
					goButton.setText("Stop");
				}else{
					diffuseTimer.stop();
					goButton.setText("Go");
				}
			}
		});
	}
}











