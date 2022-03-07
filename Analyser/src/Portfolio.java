import java.util.Date;

public class Portfolio {

	int row;
	int tradeID;
	int dca_level;
	float avr_value;
	float eiWert;
	float vkWert;
	float coinMenge;
	float coinWert;
	float kontostandBeimKauf;
	int status; 				// 0 = bought 1 = sold
	
	int buyTimeUnix;
	int sellTimeUnix;
	int holdingtime;
	Date eiDatum;
	Date vkDatum;	
	
	Portfolio(){
		
		tradeID = 0;
		eiWert = 0;
		vkWert = 0;
		status = 0;
		dca_level = 0;
		avr_value = 0;
		
	}
	
	public double getKontostandBeimKauf() {
		return kontostandBeimKauf;
	}

	public void setKontostandBeimKauf(float kontostand) {
		this.kontostandBeimKauf = kontostand;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getCoinMenge() {
		return coinMenge;
	}

	public void setCoinMenge(float coinMenge) {
		this.coinMenge = coinMenge;
	}

	public float getCoinWert() {
		return coinWert;
	}

	public void setCoinWert(float coinWert) {
		this.coinWert = coinWert;
	}

	public String toString(float closePrice, Konto newKonto) {
		newKonto.allholdingtime = newKonto.allholdingtime + holdingtime;
		if (status != 0) {
		return "Row: "+ row + " Win " + ((getVkWert()*coinMenge)-(getEiWert()*coinMenge)) + " ID " + tradeID + " " + eiDatum + " S:" + getStatus() + " VK:" + getVkDatum() + " >> CK: " + getEiWert() + " CW: " + getCoinWert() + " CM: " + getCoinMenge() + " Acc a. buy: " + Math.round(getKontostandBeimKauf()*100)/100.0;
		} else {
			return "Row: "+ row + " Win " + ((closePrice*coinMenge)-(getEiWert()*coinMenge)) + " ID " + tradeID + " " + eiDatum + " S:" + getStatus() + " VK:" + getVkDatum() + " >> CK: " + getEiWert() + " CW: " + getCoinWert() + " CM: " + getCoinMenge() + " Acc a. buy: " + Math.round(getKontostandBeimKauf()*100)/100.0;
		}
	}

	public int getTradeID() {
		return tradeID;
	}

	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}

	public float getEiWert() {
		return eiWert;
	}

	public void setEiWert(float eiWert) {
		this.eiWert = eiWert;
	}

	public float getVkWert() {
		return vkWert;
	}

	public void setVkWert(float vkWert) {
		this.vkWert = vkWert;
	}

	public Date getEiDatum() {
		return eiDatum;
	}

	public void setEiDatum(Date eiDatum) {
		this.eiDatum = eiDatum;
	}

	public Date getVkDatum() {
		return vkDatum;
	}

	public void setVkDatum(Date vkDatum) {
		this.vkDatum = vkDatum;
	}
}
