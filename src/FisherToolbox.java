import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FisherToolbox {

	public static void printDataToFile(ArrayList<Double> xData, ArrayList<Double> yData, String filename){

		try{

			File file = new File(filename+".txt");
			if(!file.exists()) file.createNewFile();

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			int n = xData.size();

			for(int i = 0; i < n; i ++){

				String output = String.valueOf(xData.get(i)) + " " + String.valueOf(yData.get(i));
				bw.write(output);
				bw.newLine();
			}
			bw.close();
		}catch (IOException e){}

	}
}
