import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Run implements Runnable {
	
	static int capacityOfQueue = 1000000;
	BlockingQueue<ThreadData> queue = null;
	
//	static int capacityOfQueue2 = 100000;
//	static BlockingQueue<StatsTrade> queueStats = new LinkedBlockingDeque<StatsTrade>(capacityOfQueue2);
	
	static List<ChartsCSV> charts = null;
	ObjToCSV newObjToCSV = null;
	
	static float prozentPair = 2;
	
	static int g_1 = 1;							// soll % min
	static int g_2 = 15;						// soll % max
	
	static int sr_1 = 5;						// sollRsiBuy min
	static int sr_2 = 90;						// sollRsiBuy max
	static int srd_1 = 2;						// sollRsidownlimitBuy
	
	static int p = 5;							// Pairs
	
	static int e_1d = 95;						// ema untere Grenze min
	static int e_2d = 105;						// ema untere Grenze max
	static int e_1u = e_1d + 1;					// ema obere Grenze min
	static int e_2u = e_2d + 1;					// ema obere Grenze max
	
	static int smma500_start = 95;				// getClose ¸ber smma500
	static int smma500_end = 105;				// getClose ¸ber smma500
	
	static int rebuytimeout = 1;
	
	static boolean dca_enabled = true;
	static int dca1_a = 80;
	static int dca1_b = 99;
	static int dcalevel_allowed = 4;
	
	// runtime prediction settings
	static int isDone = 0;
	static int sizeThread = 0;
	static int power1 = 0;
	static int power2 = 0;
	static int powerPerSek = 0;
	static long queueDone = 0;
	static long size = (g_2 - g_1) * (sr_2 - sr_1) * (srd_1/srd_1) * (e_2d - e_1d) * (e_2u - e_1u) * (smma500_end - smma500_start) * (dca1_b - dca1_a) * p;
	// -
	
	public String corename;
	
    static ObjToCSV core1_ObjCSV = new ObjToCSV();
    static ObjToCSV core2_ObjCSV = new ObjToCSV();
    static ObjToCSV core3_ObjCSV = new ObjToCSV();
    static ObjToCSV core4_ObjCSV = new ObjToCSV();
    static ObjToCSV core5_ObjCSV = new ObjToCSV();
    static ObjToCSV core6_ObjCSV = new ObjToCSV();
    static ObjToCSV core7_ObjCSV = new ObjToCSV();
    static ObjToCSV core8_ObjCSV = new ObjToCSV();
    static ObjToCSV core9_ObjCSV = new ObjToCSV();
    static ObjToCSV core10_ObjCSV = new ObjToCSV();
    static ObjToCSV core11_ObjCSV = new ObjToCSV();
    static ObjToCSV core12_ObjCSV = new ObjToCSV();
	
	public Run(String corename, BlockingQueue<ThreadData> queue, ObjToCSV newObjToCSV) {
		
		this.corename = corename;
		this.queue = queue;
		this.newObjToCSV = newObjToCSV;
	}
		
	static void runtime_prediction() {
		
		System.out.println("Ungef‰hr " + size + " Queue Eintr‰ge");
		powerPerSek = (power2-power1)/2;
		System.out.println("Gesch‰tzte Laufzeit: " + size/powerPerSek/60 + " Minuten.");
		}
	
	public static void main(String[] args) throws InterruptedException {
		
		Scanner s = new Scanner(System.in);
		Konto neuesKonto = new Konto();
		Trader newTrader = null;
		String csvdatennameL1 = "BINANCE ADAUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv";
		
		BlockingQueue<ThreadData> queue = new LinkedBlockingDeque<ThreadData>(capacityOfQueue);
				
		charts = ChartsCSV.readChartsFromCSV(csvdatennameL1);
		List<Portfolio> folios = new ArrayList<>();
		
		Run myTask1 = new Run("core1", queue, core1_ObjCSV);
		Run myTask2 = new Run("core2", queue, core2_ObjCSV);
		Run myTask3 = new Run("core3", queue, core3_ObjCSV);
		Run myTask4 = new Run("core4", queue, core4_ObjCSV);
		Run myTask5 = new Run("core5", queue, core5_ObjCSV);
		Run myTask6 = new Run("core6", queue, core6_ObjCSV);
		Run myTask7 = new Run("core7", queue, core7_ObjCSV);
		Run myTask8 = new Run("core8", queue, core8_ObjCSV);
		Run myTask9 = new Run("core9", queue, core9_ObjCSV);
		Run myTask10 = new Run("core10", queue, core10_ObjCSV);
		Run myTask11 = new Run("core11", queue, core11_ObjCSV);
		Run myTask12 = new Run("core12", queue, core12_ObjCSV);
		
		int eingabe;		
		long timeStart = 0;	
		long timeEndAllCores = 0;
		long timeEndSystem = 0;
		
		while(true) {
			
			if (neuesKonto.kontostand == -1) {
				System.out.print("Bitte Kontostand festlegen.. = ");
				neuesKonto.kontostand = (float)s.nextInt();
			}
			System.out.println("maxMemory " + Runtime.getRuntime().maxMemory());
			System.out.println("freeMemory " + Runtime.getRuntime().freeMemory());
			System.out.print("Bitte um Eingabe:");
			eingabe = s.nextInt();
			
			if (eingabe == 0) System.exit(0);
			
			if (eingabe == 1) {
				newTrader = new Trader(3, 35, 5, 96, 98, 8, 1, 2);
				
				timeStart = System.currentTimeMillis();
				for (int n = 0; n < charts.size(); n++) {
				if (newTrader.istpairs == 0) newTrader.coinsBuyByKonto(neuesKonto.kontostand, charts.get(n).getClose());
				if (newTrader.istpairs < newTrader.maxpairs) newTrader.tryKaufen(charts.get(n), neuesKonto, folios, newTrader.emaFaktor, n, newTrader.emaFaktorLimit, newTrader.smma500);
				if (folios.isEmpty()==false) newTrader.tryVerkaufen(charts.get(n), neuesKonto, folios); 
				}
				
				timeEndSystem = System.currentTimeMillis();
//				ObjToCSV newObjToCSV = new ObjToCSV();
//				newTrader = new Trader(3, 35, 5, 96, 98, rebuytimeout, 8, 1);
//				newTrader.mainwork(newTrader, 0, 0, folios, charts, newObjToCSV);
			}
			
			if (eingabe == 2) {
				
				for (int i = 0; i < folios.size(); i++) System.out.println(folios.get(i).toString(charts.get(charts.size()-1).getClose(), neuesKonto));
				if (folios.isEmpty() == false) neuesKonto.nowPortfolio = neuesKonto.anzahlcoins*(charts.get(folios.size()-1).getClose());
				if (folios.isEmpty() == false) neuesKonto.allholdingtime =neuesKonto.allholdingtime/folios.size();
				newTrader.toStringTrader(neuesKonto, newTrader, charts, csvdatennameL1, folios);
			}
			
			if (eingabe == 3) {

				timeStart = System.currentTimeMillis();
				
				Thread mT1 = new Thread(myTask1);
				Thread mT2 = new Thread(myTask2);
				Thread mT3 = new Thread(myTask3);
				Thread mT4 = new Thread(myTask4);
				Thread mT5 = new Thread(myTask5);
				Thread mT6 = new Thread(myTask6);
				Thread mT7 = new Thread(myTask7);
				Thread mT8 = new Thread(myTask8);
				Thread mT9 = new Thread(myTask9);
				Thread mT10 = new Thread(myTask10);
				Thread mT11 = new Thread(myTask11);
				Thread mT12 = new Thread(myTask12);
				
				mT1.start();
				mT2.start();
				mT3.start();
				mT4.start();
				mT5.start();
				mT6.start();
				mT7.start();
				mT8.start();
				mT9.start();
				mT10.start();
				mT11.start();
				mT12.start();

				for (int g_range = g_1; g_range < g_2; g_range++) {													// sollGain Range
			
					for (int rsi_range = sr_1; rsi_range < sr_2; rsi_range++) {										// sollRsi	
														
							for (int pairs_range = 1; pairs_range < p; pairs_range++) {								// Pairs
						
								for (int ema_u = e_2d; ema_u > e_1d; ema_u--) { 									// ema unteregrenze
								
									for (int ema_o = e_1u; ema_o < e_2u; ema_o++) {									// ema oberegrenze	
						
										for (int d1_range = dca1_a; d1_range < dca1_b; d1_range++) {								// dca range	

												for (int smma_range = smma500_start; smma_range < smma500_end; smma_range++) {		// smma range
													
													ThreadData newData = new ThreadData(g_range, rsi_range, srd_1, pairs_range, ema_u, ema_o, d1_range, smma_range);
													queue.add(newData);

													if (queue.size()>=500000) {
														
														Thread.sleep(500);
														power1 = queue.size();
														Thread.sleep(4000);
														power2 = queue.size();
														powerPerSek = (power1-power2)/4; 
														System.out.flush();  
														System.out.println(java.time.LocalTime.now() + " " + "Ungef‰hre Dauer: " + ((size-queueDone)/powerPerSek)/60 + " min.");
														System.out.println(java.time.LocalTime.now() + " " + "Fortschritt: " + Math.round((((float)queueDone/size)*100)*100)/100.0 + " % der Queues bearbeitet.");	
													}
							}
						  }
				        }	
					  }
				    }
				  }
				}
				
				do {
					
					if (queue.size() ==0) {
						System.out.println(java.time.LocalTime.now() + " " + "Fortschritt: 100 % der Queues bearbeitet.");
						isDone = 1;
						}
				} while (isDone==0);
				
				try {
					
					mT1.join();
					mT2.join();
					mT3.join();
					mT4.join();
					mT5.join();
					mT6.join();
					mT7.join();
					mT8.join();
					mT9.join();
					mT10.join();
					mT11.join();
					mT12.join();
				}catch(InterruptedException e){
				    System.out.println("nicht alle Threads gestartet");
				}
					
				timeEndAllCores = System.currentTimeMillis();
				
				ObjToCSV newObjToCSVAll = new ObjToCSV();
				System.out.println("MainThread schreibt CSV..");
				newObjToCSVAll.writeAllLayersToCSV(core1_ObjCSV, core2_ObjCSV, core3_ObjCSV, core4_ObjCSV, core5_ObjCSV, core6_ObjCSV, core7_ObjCSV, core8_ObjCSV, core9_ObjCSV, core10_ObjCSV, core11_ObjCSV, core12_ObjCSV, "Layer_15_All_Cores.csv");
									
				timeEndSystem = System.currentTimeMillis();
			}
			
			if (eingabe == 4) {
				
				System.out.println("Verlaufszeit des Systems: " + ((timeEndSystem - timeStart)/1000) + " Sek.");
				System.out.println("Verlaufszeit bis alle Cores abgeschlossen: " + ((timeEndAllCores - timeStart)/1000) + " Sek.");
				System.out.println("Berechnete Queuers: " + sizeThread);
			}
			
			if (eingabe == 5) System.out.println("getEma: " + charts.get(33).getEma());
			
			if (eingabe == 6) {
				
//				newTrader.toNextLayer(newTrader, newObjToCSV, newObjToCSVL2, "BINANCE BCHUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, "Layer_2.csv");
//				newTrader.toNextLayer(newTrader, newObjToCSVL2, newObjToCSVL3, "BINANCE BNBUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, "Layer_3.csv");
//				newTrader.toNextLayer(newTrader, newObjToCSVL3, newObjToCSVL4, "BINANCE ETHUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, "Layer_4.csv");
//				newTrader.toNextLayer(newTrader, newObjToCSVL4, newObjToCSVL5, "BINANCE LTCUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, "Layer_5.csv");
//				newTrader.toNextLayer(newTrader, newObjToCSVL5, newObjToCSVL6, "BINANCE SOLUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, "Layer_6.csv");
//				newTrader.toNextLayer(newTrader, newObjToCSVL6, newObjToCSVL7, "BINANCE LINKUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, "Layer_7.csv");
//				newTrader.toNextLayer(newTrader, newObjToCSVL7, newObjToCSVL8, "BINANCE DOTUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, "Layer_8.csv");
			}
		}
	}

	@Override
	public void run() {
		
		List<Portfolio> folios = new ArrayList<>();
		ObjToCSV nextLayer_newObjToCSV = new ObjToCSV();
		ObjToCSV nextLayer2_newObjToCSV = new ObjToCSV();
		File file = new File("test.log");
		PrintStream ps = null;
		
		try {
			ps = new PrintStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		do {
			try {
				if(queue.size() != 0 || queue.isEmpty() == false) { 
					
					ThreadData newData = queue.take();
					Trader newTrader = new Trader(newData.maxpairs, newData.sr_1, newData.sollRsidownlimitBuy, newData.ema_u, newData.ema_o, newData.g_1, newData.smma, newData.dca1range);
					newTrader.mainwork(newTrader, folios, charts, newObjToCSV);
					queueDone++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace(ps);
		} catch (OutOfMemoryError e) {
			e.printStackTrace(ps);
			}
		} while (isDone == 0 || queue.size() != 0); 	
		
		Trader newTrader = new Trader();
		System.out.println(java.time.LocalTime.now() + " " + corename + " findet offene Queue " + queue.size());
		System.out.println(java.time.LocalTime.now() + " " + corename + " startet nextLayer..");
		
		nextLayer_newObjToCSV = newTrader.toNextLayerThread(newObjToCSV, "BINANCE BCHUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer1");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer1 ab..");
		newObjToCSV = null;
		nextLayer2_newObjToCSV = newTrader.toNextLayerThread(nextLayer_newObjToCSV, "BINANCE BNBUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer2");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer2 ab..");
		nextLayer_newObjToCSV = newTrader.toNextLayerThread(nextLayer2_newObjToCSV, "BINANCE BTCUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer3");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer3 ab..");
		nextLayer2_newObjToCSV = newTrader.toNextLayerThread(nextLayer_newObjToCSV, "BINANCE CAKEUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer4");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer4 ab..");
		nextLayer_newObjToCSV = newTrader.toNextLayerThread(nextLayer2_newObjToCSV, "BINANCE DOTUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer5");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer5 ab..");
		nextLayer2_newObjToCSV = newTrader.toNextLayerThread(nextLayer_newObjToCSV, "BINANCE ETHUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer6");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer6 ab..");
		nextLayer_newObjToCSV = newTrader.toNextLayerThread(nextLayer2_newObjToCSV, "BINANCE LINKUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer7");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer7 ab..");
		nextLayer2_newObjToCSV = newTrader.toNextLayerThread(nextLayer_newObjToCSV, "BINANCE LTCUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer8");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer8 ab..");
		nextLayer_newObjToCSV = newTrader.toNextLayerThread(nextLayer2_newObjToCSV, "BINANCE LUNAUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer9");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer9 ab..");
		nextLayer2_newObjToCSV = newTrader.toNextLayerThread(nextLayer_newObjToCSV, "BINANCE MATICUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer10");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer10 ab..");
		nextLayer_newObjToCSV = newTrader.toNextLayerThread(nextLayer2_newObjToCSV, "BINANCE SOLUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer11");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer11 ab..");
		nextLayer2_newObjToCSV = newTrader.toNextLayerThread(nextLayer_newObjToCSV, "BINANCE UNIUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer12");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer12 ab..");
		nextLayer_newObjToCSV = newTrader.toNextLayerThread(nextLayer2_newObjToCSV, "BINANCE XMRUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer13");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer13 ab..");
		nextLayer2_newObjToCSV = newTrader.toNextLayerThread(nextLayer_newObjToCSV, "BINANCE XRPUSDT, 1m, CLOSE, SMMA500, EMA100, RSI30, OBV200-1.csv", "Layer14");
		System.out.println(java.time.LocalTime.now() + " " + corename + " schlieﬂt Layer14 ab..");
		nextLayer_newObjToCSV = null;
		
		if (corename == "core1") core1_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core2") core2_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core3") core3_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core4") core4_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core5") core5_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core6") core6_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core7") core7_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core8") core8_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core9") core9_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core10") core10_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core11") core11_ObjCSV = nextLayer2_newObjToCSV;
		if (corename == "core12") core12_ObjCSV = nextLayer2_newObjToCSV;

	} 
}
