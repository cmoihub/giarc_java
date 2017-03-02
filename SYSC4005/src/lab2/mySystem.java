package lab2;

import java.io.*; 
import java.util.*;

public class mySystem {
	private ArrayList<Double> IATimes;

	private final int MAX_DEPARTURES = 100000;
	
	private Server server;
	private ArrayList<Event> eventList; 
	private double clock;

	private int qSize;
	private int departures;

	public mySystem() {
		this.server = new Server(); 
		this.eventList = new ArrayList<>();	 
		this.IATimes = new ArrayList<>();
		this.clock = 0;
		}
	
	public static void main (String [] args){
		mySystem m = new mySystem();
		m.run();		
	}
	
	public void run(){
		initialize();
		// while there is a next event
		while(!eventList.isEmpty()){  
			// take the first "future event"
			Event e = eventList.get(0);
			// take the first future event 
			// progress clock
			clock = e.getTime();
			/* more code to be added here */
			if(e.getType().equals("arrival")) // if the event is an
				arrive();
				
			
			else if (e.getType().equals("departure"))
				depart();
				
			eventList.remove(0);
			}
		}
	
	private void arrive(){
		qSize++;
		
		//if server is idle
		if(!server.isBusy()){  
			//set server busy
			server.setBusy(true);  
			// create new departure event
			Event ev = new Event("departure",server.getServiceTime()+clock);  
			int pos=-1;
			
			// insert new departure event in the approriate position
			for(Event event : eventList){ 
				if(event.getTime() < ev.getTime()) 
					pos = eventList.indexOf(event); 
				}
			eventList.add(pos+1,ev); 
			}
		// generate next arrival
		Event ev = new Event("arrival", getIATime()+clock);
		
		// if maximum number of arrivals is not reached, 
		//place the new arrival in the appropriate position in the event list
		if(ev.getTime()-clock > 0){
			int pos = -1;
			for(Event event : eventList){
				if(event.getTime() < ev.getTime())
					pos = eventList.indexOf(event);
				}
			eventList.add(pos+1,ev); 
			}
	}
	
	private void depart(){
		qSize--;
		departures++;
		//verify end of program
		if(departures == MAX_DEPARTURES){
			stop();
			return;
		}
		
		server.setBusy(false);
		if(qSize>0){
			Event ev = new Event("departure", server.getServiceTime()+clock);
			server.setBusy(true);
			int pos = -1;
			
			for (Event event: eventList){
				if(event.getTime()<ev.getTime())
					pos = eventList.indexOf(event);
			}
			eventList.add(pos+1, ev);
			}
	}
	
		
	private void stop() {
		// TODO Auto-generated method stub
	}

	private double getIATime() {
		if(IATimes.isEmpty())
			// if there are no more arrivals return -1
			return -1;  
		return IATimes.remove(0);
	}

	private void initialize() {
		importTimes();
		//the arrival of the first customer is set to 0
		Event e = new Event("arrival", 0);
		IATimes.remove(0); 
		eventList.add(e);
	}

	private void importTimes() {
		Scanner scaS,scaIA; 
		String path = "src/files/";
		String service = "serviceTimes-100K.txt";
		String interArrivals = "interArrivalTimes-100K.txt";
		FileReader frS=null; 
		FileReader frIA=null;
		try {
			frS = new FileReader(path + service);
			frIA = new FileReader(path + interArrivals); 
			}	catch(FileNotFoundException e){
				System.out.println("error opening file" + e); 
				}
		scaS = new Scanner(frS); 
		scaIA = new Scanner(frIA);
		
		ArrayList<Double> serviceTimes = new ArrayList<Double>();
		
		for (int i = 0; i < MAX_DEPARTURES; i++){ 
			serviceTimes.add(scaS.nextDouble());
			IATimes.add(scaIA.nextDouble()); 
			}
		server.setServiceTimes(serviceTimes);
		
		scaS.close();
		scaIA.close();
	}
}