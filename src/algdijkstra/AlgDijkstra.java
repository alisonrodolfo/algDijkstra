/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algdijkstra;

/**
 *
 * @author aliso
 */
public class AlgDijkstra  extends Thread{
	
	Lock lock;	
	int id;
	
	public AlgDijkstra(Lock lock, int id){
		this.lock = lock;		
		this.id = id;
	}
	
	public void run()
	{
		while (true)
		{
			lock.requestCS(id);
			//Seção Crítica
			System.out.println("teste " + id);			
			//Seção Crítica
			lock.releaseCS(id);	
			//try {Thread.sleep(200 + id*500);} catch (InterruptedException e) {}
		}
	}
	
	public static void main (String[] args)
	{
		int nproc = 10;						//num de threads
		
		Lock lock = new LockImpl(nproc);
		
		for(int p = 0; p < nproc; p++)		//inicia threads
		{
			(new AlgDijkstra(lock, p)).start();
		}
	}
}
