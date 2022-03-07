import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChartsCSV {

	private int time;			// 0
	private float close;		// 1
	private float smma;			// 2
	private float ema;			// 3
	private float rsi;			// 4
	private float obv;			// 5

	
	public ChartsCSV(int time,  float close, float smma,float ema, float rsi, float obv) {
		
		this.time = time;
		this.close = close;
		this.smma = smma;
		this.ema = ema;
		this.rsi = rsi;
		this.obv = obv;
		
	}
	
	static ChartsCSV createCharts(String[] metadata) {
		
        int time = Integer.parseInt(metadata[0]);
        float close = Float.parseFloat(metadata[1]);
        float smma = Float.parseFloat(metadata[2]);
        float ema = Float.parseFloat(metadata[3]);
        float rsi = Float.parseFloat(metadata[4]);
        float obv = Float.parseFloat(metadata[5]);
        
        return new ChartsCSV(time, close, smma, ema, rsi, obv);
    }
	
    static List<ChartsCSV> readChartsFromCSV(String fileName) {
	    List<ChartsCSV> charts = new ArrayList<>();
	    Path pathToFile = Paths.get(fileName);

	    try (BufferedReader br = Files.newBufferedReader(pathToFile,
	          StandardCharsets.US_ASCII)) {

	          String line = br.readLine();
	          line = br.readLine();
	            
	       while (line != null) {

	          String[] attributes = line.split(",");
	          ChartsCSV chart = ChartsCSV.createCharts(attributes);
	          charts.add(chart);
	         line = br.readLine();
	      }

	   } catch (IOException ioe) {
		   
	   ioe.printStackTrace();
	   }
	        return charts;
	    }
	    	
    public float getEma() {
		return ema;
	}

	public void setEma(float ema) {
		this.ema = ema;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	public float getRsi() {
		return rsi;
	}

	public void setRsi(float rsi) {
		this.rsi = rsi;
	}


	public float getSmma() {
		return smma;
	}

	public void setSmma(float smma) {
		this.smma = smma;
	}

	public float getObv() {
		return obv;
	}

	public void setObv(float obv) {
		this.obv = obv;
	}
	
}
