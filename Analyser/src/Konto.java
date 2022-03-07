public class Konto {

	float kontostand;
	float ekPortfolio;
	float nowPortfolio;
	float gewinn;
	float anzahlcoins;
	int allholdingtime;
	int foliossize;
	int doneTrades;
	
	float highest_absoluter_gewinn;
	float highest_gewinn;
	float trading_volume;
	
	float winning_rsiBuy;
	float winning_gain;
	float winning_maxpairs;
	float winning_rsiDistanz;
	float winning_emaFaktor;
	float winning_emaFaktorLimit;
	float winning_trading_volume;
	
	float absoluter_gewinn;
	float absoluter_winning_rsiBuy;
	float absoluter_winning_gain;
	float absoluter_winning_maxpairs;
	float absoluter_winning_rsiDistanz;
	float absoluter_winning_ema;
	float absoluter_winning_emaFaktor;
	float absoluter_winning_emaFaktorLimit;
	float absoluter_trading_volume;
	
	float allTradingFees;
	float winning_allTradingFees;
	float absoluter_winning_allTradingFees;
	
	public Konto() {
		
		kontostand = -1;
		ekPortfolio  = 0;
		nowPortfolio = 0;
		gewinn = 0;
		anzahlcoins = 0;
		allholdingtime = 0;
		foliossize = 0;
		doneTrades = 0;
		
		highest_absoluter_gewinn = 0;
		absoluter_gewinn = 0;
		highest_gewinn = 0;
		winning_rsiBuy = 0;
		winning_gain = 0;
		winning_maxpairs = 0;
		absoluter_winning_rsiBuy = 0;
		absoluter_winning_gain = 0;
		absoluter_winning_maxpairs = 0;
		allTradingFees = 0;
		winning_allTradingFees = 0;
		absoluter_winning_allTradingFees = 0;
		winning_emaFaktorLimit = 0;
		winning_emaFaktor = 0;
		
		trading_volume = 0;
		winning_trading_volume = 0;
		absoluter_trading_volume = 0;
	}
	
	public void reset() {
		
		allholdingtime = 0;
		kontostand = 0;
		gewinn = 0;
		ekPortfolio = 0;
		nowPortfolio = 0;
		anzahlcoins = 0;
		allTradingFees = 0;
		allTradingFees = 0;
		trading_volume = 0;
		foliossize = 0;
		doneTrades = 0;
	}
	
}
