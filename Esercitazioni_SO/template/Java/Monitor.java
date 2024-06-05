package pkg;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	static private int nPostiStandard = 10;
	static private int nPostiMaxi = 10;
	static private int IN = 1;
	static private int OUT = 0;
	static private int S = 1;
	static private int M = 0;
	private int nPostiLiberiS = nPostiStandard;
	private int nPostiLiberiM = nPostiMaxi;
	private int[] Elemento3Sospesi = new int[2];
	private int[] Elemento1Sospesi = new int[2];
	private int[] Elemento2Sospesi = new int[2];
	private int[] Elemento3Canale = new int[2];
	private int[] Elemento1Canale = new int[2];
	private int[] Elemento2Canale = new int[2];
	private Lock lock = new ReentrantLock();
	private Condition[] Elemento1Cond = new Condition[2];
	private Condition[] Elemento2Cond = new Condition[2];
	private Condition[] Elemento3Cond = new Condition[2];
	
	public Monitor()
	{
		Elemento3Sospesi[IN] = 0;
		Elemento3Sospesi[OUT] = 0;
		Elemento1Sospesi[IN] = 0;
		Elemento1Sospesi[OUT] = 0;
		Elemento2Sospesi[IN] = 0;
		Elemento2Sospesi[OUT] = 0;
		Elemento3Canale[IN] = 0;
		Elemento3Canale[OUT] = 0;
		Elemento1Canale[IN] = 0;
		Elemento1Canale[OUT] = 0;
		Elemento2Canale[IN] = 0;
		Elemento2Canale[OUT] = 0;
		Elemento3Cond[IN] = lock.newCondition();
		Elemento3Cond[OUT] = lock.newCondition();
		Elemento2Cond[IN] = lock.newCondition();
		Elemento2Cond[OUT] = lock.newCondition();
		Elemento1Cond[IN] = lock.newCondition();
		Elemento1Cond[OUT] = lock.newCondition();
	}
	
	public void entraElemento1(int direction, int tipo) throws InterruptedException
	{
		lock.lock();
		
		while ((nPostiLiberiS == 0 && nPostiLiberiM == 0) 
				|| (Elemento3Canale[IN] != 0 || Elemento3Canale[OUT] != 0) 
				|| (Elemento2Canale[(1-direction)] != 0) || (Elemento3Sospesi[direction] != 0)
				|| (Elemento2Sospesi[direction] != 0) || (Elemento1Canale[direction] != 0)
				|| (Elemento2Canale[direction] != 0))
		{
			Elemento1Sospesi[direction]++;
			Elemento1Cond[direction].await();
			Elemento1Sospesi[direction]--;
		}
		
		/*if (nPostiLiberiS != 0)
			nPostiLiberiS--;
		else
			nPostiLiberiM--;*/
		
		Elemento1Canale[direction]++;
		
		if (direction == OUT)
		{
			if (tipo == M)
				nPostiLiberiM++;
			else
				nPostiLiberiS++;
		}
			
		lock.unlock();
	}
	
	public int esceElemento1(int direction) throws InterruptedException
	{
		int tipo = 0;
		
		lock.lock();
		
		if (direction == IN)
		{
			if (nPostiLiberiS != 0)
			{
				nPostiLiberiS--;
				tipo = S;
			}
			else
			{
				nPostiLiberiM--;
				tipo = M;
			}
		}
		Elemento1Canale[direction]--;
		
		if (Elemento3Sospesi[IN] != 0)
			Elemento3Cond[IN].signal();
		if (Elemento3Sospesi[OUT] != 0)
			Elemento3Cond[OUT].signal();
		if (Elemento2Sospesi[IN] != 0)
			Elemento2Cond[IN].signal();
		if (Elemento2Sospesi[OUT] != 0)
			Elemento2Cond[OUT].signal();
		if (Elemento1Sospesi[IN] != 0)
			Elemento1Cond[IN].signal();
		if (Elemento1Sospesi[OUT] != 0)
			Elemento1Cond[OUT].signal();

		lock.unlock();
		
		return tipo;
	}
	
	public void esceElemento2(int direction) throws InterruptedException
	{		
		lock.lock();
		
		if (direction == IN)
			nPostiLiberiM--;
		Elemento2Canale[direction]--;
		
		if (Elemento3Sospesi[IN] != 0)
			Elemento3Cond[IN].signal();
		if (Elemento3Sospesi[OUT] != 0)
			Elemento3Cond[OUT].signal();
		if (Elemento2Sospesi[IN] != 0)
			Elemento2Cond[IN].signal();
		if (Elemento2Sospesi[OUT] != 0)
			Elemento2Cond[OUT].signal();
		if (Elemento1Sospesi[IN] != 0)
			Elemento1Cond[IN].signal();
		if (Elemento1Sospesi[OUT] != 0)
			Elemento1Cond[OUT].signal();


		lock.unlock();
	}
	
	public void entraElemento2(int direction) throws InterruptedException
	{
		lock.lock();
		
		while ((nPostiLiberiM == 0) 
				|| (Elemento3Canale[IN] != 0 || Elemento3Canale[OUT] != 0) 
				|| (Elemento2Canale[(1-direction)] != 0) || (Elemento3Sospesi[direction] != 0)
				|| (Elemento2Sospesi[direction] != 0) || (Elemento2Canale[direction] != 0))
		{
			Elemento1Sospesi[direction]++;
			Elemento1Cond[direction].await();
			Elemento1Sospesi[direction]--;
		}
		
		/*if (nPostiLiberiS != 0)
			nPostiLiberiS--;
		else
			nPostiLiberiM--;*/
		
		Elemento2Canale[direction]++;
		
		if (direction == OUT)
			nPostiLiberiM++;
			
		lock.unlock();
	}
	
	public void entraElemento3(int direction) throws InterruptedException
	{
		lock.lock();
		
		while ((Elemento3Canale[IN] != 0
				|| Elemento3Canale[OUT] != 0))
		{
			Elemento3Sospesi[direction]++;
			Elemento3Cond[direction].await();
			Elemento3Sospesi[direction]--;
		}
		
		Elemento3Canale[direction]++;
			
		lock.unlock();
	}
	
	public void esceElemento3(int direction) throws InterruptedException
	{		
		lock.lock();
		
		Elemento3Canale[direction]--;
		
		if (Elemento3Sospesi[IN] != 0)
			Elemento3Cond[IN].signal();
		if (Elemento3Sospesi[OUT] != 0)
			Elemento3Cond[OUT].signal();
		if (Elemento2Sospesi[IN] != 0)
			Elemento2Cond[IN].signalAll();
		if (Elemento2Sospesi[OUT] != 0)
			Elemento2Cond[OUT].signalAll();
		if (Elemento1Sospesi[IN] != 0)
			Elemento1Cond[IN].signalAll();
		if (Elemento1Sospesi[OUT] != 0)
			Elemento1Cond[OUT].signalAll();

		lock.unlock();
	}
}
