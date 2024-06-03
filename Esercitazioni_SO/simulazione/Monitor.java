package pkg;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	static private int maxBici = 30;
	static private int nBici = 10;
	static private int IN = 0;
	static private int OUT = 1;
	static private int A = 0;
	static private int B = 1;
	static private int C = 2;
	public int[] cassa = new int[3];
	private int[] biciDisp = new int[3];
	private int[][] singoliSospesi = new int[3][2];
	private int[][] coppieSospese = new int[3][2];
	private Lock lock = new ReentrantLock();
	private Condition[][] singoliCondition = new Condition[3][2];
	private Condition[][] coppieCondition = new Condition[3][2];
	
	public Monitor()
	{
		biciDisp[A] = nBici;
		biciDisp[B] = nBici;
		biciDisp[C] = nBici;
		cassa[A] = 0;
		cassa[B] = 0;
		cassa[C] = 0;
		singoliSospesi[A][IN] = 0;
		singoliSospesi[B][IN] = 0;
		singoliSospesi[C][IN] = 0;
		singoliSospesi[A][OUT] = 0;
		singoliSospesi[B][OUT] = 0;
		singoliSospesi[C][OUT] = 0;
		coppieSospese[A][IN] = 0;
		coppieSospese[B][IN] = 0;
		coppieSospese[C][IN] = 0;
		coppieSospese[A][OUT] = 0;
		coppieSospese[B][OUT] = 0;
		coppieSospese[C][OUT] = 0;
		coppieSospese[A][IN] = 0;
		coppieSospese[B][IN] = 0;
		coppieSospese[C][IN] = 0;
		coppieSospese[A][OUT] = 0;
		coppieSospese[B][OUT] = 0;
		coppieSospese[C][OUT] = 0;
		singoliCondition[A][IN] = lock.newCondition();
		singoliCondition[B][IN] = lock.newCondition();
		singoliCondition[C][IN] = lock.newCondition();
		singoliCondition[A][OUT] = lock.newCondition();
		singoliCondition[B][OUT] = lock.newCondition();
		singoliCondition[C][OUT] = lock.newCondition();
		coppieCondition[A][IN] = lock.newCondition();
		coppieCondition[B][IN] = lock.newCondition();
		coppieCondition[C][IN] = lock.newCondition();
		coppieCondition[A][OUT] = lock.newCondition();
		coppieCondition[B][OUT] = lock.newCondition();
		coppieCondition[C][OUT] = lock.newCondition();
	}
	
	public void richiediBiciS(int stazione) throws InterruptedException
	{
		lock.lock();
		
		while (biciDisp[stazione] == 0)
		{
			singoliSospesi[stazione][IN]++;
			singoliCondition[stazione][IN].await();
			singoliSospesi[stazione][IN]--;
		}
		
		biciDisp[stazione]--;
		cassa[stazione] = cassa[stazione] + 10;
		
		if (coppieSospese[stazione][OUT] != 0)
			coppieCondition[stazione][OUT].signal();
		if (singoliSospesi[stazione][OUT] != 0)
			singoliCondition[stazione][OUT].signal();
		
		lock.unlock();
	}
	
	public void richiediBiciC(int stazione) throws InterruptedException
	{
		lock.lock();
		
		while ((biciDisp[stazione] < 2) || singoliSospesi[stazione][IN] != 0)
		{
			coppieSospese[stazione][IN]++;
			coppieCondition[stazione][IN].await();
			coppieSospese[stazione][IN]--;
		}
		
		biciDisp[stazione] = biciDisp[stazione] - 2;
		cassa[stazione] = cassa[stazione] + 20;
		
		if (coppieSospese[stazione][OUT] != 0)
			coppieCondition[stazione][OUT].signal();
		if (singoliSospesi[stazione][OUT] != 0)
			singoliCondition[stazione][OUT].signal();
		
		lock.unlock();
	}
	
	public void cediBiciS(int stazione) throws InterruptedException
	{
		lock.lock();
		
		while (cassa[stazione] == 0 || coppieSospese[stazione][OUT] != 0 || biciDisp[stazione] == maxBici)
		{
			singoliSospesi[stazione][OUT]++;
			singoliCondition[stazione][OUT].await();
			singoliSospesi[stazione][OUT]--;
		}
		
		biciDisp[stazione]++;
		cassa[stazione] = cassa[stazione] - 10;
		
		if (singoliSospesi[stazione][IN] != 0)
			singoliCondition[stazione][IN].signal();
		if (coppieSospese[stazione][IN] != 0)
			coppieCondition[stazione][IN].signal();
		
		lock.unlock();
	}
	
	public void cediBiciC(int stazione) throws InterruptedException
	{
		lock.lock();
		
		while (cassa[stazione] < 20 || biciDisp[stazione] == (maxBici - 1))
		{
			coppieSospese[stazione][OUT]++;
			coppieCondition[stazione][OUT].await();
			coppieSospese[stazione][OUT]--;
		}
		
		biciDisp[stazione] = biciDisp[stazione] + 2;
		cassa[stazione] = cassa[stazione] - 20;
		
		if (singoliSospesi[stazione][IN] != 0)
			singoliCondition[stazione][IN].signal();
		if (coppieSospese[stazione][IN] != 0)
			coppieCondition[stazione][IN].signal();
		
		lock.unlock();
	}
	
	
}
