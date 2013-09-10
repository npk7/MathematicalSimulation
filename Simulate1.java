import java.util.*;
public class Simulate1
{


    public static void main(String[] args)
    {
	double lambdaA = 19.0;
	double lambdaS = 20.0;
	double tEnd = 8.0;
	simulateSingleServer(lambdaA, lambdaS, tEnd);
    }

    public static void simulateSingleServer(double lambdaA, double lambdaS, double tEnd)
    {
      Queue q = new Queue(); // sets up the Queue
      MyRandom r = new MyRandom(12342);  // sets up random number generator
	  /* Declaring the variables required */
	  int lnqueue = 0;
	  int k = 3;
	  boolean serverfree =true;
	  double qArrival[] = new double[q.mx_q];
	  double tnc=0.0,tna=0.0,totalfree = 0.0;
	  double clock = 0.0;
	  int lnwait = 0;
	  int ttserved = 0,i=0;
	  double waitedppl = 0.0;
	  double timediff = 0.0;
	  System.out.println("\n\n# Event         ql        clock");
	  System.out.println("-----------------------------------------------------------\n");
	  /* The main loop from which the clock starts and runs till 8 hours */
	  for(clock=0.0;clock<tEnd;clock = tna)
	  {
		  if(tna<=tnc || serverfree==true) // Customer Arrival Event
		  {
		  System.out.println(i+" Arrvl  " +q.length()+ "  "+tna);
		  tna = clock + r.nextExponential(lambdaA);
		  clock = tna;
		  lnqueue++;
		  i++;
		  qArrival[i] = tna;
		  q.put(tna);
		  }
		  else
		  {
 	      clock = tnc;
		  serverfree = false;
		  }
		  if(q.isEmpty() == false && r.nextDouble()<0.3) // Service Completion Event
		  {
			  tnc = clock + r.nextErlang(3,lambdaS);
			  serverfree = false;
			  double time = q.get();
			  ttserved++;
			  timediff = tnc - qArrival[i];
			  i++;
			  System.out.println(i+" Compl  "+q.length()+ "  " +tnc);
			  double wait = q.list[q.next_off_q]-time;
			  System.out.println("Guy came at "+time+ " has served, waited:" + wait);
			  if(wait>0.006) //Customers waited for more than 6 minutes
		 	  {
				lnwait++;
			  } //end of if loop

		  } //end of service completion if loop
		  else if(q.isEmpty() == true)
		  {
			  double freetime = tna-q.list[q.next_off_q];
			  totalfree = totalfree+freetime;
			  System.out.println("Server free for "+freetime+" , ttserver free yet "+totalfree);
	   		  }
}//end of for loop

	  /* To serve the remaining customers after 8 hours has been completed  */
      int bal = lnqueue-ttserved;
      int bal1 = bal;
	  for(int j=0;j<bal;j++)
	  {
		  double time = q.get();
		  tnc = clock + r.nextErlang(3,lambdaS);
		  System.out.println(i+" Compl  "+q.length()+"  "+tnc);
		  bal1--;
		  timediff = tnc - time;
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





