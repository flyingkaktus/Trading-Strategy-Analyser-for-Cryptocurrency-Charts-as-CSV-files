import java.util.ArrayList;
import java.util.List;


public class Trader {
	
	int tradeID = 0;
	int maxpairs;
	int istpairs = 0;
	int sollRsiBuy;
	int sollRsidownlimitBuy;
	float dca1range;
	float buyamountincoins;
	float emaFaktor;
	float emaFaktorLimit;
	float gainSell;
	float buyamountcash;
	float exchangefees = (float) 0.075; // %
	float layerGewinn;
	float smma500;
	
	public Trader() {
				
		// Buy Strategy
		buyamountincoins = 0;			// Wird automatisch prozentual über Kontostand ermittelt // Pairs
		buyamountcash = 0;
		maxpairs = 0;
		sollRsiBuy = 0; 
		sollRsidownlimitBuy = 0;
		emaFaktor = (float) 0;			// .getema()*emaFaktor < .close // kaufen()
		emaFaktorLimit = (float) 0;		// .getema()*emaFaktor > .close // kaufen()
		layerGewinn = 0;

		// Sell Strategy
		gainSell = (float) 0;			// %
		smma500 = (float) 0;
	}
	
	public Trader(int maxpairs, int sollRsiBuy, int sollRsidownlimitBuy, float emaFaktor, float emaFaktorLimit, float gainSell, float smma500, int dca1range) {
		
		// Buy Strategy

		this.maxpairs = maxpairs;
		this.sollRsiBuy = sollRsiBuy;
		this.sollRsidownlimitBuy = sollRsidownlimitBuy;
		this.emaFaktor = (float)emaFaktor/100;							// .getema()*emaFaktor < .close // kaufen()
		this.emaFaktorLimit = (float)emaFaktorLimit/100;				// .getema()*emaFaktor > .close // kaufen()

		this.smma500 = smma500;
		this.dca1range = dca1range;
		
		// Sell Strategy
		this.gainSell = (float)gainSell;							// %
		
	}
	
	void coinsBuyByKonto(float kontostand, float close) {
		//buyamountincoins = (float) ((kontostand/maxpairs)/close)/(1+(exchangefees/100));
		buyamountincoins = (float) ((kontostand*(Run.prozentPair/100)/close)/(1+(exchangefees/100)));
	}
		
	void tryVerkaufen(ChartsCSV input, Konto konto, List<Portfolio> folios) {
		for (int i = 0; i < folios.size(); i++) {		
			if (folios.get(i).getEiWert()*(1+(gainSell/100)+(exchangefees/100)) <= input.getClose() && folios.get(i).getStatus() != 1) {
				verkaufen(input.getTime(), input.getClose(), konto, folios.get(i), folios);
			}
		}
	}
	
	void verkaufen(int time, float closePrice, Konto konto, Portfolio portfolio, List<Portfolio> folios) {
		
		portfolio.setVkWert(closePrice);
		portfolio.setStatus(1);
		portfolio.sellTimeUnix = time;
		portfolio.holdingtime = portfolio.sellTimeUnix - portfolio.buyTimeUnix;
		portfolio.setVkDatum(new java.util.Date((long)time*1000));
		
		konto.kontostand = (konto.kontostand + (closePrice*portfolio.coinMenge) - (closePrice*portfolio.coinMenge)*(exchangefees/100));
		konto.gewinn = (konto.gewinn + (closePrice*portfolio.coinMenge) - (portfolio.eiWert*portfolio.coinMenge) - (closePrice*portfolio.coinMenge)*(exchangefees/100));
		konto.anzahlcoins = konto.anzahlcoins - portfolio.coinMenge;
		konto.ekPortfolio = konto.ekPortfolio - portfolio.getCoinWert();
		konto.allTradingFees = (float) (konto.allTradingFees + (closePrice*portfolio.coinMenge)*(exchangefees/100));
		konto.allholdingtime = konto.allholdingtime + portfolio.holdingtime;
		istpairs--;
//		System.out.println("Verkauf wurde ausgeführt für ID: " + portfolio.tradeID);
//		if (portfolio.dca_level > 0) {
//
//			System.out.println("Verkauf erfolgte mit DCA: " + portfolio.dca_level);
//		}

		
	}
	
	void tryKaufen(ChartsCSV input, Konto konto, List<Portfolio> folios, float emafaktor, int row, float emafaktorLimit, float smma500) {
		//    && input.getLowbb()*lowbbFaktorLimit > input.getClose() && input.getLowbb()*lowbbFaktor < input.getClose() &&
		
		if (smma500 < 1) {
		if (input.getSmma() * smma500  >= input.getClose() && input.getEma()*emafaktor < input.getClose() && input.getEma()*emafaktorLimit > input.getClose() && input.getRsi() <= sollRsiBuy && input.getRsi() >= sollRsidownlimitBuy && konto.kontostand >= (input.getClose()*buyamountincoins)) {
			if (folios.isEmpty() == false) {
			
				if (input.getTime() > folios.get(folios.size()-1).buyTimeUnix+(Run.rebuytimeout*60*60)) {
				
					kaufen(input.getTime(), input.getClose(), konto, folios, row);
					//System.out.println("Kauf getätigt.");
				}
			}
			if (folios.isEmpty() == true) {
				
				kaufen(input.getTime(), input.getClose(), konto, folios, row);
			}	
		  } 
		}
		
		if (smma500 >= 1) {
		if (input.getSmma() * smma500  <= input.getClose() && input.getEma()*emafaktor < input.getClose() && input.getEma()*emafaktorLimit > input.getClose() && input.getRsi() <= sollRsiBuy && input.getRsi() >= sollRsidownlimitBuy && konto.kontostand >= (input.getClose()*buyamountincoins)) {
			if (folios.isEmpty() == false) {
			
				if (input.getTime() > folios.get(folios.size()-1).buyTimeUnix+(Run.rebuytimeout*60*60)) {
				
					kaufen(input.getTime(), input.getClose(), konto, folios, row);
					//System.out.println("Kauf getätigt.");
				}
			}
			if (folios.isEmpty() == true) {
				
				kaufen(input.getTime(), input.getClose(), konto, folios, row);
			}	
		  } 
		}
	}

	void kaufen(int time, float closePrice, Konto konto, List<Portfolio> folios, int row) {
		
		tradeID++;
		istpairs++;
		konto.kontostand = (float) (konto.kontostand - (closePrice*buyamountincoins)*(1+(exchangefees/100)));
		konto.anzahlcoins = konto.anzahlcoins + buyamountincoins;

		Portfolio newPortfolio = new Portfolio();
		newPortfolio.setTradeID(tradeID);
		newPortfolio.row = row;
		newPortfolio.buyTimeUnix = time;
		newPortfolio.setEiDatum(new java.util.Date((long)time*1000));
		newPortfolio.setEiWert(closePrice);
		newPortfolio.setCoinWert(closePrice*buyamountincoins);
		newPortfolio.setCoinMenge(buyamountincoins);
		newPortfolio.setKontostandBeimKauf(konto.kontostand);

		folios.add(newPortfolio);
		konto.ekPortfolio = konto.ekPortfolio + newPortfolio.getCoinWert();
		konto.allTradingFees = (float) (konto.allTradingFees + (closePrice*buyamountincoins)*(exchangefees/100));
		konto.gewinn = konto.gewinn - (closePrice*buyamountincoins)*(exchangefees/100);
		//System.out.println("Kauf ausgeführt für ID " + newPortfolio.tradeID);
	}
	
	void tryDCA(ChartsCSV input, Konto konto, List<Portfolio> folios, float dca1range, int row) {
		for (int i = 0; i < folios.size(); i++) {
			if ((input.getClose()/folios.get(i).getEiWert()) <= (dca1range/100) && konto.kontostand >= folios.get(i).getCoinMenge()*input.getClose() && folios.get(i).dca_level < Run.dcalevel_allowed) {
				kaufenDCA(input.getClose(), konto, folios.get(i), row);
			}
		}
	}
	
	void kaufenDCA(float closePrice, Konto konto, Portfolio portfolio, int row) {
//		System.out.println("vor DCA Kauf:");
//		System.out.println("Kontostand:" + konto.kontostand);
//		System.out.println("Coin Menge:" + portfolio.coinMenge);
//		System.out.println("einkaufswert:" + portfolio.eiWert);

		konto.kontostand = (float) (konto.kontostand - (closePrice*portfolio.coinMenge)*(1+(exchangefees/100)));
		konto.anzahlcoins = konto.anzahlcoins + portfolio.coinMenge;

		portfolio.eiWert = (portfolio.eiWert + closePrice)/2;
		portfolio.setKontostandBeimKauf(konto.kontostand);

		konto.ekPortfolio = konto.ekPortfolio - portfolio.getCoinWert();

		konto.allTradingFees = (float) (konto.allTradingFees + (closePrice*portfolio.coinMenge)*(exchangefees/100));
		konto.gewinn = konto.gewinn - (closePrice*portfolio.coinMenge)*(exchangefees/100);
		
		portfolio.coinMenge = portfolio.coinMenge*2;
		portfolio.coinWert = portfolio.eiWert * portfolio.coinMenge;
		konto.ekPortfolio = konto.ekPortfolio + portfolio.getCoinWert();
		
		portfolio.dca_level++;
		
//		System.out.println("nach DCA Kauf:");
//		System.out.println("Kontostand:" + konto.kontostand);
//		System.out.println("Coin Menge:" + portfolio.coinMenge);
//		System.out.println("einkaufswert:" + portfolio.eiWert);
//		System.out.println("Die ID: " + portfolio.tradeID + " hat DCA: " + portfolio.dca_level);

	}

	void mainwork(Trader newTrader, List<Portfolio> folios, List<ChartsCSV> charts, ObjToCSV newObjToCSV) {
		
		Konto neuesKonto = new Konto();
						
		newTrader.smma500 = (float)newTrader.smma500/100;
		
		neuesKonto.reset();
		neuesKonto.kontostand = 500;

		folios.clear();
	
		newTrader.tradeID = 0;
		newTrader.istpairs = 0;
	
		newTrader.runTrader(neuesKonto, folios, newTrader, charts, newObjToCSV, "Layer0");
	}

	void runTrader(Konto neuesKonto, List<Portfolio> folios, Trader newTrader, List<ChartsCSV> charts, ObjToCSV newObjToCSV, String layerLevel) {

			for (int n = 0; n < charts.size(); n++) {
				
				if (newTrader.istpairs == 0) newTrader.coinsBuyByKonto(neuesKonto.kontostand, charts.get(n).getClose());
				
				if (newTrader.istpairs < newTrader.maxpairs) newTrader.tryKaufen(charts.get(n), neuesKonto,
						folios, newTrader.emaFaktor, n, newTrader.emaFaktorLimit, newTrader.smma500);
				
				if (folios.isEmpty()==false) newTrader.tryVerkaufen(charts.get(n), neuesKonto, folios); 
				
				if (folios.isEmpty()==false && Run.dca_enabled==true) newTrader.tryDCA(charts.get(n), neuesKonto, folios, dca1range, n);
			}
				
			neuesKonto.nowPortfolio = neuesKonto.anzahlcoins*(charts.get(charts.size()-1).getClose());
			neuesKonto.absoluter_gewinn = neuesKonto.gewinn + (neuesKonto.nowPortfolio - neuesKonto.ekPortfolio);
			
			if (neuesKonto.absoluter_gewinn > 0) {
				
				if (folios.isEmpty() == false) neuesKonto.allholdingtime = neuesKonto.allholdingtime/folios.size();
				if (folios.isEmpty() == false) neuesKonto.doneTrades = folios.size();
				
				newTrader.layerGewinn = newTrader.layerGewinn + neuesKonto.absoluter_gewinn;
				
				newObjToCSV.addDataToCSV(neuesKonto.absoluter_gewinn, newTrader.gainSell, newTrader.emaFaktor, newTrader.emaFaktorLimit, 
						newTrader.maxpairs, newTrader.sollRsiBuy, newTrader.sollRsidownlimitBuy, (neuesKonto.allholdingtime/60),
						newTrader.istpairs, newTrader.layerGewinn, newTrader.smma500, newTrader.dca1range, neuesKonto.doneTrades, layerLevel);
				
//				if (layerLevel == "Layer1") newObjToCSV.L1_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer2") newObjToCSV.L2_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer3") newObjToCSV.L3_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer4") newObjToCSV.L4_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer5") newObjToCSV.L5_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer6") newObjToCSV.L6_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer7") newObjToCSV.L7_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer8") newObjToCSV.L8_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer9") newObjToCSV.L9_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer10") newObjToCSV.L10_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer11") newObjToCSV.L11_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer12") newObjToCSV.L12_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer13") newObjToCSV.L13_gewinn.add(neuesKonto.absoluter_gewinn);
//				if (layerLevel == "Layer14") newObjToCSV.L14_gewinn.add(neuesKonto.absoluter_gewinn);
//				
//				if (layerLevel == "Layer1") newObjToCSV.L1_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer2") newObjToCSV.L2_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer3") newObjToCSV.L3_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer4") newObjToCSV.L4_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer5") newObjToCSV.L5_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer6") newObjToCSV.L6_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer7") newObjToCSV.L7_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer8") newObjToCSV.L8_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer9") newObjToCSV.L9_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer10") newObjToCSV.L10_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer11") newObjToCSV.L11_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer12") newObjToCSV.L12_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer13") newObjToCSV.L13_doneTrades.add(neuesKonto.doneTrades);
//				if (layerLevel == "Layer14") newObjToCSV.L14_doneTrades.add(neuesKonto.doneTrades);
				
//				StatsTrade newStats = new StatsTrade(neuesKonto.gewinn, newTrader.gainSell, newTrader.emaFaktor, newTrader.emaFaktorLimit, 
//						newTrader.maxpairs, newTrader.sollRsiBuy, newTrader.sollRsidownlimitBuy, (neuesKonto.allholdingtime/60),
//						newTrader.istpairs, newTrader.layerGewinn, newTrader.smma500, newTrader.dca1, neuesKonto.doneTrades);			
				
//				if (layerLevel == "Layer1") newStats.L1_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer2") newStats.L2_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer3") newStats.L3_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer4") newStats.L4_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer5") newStats.L5_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer6") newStats.L6_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer7") newStats.L7_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer8") newStats.L8_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer9") newStats.L9_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer10") newStats.L10_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer11") newStats.L11_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer12") newStats.L12_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer13") newStats.L13_gewinn = neuesKonto.gewinn;
//				if (layerLevel == "Layer14") newStats.L14_gewinn = neuesKonto.gewinn;
//				
//				if (layerLevel == "Layer1") newStats.L1_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer2") newStats.L2_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer3") newStats.L3_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer4") newStats.L4_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer5") newStats.L5_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer6") newStats.L6_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer7") newStats.L7_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer8") newStats.L8_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer9") newStats.L9_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer10") newStats.L10_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer11") newStats.L11_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer12") newStats.L12_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer13") newStats.L13_doneTrades = neuesKonto.doneTrades;
//				if (layerLevel == "Layer14") newStats.L14_doneTrades = neuesKonto.doneTrades;
//				
//				Run.queueStats.add(newStats);
				
				newTrader.layerGewinn = 0;
			}
			
			if (neuesKonto.gewinn > neuesKonto.highest_gewinn) {
			
				neuesKonto.highest_gewinn = neuesKonto.gewinn;
				neuesKonto.winning_rsiBuy = newTrader.sollRsiBuy;
				neuesKonto.winning_gain = (float) newTrader.gainSell;
				neuesKonto.winning_maxpairs = newTrader.maxpairs;
				neuesKonto.winning_rsiDistanz = newTrader.sollRsiBuy - newTrader.sollRsidownlimitBuy;
				neuesKonto.winning_allTradingFees = neuesKonto.allTradingFees;
				neuesKonto.winning_emaFaktor = newTrader.emaFaktor;
				neuesKonto.winning_emaFaktorLimit = newTrader.emaFaktorLimit;
			}
			
			if (neuesKonto.absoluter_gewinn > neuesKonto.highest_absoluter_gewinn) {
			
				neuesKonto.highest_absoluter_gewinn = neuesKonto.absoluter_gewinn;
				neuesKonto.absoluter_winning_rsiBuy = newTrader.sollRsiBuy;
				neuesKonto.absoluter_winning_gain = (float) newTrader.gainSell;
				neuesKonto.absoluter_winning_maxpairs = newTrader.maxpairs;
				neuesKonto.absoluter_winning_rsiDistanz = newTrader.sollRsiBuy - newTrader.sollRsidownlimitBuy;
				neuesKonto.absoluter_winning_allTradingFees = neuesKonto.allTradingFees;
				neuesKonto.absoluter_winning_emaFaktor = newTrader.emaFaktor;
				neuesKonto.absoluter_winning_emaFaktorLimit = newTrader.emaFaktorLimit;
			}			
	}

	ObjToCSV toNextLayerThread(ObjToCSV newObjToCSV, String csvdatennameL2, String layerLevel) {
		
	List<Portfolio> folios = new ArrayList<>();
	List<ChartsCSV> chartsL2 = ChartsCSV.readChartsFromCSV(csvdatennameL2);
	Konto neuesKonto = new Konto();
	Trader newTrader = new Trader();
	ObjToCSV nextLayer_newObjToCSV = new ObjToCSV();
	
	for (int i = 0; i < newObjToCSV.L0_gewinn.size(); i++) {
		
		// Werte, relevant für den nächsten Druchlauf
		
		newTrader.gainSell = newObjToCSV.gain.get(i).floatValue();
		newTrader.emaFaktor = newObjToCSV.emaFaktor.get(i).floatValue();
		newTrader.emaFaktorLimit = newObjToCSV.emaFaktorLimit.get(i).floatValue();
		newTrader.maxpairs = newObjToCSV.maxPairs.get(i).intValue();
		newTrader.sollRsiBuy = newObjToCSV.sollRsiBuy.get(i).intValue();
		newTrader.sollRsidownlimitBuy = newObjToCSV.sollRsidownlimitBuy.get(i).intValue();
		newTrader.layerGewinn = newObjToCSV.layerGewinn.get(i).floatValue();
		newTrader.smma500 = newObjToCSV.smma500.get(i).floatValue();
		newTrader.dca1range = newObjToCSV.dca1.get(i).floatValue();
//		newTrader.layer1Gewinn = newObjToCSV.layer1Gewinn.get(i).floatValue();
		neuesKonto.reset();
		neuesKonto.kontostand = 500;

		folios.clear();
			
		newTrader.tradeID = 0;
		newTrader.istpairs = 0;
		
		newTrader.runTrader(neuesKonto, folios, newTrader, chartsL2, nextLayer_newObjToCSV, layerLevel);
	}

	System.out.println("Objekte in Gewinn: " + nextLayer_newObjToCSV.L0_gewinn.size() + " von " + layerLevel);

	return nextLayer_newObjToCSV;
}
		
	void toNextLayer(Trader newTrader, ObjToCSV newObjToCSV, ObjToCSV nextLayer_newObjToCSV, String csvdatennameL2, Konto neuesKonto, List<Portfolio> folios, String newLayer) {
		
		List<ChartsCSV> chartsL2 = ChartsCSV.readChartsFromCSV(csvdatennameL2);
		
		for (int i = 0; i < newObjToCSV.L1_gewinn.size(); i++) {
			
			// Parameter aus vorherigen Durchlauf werden eingelesen
			newTrader.gainSell = newObjToCSV.gain.get(i).floatValue();
			newTrader.emaFaktor = newObjToCSV.emaFaktor.get(i).floatValue();
			newTrader.emaFaktorLimit = newObjToCSV.emaFaktorLimit.get(i).floatValue();
			newTrader.maxpairs = newObjToCSV.maxPairs.get(i).intValue();
			newTrader.sollRsiBuy = newObjToCSV.sollRsiBuy.get(i).intValue();
			newTrader.sollRsidownlimitBuy = newObjToCSV.sollRsidownlimitBuy.get(i).intValue();
			newTrader.layerGewinn = newObjToCSV.layerGewinn.get(i).floatValue();
			newTrader.dca1range = newObjToCSV.dca1.get(i).floatValue();
			
			neuesKonto.reset();
			neuesKonto.kontostand = 500;
	
			folios.clear();
				
			newTrader.tradeID = 0;
			newTrader.istpairs = 0;
			
			newTrader.runTrader(neuesKonto, folios, newTrader, chartsL2, nextLayer_newObjToCSV, "layer0");
		}
	}
	
	void runLayerSequence(List<Portfolio> folios, Konto neuesKonto, Trader newTrader, ObjToCSV newObjToCSV,ObjToCSV newObjToCSVL2, ObjToCSV newObjToCSVL3, ObjToCSV newObjToCSVL4, ObjToCSV newObjToCSVL5, ObjToCSV newObjToCSVL6, ObjToCSV newObjToCSVL7, ObjToCSV newObjToCSVL8, String corename) {
				
		newTrader.toNextLayer(newTrader, newObjToCSV, newObjToCSVL2, "BINANCE BCHUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, corename + "_Layer_2.csv");
		newTrader.toNextLayer(newTrader, newObjToCSVL2, newObjToCSVL3, "BINANCE BNBUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, corename + "_Layer_3.csv");
		newTrader.toNextLayer(newTrader, newObjToCSVL3, newObjToCSVL4, "BINANCE ETHUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, corename + "_Layer_4.csv");
		newTrader.toNextLayer(newTrader, newObjToCSVL4, newObjToCSVL5, "BINANCE LTCUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, corename + "_Layer_5.csv");
		newTrader.toNextLayer(newTrader, newObjToCSVL5, newObjToCSVL6, "BINANCE SOLUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, corename + "_Layer_6.csv");
		newTrader.toNextLayer(newTrader, newObjToCSVL6, newObjToCSVL7, "BINANCE LINKUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, corename + "_Layer_7.csv");
		newTrader.toNextLayer(newTrader, newObjToCSVL7, newObjToCSVL8, "BINANCE DOTUSDT, 1, RSI, 100EMA.csv", neuesKonto, folios, corename + "_Layer_8.csv");
		newObjToCSVL8.nowWriteCSV("Layer 8 von " + corename + ".csv");
		//abfrageStatic(corename, newObjToCSVL8);
					
	}
	
	void toStringTrader(Konto neuesKonto, Trader newTrader, List<ChartsCSV> charts, String csvdatenname, List<Portfolio> folios) {
		
		System.out.println("--- --- --- --- --- --- --- --- --- ---");
		System.out.println("Last run:");	
		System.out.println("Kontostand Ende: " + neuesKonto.kontostand);
		System.out.println("Anzahl Coins auf Konto " + neuesKonto.anzahlcoins);
		System.out.println("Last Chart-Preis " + charts.get(charts.size()-1).getClose());
		System.out.println("Pairs gehalten: " + newTrader.istpairs);
		System.out.println("Maxpairs erlaubt: " + newTrader.maxpairs);
		System.out.println("Erzielter Gewinn: " + neuesKonto.gewinn);
		System.out.println("Gesamt Einträge im Portfolio: " + folios.size());
		System.out.println("Gesamt Gewinn: " + (neuesKonto.gewinn + (neuesKonto.nowPortfolio - neuesKonto.ekPortfolio)));
		System.out.println("nowPortfolio: " + neuesKonto.nowPortfolio);
		System.out.println("ekPortfolio: " + neuesKonto.ekPortfolio);
		System.out.println("Tradingfees: " + neuesKonto.allTradingFees);
		System.out.println("Buy RSI: " + newTrader.sollRsiBuy);
		System.out.println("Avr. Holding Time: " + (neuesKonto.allholdingtime/60)/60 + "h");
		System.out.println("Tradingfees: " + neuesKonto.allTradingFees);
		System.out.println("Ema Faktor: " + newTrader.emaFaktor);
		System.out.println("Ema Limit Faktor: " + newTrader.emaFaktorLimit);
		System.out.println("SollGain: " + newTrader.gainSell);
		System.out.println("SollSmma: " + newTrader.smma500);
		System.out.println("--- --- --- --- --- --- --- --- --- ---");
		System.out.println(csvdatenname);
		System.out.println("Anfang: " + new java.util.Date((long)charts.get(0).getTime()*1000));
		System.out.println("Ende: " + new java.util.Date((long)charts.get(charts.size()-1).getTime()*1000));
		System.out.println("--- --- --- --- --- --- --- --- --- ---");
		System.out.println("+++++ Höchster Gewinn: " + neuesKonto.highest_gewinn + " +++++");
		System.out.println("+++++ mit RSI Buy: " + neuesKonto.winning_rsiBuy + " +++++");
		System.out.println("+++++ mit RSI Distanz: " + neuesKonto.winning_rsiDistanz + " +++++");
		//System.out.println("+++++ mit RSI Sell: " + neuesKonto.winning_rsiSell + " +++++");
		System.out.println("+++++ und Gain: " + neuesKonto.winning_gain + " +++++");
		System.out.println("+++++ und Maxpairs: " + neuesKonto.winning_maxpairs + " +++++");
		System.out.println("+++++ Tradingfees: " + neuesKonto.winning_allTradingFees + " +++++");
		System.out.println("+++++ Ema Faktor: " + neuesKonto.winning_emaFaktor + " +++++");
		System.out.println("+++++ Ema Limit Faktor: " + neuesKonto.winning_emaFaktorLimit + " +++++");

		System.out.println("--- --- --- --- --- --- --- --- --- ---");
		System.out.println("+++++ Absoluter höchster Gewinn: " + neuesKonto.highest_absoluter_gewinn + " +++++");
		System.out.println("+++++ mit RSI Buy: " + neuesKonto.absoluter_winning_rsiBuy + " +++++");
		System.out.println("+++++ mit RSI Distanz: " + neuesKonto.absoluter_winning_rsiDistanz + " +++++");
		//System.out.println("+++++ mit RSI Sell: " + neuesKonto.absoluter_winning_rsiSell + " +++++");
		System.out.println("+++++ und Gain: " + neuesKonto.absoluter_winning_gain + " +++++");
		System.out.println("+++++ und Maxpairs: " + neuesKonto.absoluter_winning_maxpairs + " +++++");
		System.out.println("+++++ Tradingfees: " + neuesKonto.absoluter_winning_allTradingFees + " +++++");
		System.out.println("+++++ Ema Faktor: " + neuesKonto.absoluter_winning_emaFaktor + " +++++");
		System.out.println("+++++ Ema Limit Faktor: " + neuesKonto.absoluter_winning_emaFaktorLimit + " +++++");
		System.out.println("--- --- --- --- --- --- --- --- --- ---");
	}

}