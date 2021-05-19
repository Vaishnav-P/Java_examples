//This will be the main class 

import java.util.*;

class taxiBooking extends Thread{

	static Scanner scan = new Scanner(System.in);
	static int bookid = 1;
	static ArrayList<taxi> taxies = new ArrayList<taxi>(); // Arraylist of taxi objects can be N numbers.

	// Constructor
	taxiBooking()
	{
		// We'll go with 4 taxies 
		for(int i = 1; i<=4;i++)
		{
			taxi t = new taxi(i); // taxi-1 taxi-2 tax-3 taxi-4 
			taxies.add(t); // Store the objects in the arraylist
		}

	}

	// The booking menu 
	static void bookMenu(){

		System.out.println("------Taxi Booking------");
		System.out.println(" (work with four taxi) ");
		System.out.println("1.Book Taxi  ");
		System.out.println("2.Taxi Details  ");
		System.out.println("3.Taxies Status ");
		System.out.println("4.Exit ");
		System.out.println("Enter your choice : ");
		int ch = scan.nextInt();

		switch(ch){

			case 1:{
				bookTaxi();
				bookMenu();
				break;
			}

			case 2:{

				taxiDetails();
				bookMenu();	
				break;
			}
			case 3:{

				taxiStatus();
				bookMenu();
				break;
			}
			case 4:{return;}

			default: {
				System.out.println("Enter a  valid choice:");
				bookMenu();
				break;
			}

		}

	}

	// taxi booking logic 
	static void bookTaxi(){

		System.out.println("Enter Customer ID: ");
		char cid = scan.next().charAt(0);
		System.out.println("Enter Pickup Point: ");
		char pp = scan.next().charAt(0);	//from
		System.out.println("Enter Drop Point: ");
		char dp = scan.next().charAt(0);	//to 
		System.out.println("Enter Pickup Time: ");
		int pt = scan.nextInt();
		int rej = 1; // For rejecting a booking if all taxies are busy.

		// for-each loop
		for(taxi t:taxies){
			if((t.isAval ==true)&&(t.currentPosition == pp )){ 

				t.assign(bookid,cid,pp,dp,pt);
				bookid++; //global variable 
				taxiTravel tt = new taxiTravel(t);
				tt.start();
				try{Thread.sleep(1000);}
				catch(Exception e){System.out.println(e);}
				return;

			}
			if(t.isAval == true) rej = 0; // At least one taxi is free

		}
		// All taxies are busy
		if(rej == 1){
			System.out.println(" Sorry no taxi is available at the moment please try again later ");
			return;
		}
		// other Scenarios -  nearest taxis, if tied taxis with min totalAmount 
		ArrayList<taxi> nearestTaxies = new ArrayList<taxi>();
		for(taxi t1:taxies){ // Find the nearest available taxi

			if(t1.isAval == true){
				if(Math.abs(t1.currentPosition-pp)== 1){nearestTaxies.add(0,t1);} // nearest taxi is added first
				else {nearestTaxies.add(t1);} // add taxies farther away
			}


		}

		if(nearestTaxies.size()==1){ // only one nearest taxi -- Easiest case 
			nearestTaxies.get(0).assign(bookid,cid,pp,dp,pt);
			bookid++;
			taxiTravel tt = new taxiTravel(nearestTaxies.get(0));
			tt.start();
			try{Thread.sleep(1000);} // Currently executing thread sleeps for a second
			catch(Exception e){System.out.println(e);}
			return;
		}
		else if((pp=='A' || pp == 'F') && (Math.abs(nearestTaxies.get(0).currentPosition - pp) == 1)){
			nearestTaxies.get(0).assign(bookid,cid,pp,dp,pt);
			bookid++;
			taxiTravel tt = new taxiTravel(nearestTaxies.get(0));
			tt.start();
			try{Thread.sleep(1000);}
			catch(Exception e){System.out.println(e);}
			return;

		}	// Tougher Cases --> Tie and min totalAmount 
		else{

				int min = Math.abs(nearestTaxies.get(0).currentPosition -pp);
				int tie = 0,loc = 0;
				// Find the minimum distance we are assuming first taxi in the least is nearest and then we check for other taxies which are closer
				for(int i=1;i<nearestTaxies.size();i++){
					if(min > Math.abs(nearestTaxies.get(i).currentPosition - pp )){
						min = Math.abs(nearestTaxies.get(i).currentPosition - pp );
						loc = i; // location of the nearest taxi
					}
					else{	// meaning taxi at [0] is the nearest remove the rest
							nearestTaxies.remove(i);
					} 
					if(min == Math.abs(nearestTaxies.get(i).currentPosition - pp )){ // In case of ties 
						tie++;
					}
				}
				if(tie == 0){ // No tie then assign the nearest taxi
					nearestTaxies.get(0).assign(bookid,cid,pp,dp,pt);
					bookid++;
					taxiTravel tt = new taxiTravel(nearestTaxies.get(0));
					tt.start();
					try{Thread.sleep(1000);}
					catch(Exception e){System.out.println(e);}
					return;

				}
				else{  // In case of ties find taxi with min totalEarning 

					double minAmt = nearestTaxies.get(0).totalEarning; 
					// Assuming taxi at [0] has the lowest totalEarning, find the lowest 
					int minLoc = 0;
					for(int i=1; i<nearestTaxies.size();i++){
						if(minAmt > nearestTaxies.get(i).totalEarning){
							minAmt = nearestTaxies.get(i).totalEarning;
							minLoc = i; // position of taxi with lowest totalEarning
						}

					}

					nearestTaxies.get(minLoc).assign(bookid,cid,pp,dp,pt);
					bookid++;
					taxiTravel tt = new taxiTravel(nearestTaxies.get(minLoc));
					tt.start();
					try{Thread.sleep(1000);}
					catch(Exception e){System.out.println(e);}
					return;

				}	
		
		}
	}

	static void taxiDetails(){

		for(taxi t:taxies){

			System.out.println(" ");
			System.out.print("Taxi id "+t.id);
			System.out.print("\t\t Total Earnings "+t.totalEarning);
			System.out.println(" ");
			System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
			for(int i=0;i<t.bookingId.size();i++){
				System.out.print(t.bookingId.get(i));
				System.out.print("      "+t.custId.get(i));
				System.out.print("      "+t.from.get(i));
				System.out.print("      "+t.to.get(i));
				System.out.print("      "+t.pickupTime.get(i));
				System.out.print("      "+t.dropTime.get(i));
				System.out.print("      "+t.amount.get(i));
			}
			System.out.println(" ");

		}

	}
	static void taxiStatus(){
		for(taxi t:taxies){
			if(t.isAval == true)
				System.out.println("Taxi id: "+t.id+" is available");
			else
				System.out.println("Taxi id: "+t.id+" is unavailable");

		}
	}

	public static void main(String args[]){

		taxiBooking tb = new taxiBooking();
		tb.bookMenu();
	}


}