import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class StatsTrade implements BlockingQueue {
	
    float L0_gewinn;
    float L1_gewinn;
    float L2_gewinn;
    float L3_gewinn;
    float L4_gewinn;
    float L5_gewinn;
    float L6_gewinn;
    float L7_gewinn;
    float L8_gewinn;
    float L9_gewinn;
    float L10_gewinn;
    float L11_gewinn;
    float L12_gewinn;
    float L13_gewinn;
    float L14_gewinn;
    
    int L0_doneTrades;
    int L1_doneTrades;
    int L2_doneTrades;
    int L3_doneTrades;
    int L4_doneTrades;
    int L5_doneTrades;
    int L6_doneTrades;
    int L7_doneTrades;
    int L8_doneTrades;
    int L9_doneTrades;
    int L10_doneTrades;
    int L11_doneTrades;
    int L12_doneTrades;
    int L13_doneTrades;
    int L14_doneTrades;

    
    float gain;
    float emaFaktor;
    float emaFaktorLimit;
    float layerGewinn;
    float smma500;
    
    int maxPairs;
    int sollRsiBuy;
    int sollRsidownlimitBuy;
    int avrHolding;

    int istPairs;
    int dca1;
    
    public StatsTrade() {
        L0_gewinn = 0;
        L1_gewinn= 0;
        L2_gewinn= 0;
        L3_gewinn= 0;
        L4_gewinn= 0;
        L5_gewinn= 0;
        L6_gewinn= 0;
        L7_gewinn= 0;
        L8_gewinn= 0;
        L9_gewinn= 0;
        L10_gewinn= 0;
        L11_gewinn= 0;
        L12_gewinn= 0;
        L13_gewinn= 0;
        L14_gewinn= 0;
        
        L0_doneTrades= 0;
        L1_doneTrades= 0;
        L2_doneTrades= 0;
        L3_doneTrades= 0;
        L4_doneTrades= 0;
        L5_doneTrades= 0;
        L6_doneTrades= 0;
        L7_doneTrades= 0;
        L8_doneTrades= 0;
        L9_doneTrades= 0;
        L10_doneTrades= 0;
        L11_doneTrades= 0;
        L12_doneTrades= 0;
        L13_doneTrades= 0;
        L14_doneTrades= 0;
        
        gain= 0;
        emaFaktor= 0;
        emaFaktorLimit= 0;
        layerGewinn= 0;
        smma500= 0;
        
        maxPairs= 0;
        sollRsiBuy= 0;
        sollRsidownlimitBuy= 0;
        avrHolding= 0;

        istPairs= 0;
        dca1= 0;
    }
    
    public StatsTrade(float gewinn, float gainSell, float emaFaktor1, float emaFaktorLimit1, int maxPairs1, int sollRsiBuy1,
    		int sollRsidownlimitBuy1, int avrHolding1, int istPairs1, float layerGewinn1, float smma500_1, int dca1_1,
    		int L0_doneTrades1) {
    	
    	this.L0_gewinn = gewinn;
    	this.gain = gainSell;
    	this.emaFaktor = emaFaktor1;
    	this.emaFaktorLimit = emaFaktorLimit1;
    	this.maxPairs = maxPairs1;
    	this.sollRsiBuy = sollRsiBuy1;
    	this.sollRsidownlimitBuy = sollRsidownlimitBuy1;
    	this.avrHolding = avrHolding1;
    	this.istPairs = istPairs1;
    	this.layerGewinn = layerGewinn1;
    	this.smma500 = smma500_1;
    	this.dca1 = dca1_1;
    	
        L1_gewinn= 0;
        L2_gewinn= 0;
        L3_gewinn= 0;
        L4_gewinn= 0;
        L5_gewinn= 0;
        L6_gewinn= 0;
        L7_gewinn= 0;
        L8_gewinn= 0;
        L9_gewinn= 0;
        L10_gewinn= 0;
        L11_gewinn= 0;
        L12_gewinn= 0;
        L13_gewinn= 0;
        L14_gewinn= 0;
        
        L1_doneTrades= 0;
        L2_doneTrades= 0;
        L3_doneTrades= 0;
        L4_doneTrades= 0;
        L5_doneTrades= 0;
        L6_doneTrades= 0;
        L7_doneTrades= 0;
        L8_doneTrades= 0;
        L9_doneTrades= 0;
        L10_doneTrades= 0;
        L11_doneTrades= 0;
        L12_doneTrades= 0;
        L13_doneTrades= 0;
        L14_doneTrades= 0;
    	
    }
    
	@Override
	public Object remove() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object element() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean offer(Object e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void put(Object e) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean offer(Object e, long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object take() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int remainingCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int drainTo(Collection c) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int drainTo(Collection c, int maxElements) {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
