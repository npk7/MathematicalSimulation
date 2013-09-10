import java.util.*;
public class simNserve
{


    public static void main(String[] args)
    {
	double lambdaA = 58.0;
	double lambdaS = 20.0;
	double tEnd = 7.0;
	int nserv = 3;
	simulateNServers(lambdaA, lambdaS, tEnd, nserv);
    }

    public static void simulateNServers(double lambdaA, double lambdaS, double tEnd, int nserv)
    {
      Queue q = new Queue(); // sets up the Queue
      MyRandom r = new MyRandom(12342);  // sets up random number generator
	  boolean serverfree[] = new boolean[nserv];
	  double qArrival[] = new double[q.mx_q];
	  double tnc[]= new double[q.mx_q];
	  double tna=0.0, clock = 0.0;
	  int lnqueue = 0,ttserved = 0,i=0, k=3;
	  for(k=0;k<nserv;k++)
	  {
		  serverfree[k] = true;
	  }
	  System.out.println(nserv+" server(s) are free for "+tnc+" hours. Total = "+tnc);
	  for(clock=0.0;clock<=tEnd;clock = tna)
	  {
		  tna = clock + r.nextExponential(lambdaA);
		  clock = tna;
		  i++;
		  lnqueue++;
		  qArrival[i] = tna;
		  System.out.println(i+" Arrvl  " +lnqueue+ "  "+qArrival[i]);
		  q.put(tna);
		  for(k=0;k<nserv;k++)
		  {
			  if(serverfree[k] == true)
			  {
				  serverfree[k] = false;
				  q.get();
				  tnc[i] = clock+r.nextErlang(3,lambdaS);
				  System.out.println("check");
			  }
		  }
	  }
		 /* else
		  {
		  			serverfree[k] = false;
 					clock = tnc[i];
		  }*/

}
}


	  for(k = 0;k<3;k++)
		  {
			  if(serverfree[k] == true)
			  {
				  q.put(tna);
				  tnc1 = clock + r.nextErlang(3, lambdaS);
				  q.get();
				  System.out.println("guy came at "+tna+" has been served, waited "+(tnc1 - tna));
			  }
		  }

		  if(q.isEmpty() == false && r.nextDouble()<0.3)//&& r.nextDouble()<0.3
		  {
			  tnc1 = clock + r.nextErlang(3,lambdaS);
			  nserv--;
			  serverfree[k] = false;
			  double time = q.get();
			  ttserved++;
			  timediff = tnc[i] - qArrival[i];
			  //double timediff = qArrival[i] - q.list[q.next_on_q++];
			  i++;
			  System.out.println(i+" Compl  "+lnqueue+ "  " +tnc1);
			  if(nserv <= 0)
			  {
			  System.out.println("All Servers are busy");
			  q.get();
			  System.out.println("check");
		  	  }
			  System.out.println(nserv+" server(s) are free for "+tnc1+" hours. Total = "+tnc1*nserv);
			  System.out.println("Guy came at "+time+ " has served, waited:" + timediff);
			  double wait = clock - q.list[q.next_off_q];
		 	  if (wait>0.1)
		 	  {
				lnwait++;
			  }

		  }
		  clock++;

	   }

      int bal = lnqueue-ttserved;
      int bal1 = bal;
	  for(int j=0;j<bal;j++)
	  {
		  double time = q.get();
		  tnc[i] = clock + r.nextErlang(3,lambdaS);
		  System.out.println(i+" Compl  "+bal1+"  "+tnc[i]);
		  bal1--;
		  timediff = tnc[i] - time;
		  i++;
		  ttserved++;
		  System.out.println("Guy has been served, waited: "+ timediff);
	  }

      System.out.println("\n-----------------------------------------------------------\n");
      System.out.println("tt_arrivals \t\t:" +lnqueue);
      System.out.println("tt_served \t\t:" +ttserved);
	  System.out.println("tt_wait_time >6 mins\t:" +lnwait);
	  int Frac = (lnwait/lnqueue)*100;
	  System.out.println("Fraction >6 mins\t:" +(double)lnwait/lnqueue*100+"%");



	}



}





