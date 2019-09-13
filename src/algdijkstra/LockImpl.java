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
public class LockImpl implements Lock{
	
	volatile int nproc, turn;
	volatile int[] status;
	
	public LockImpl(int n)
	{
		nproc = n;							//numero de processos
		turn = 0;							//vez inicial de quem
		status = new int[n];				//cria array de status para n procesos
		for (int i = 0; i < n; i++)			//seta os status todos para 0 na inicialização
		{
			status[i] = 0;
		}
	}
	
	public void requestCS(int id)
	{
		int j;
		
		do
		{
			status[id] = 1; 							//querendo entrar
			while (turn != id)
				if (status[turn] == 0) turn = id;		
			status[id] = 2;								//entrou
			j = 0;
			while((j < nproc) && ((j == id) || (status[j] != 2))) //varre todos os processos, verificando se a unica q entroua te agora é a atual
				j++;
		} while (j != nproc);			//se nao for a unica, volta ao começo
		
	}
	
	public void releaseCS(int id)
	{		
		status[id] = 0;				//thread atual ta feita
	}
}
