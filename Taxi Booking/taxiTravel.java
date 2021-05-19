// This class simulates the traveling time it takes 60 mins to go from a point to the next point.
// So the time in miliseconds = Math.abs(pp-dp)*60*60*1000 
//                                              60m 1m  1s
// we'll limit it to 60 seconds = 60000 ms

import java.lang.*;
class taxiTravel extends Thread{

	taxi t;
	// Constructor
	taxiTravel(taxi t){
		this.t = t;

	}
	public void run(){
		t.isAval = false;
		// When using threads always use exception handling!!
		try{

			System.out.println("Taxi id:"+t.id+" is assigned ");
			Thread.sleep(60000); // Thread sleeps for 60 sec simulating the traveling time
			t.isAval=true; // The thread waits for 60 sec and assigns the taxi as available 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
