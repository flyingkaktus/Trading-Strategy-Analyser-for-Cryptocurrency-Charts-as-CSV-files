import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadData implements BlockingQueue {

	int g_1;
	int sr_1;
	int sollRsidownlimitBuy;
	int maxpairs;
	int ema_u;
	int ema_o;
	int dca1range;
	int smma;

	
	public void setThreadData(int g_1, int sr_1, int sollRsidownlimitBuy, int maxpairs, int ema_u, int ema_o, int dca1range, int smma) {
		this.g_1 = g_1;
		this.sr_1 = sr_1;
		this.sollRsidownlimitBuy = sollRsidownlimitBuy;
		this.maxpairs = maxpairs;
		this.ema_u = ema_u;
		this.ema_o = ema_o;
		this.dca1range = dca1range;
		this.smma = smma;
	}
	
	public ThreadData(int g_1, int sr_1, int sollRsidownlimitBuy, int maxpairs, int ema_u, int ema_o, int dca1range, int smma) {
		this.g_1 = g_1;
		this.sr_1 = sr_1;
		this.sollRsidownlimitBuy = sollRsidownlimitBuy;
		this.maxpairs = maxpairs;
		this.ema_u = ema_u;
		this.ema_o = ema_o;
		this.dca1range = dca1range;
		this.smma = smma;
	}
	
	public int getG_1() {
		return g_1;
	}

	public void setG_1(int g_1) {
		this.g_1 = g_1;
	}

	public ThreadData() {
		// TODO Auto-generated constructor stub
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
