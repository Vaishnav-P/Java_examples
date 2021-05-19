// Taxi class we need ArrayList to store cutomerId,bookingId,amt,from,to,droptime,pickuptime
import java.util.*;
class taxi extends Thread{

	int id; // Taxi id --> 1,2,3,4.....
	char currentPosition = 'A';
	double totalEarning = 0.0;
	ArrayList<Integer> bookingId = new ArrayList<Integer>();
	ArrayList<Character>custId = new ArrayList<Character>();
	ArrayList<Character> from = new ArrayList<Character>();
	ArrayList<Character> to = new ArrayList<Character>();
	ArrayList<Integer> pickupTime = new ArrayList<Integer>();
	ArrayList<Integer> dropTime = new ArrayList<Integer>();
	ArrayList<Double> amount = new ArrayList<Double>(); 
	boolean isAval = true; // specifies whether the taxi is free or not 

	// Constructor
	taxi(int id){ 
		this.id = id; 
	}

	void assign(int bookId,char cid,char pp,char dp,int pt){
		bookingId.add(bookId);
		custId.add(cid);
		from.add(pp);
		to.add(dp);
		pickupTime.add(pt);
		dropTime.add(pt+Math.abs(pp-dp)); // total time take for the journey 
		amount.add((double)100+((15*Math.abs(pp-dp))-5)*10); // 100 minimum for first 5km then 10 for every 5km
		totalEarning+=(double)100+((15*Math.abs(pp-dp))-5)*10;
		System.out.println("Amount to pay :"+ (100+((15*Math.abs(pp-dp))-5)*10));
		this.currentPosition = dp ; // Updates the currentPosition with drop point

	}


}

// Each Taxi object will have all these ArrayList and variables each taxi stores their earnings and trip details.