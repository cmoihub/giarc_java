package lab2;

//Robert Fernandez
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class mySystem {
	private ArrayList<Double> IATimes, IATimes_;
	private final int MAX_DEPARTURES = 100000;
	private Server server, server2;
	private ArrayList<Event> eventList; 
	private double clock;
	private int qSize, qSize_;
	private int departures, departures_;
	
	private HashMap<Integer, Integer> serverQueue = new HashMap<>();
	private HashMap<Integer, Integer> serverQueue2 = new HashMap<>();
	private double departTime, arrivalTime, departTime_, arrivalTime_ = 0;
	private int numOver,numOver_ = 0;
	private double avgArrivalTime, avgArrivalTime_, avgServiceTime, avgServiceTime_  = 0;
	private int numPackets, numPackets2 = 0;
	//private double IATime2 = 0;

	public mySystem() {
		this.server = new Server();
		this.server2 = new Server();
		this.eventList = new ArrayList<>();	 
		this.IATimes = new ArrayList<>();
		this.IATimes_ = new ArrayList<>();
		this.clock = 0;
		}
	
	public static void main (String [] args){
		mySystem m = new mySystem();
		m.run();		
	}
	
	void run(){
		initialize();
		// while there is a next event
		while(!eventList.isEmpty()){  
			// take the first "future event"
			Event e = eventList.get(0);	// take the first future event 
			// progress clock
			clock = e.getTime();
			/* more code to be added here */
			if (e.getType().equals("arrival")) { // if the event is an arrival
				arrivalTime = e.getTime();
				avgArrivalTime += arrivalTime;
				numPackets++;
				arrive();
			} else if (e.getType().equals("departure")) {
				departTime = e.getTime();
				avgServiceTime += departTime;
				depart();
				if (departTime - arrivalTime > 0.3) {
					numOver++;
				}
//				prevTime = clock;
			}
			
			else if (e.getType().equals("print")) {
				System.out.println("~~~~~~~~" + e.getTime() + "~~~~~~~~~~");
				eventList.forEach(event -> {
					if (event.getType().equals("arrival") ||
							event.getType().equals("departure")) {
						System.out.println(event.getType());
						System.out.println(event.getTime());
					}
				});				
				
				avgArrivalTime /= numPackets;
				avgServiceTime /= numPackets;
				System.out.println("arrival rate = " + 1/avgArrivalTime);
				System.out.println("service rate = " + 1/avgServiceTime);
				System.out.println((1/avgArrivalTime) / (1/avgServiceTime));
				
				System.out.println("is server busy? " + server.isBusy());
				avgArrivalTime = 0;
				avgServiceTime = 0;
				numPackets = 0;
			}
			eventList.remove(0);
		}
		run_();
	}
	
	void run_(){
		initialize_();
		while(!eventList.isEmpty()){
			Event e = eventList.get(0);
			// take the first future event 
			// progress clock
			clock = e.getTime();
			if(e.getType().equals("arrival_")){
				arrivalTime_ = e.getTime();
				avgArrivalTime_ += arrivalTime_;
				numPackets2++;
				arrive_();
	//			// if the event is an
			}
			else if (e.getType().equals("departure_")){
				departTime_ = e.getTime();
				avgServiceTime_ += departTime_;
				depart_();
				if (departTime_ - arrivalTime_ > 0.2) 
					numOver_++;
				//prevTime = clock;
			}
			else if (e.getType().equals("print_")) {
				System.out.println("~~~~~~~~" + e.getTime() + "~~~~~~~~~~");
				eventList.forEach(event -> {
					if (event.getType().equals("arrival_") ||
							event.getType().equals("departure_")) {
						System.out.print(event.getType() + ":");
						System.out.println(event.getTime());
					}
				});
				avgArrivalTime_ /= numPackets2;
				avgServiceTime_ /= numPackets2;
				System.out.println("arrival rate = " + 1/avgArrivalTime_);
				System.out.println("service rate = " + 1/avgServiceTime_);
				System.out.println((1/avgArrivalTime_) / (1/avgServiceTime_));
				
				System.out.println("is server busy? " + server2.isBusy());
				avgArrivalTime_ = 0;
				avgServiceTime_ = 0;
				numPackets2 = 0;
			}
		eventList.remove(0);
		}
		
		stop();
	}
	
	void arrive(){
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
	
	void depart(){
		if (serverQueue.containsKey(qSize)) {
			int value = serverQueue.get(qSize)+1;
			serverQueue.put(qSize, value);
		} else 
			serverQueue.put(qSize,1);
		
		
		qSize--;
		departures++;
		//verify end of program
		/*if(departures == MAX_DEPARTURES){
			stop();
			return;
		}*/
		
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
	
	void arrive_(){
		qSize_++;
		
		//if server is idle
		if(!server2.isBusy()){  
			//set server busy
			server2.setBusy(true);  
			// create new departure event
			Event ev = new Event("departure_",server2.getServiceTime()+clock);  
			int pos=-1;
			
			// insert new departure event in the approriate position
			for(Event event : eventList){ 
				if(event.getTime() < ev.getTime()) 
					pos = eventList.indexOf(event); 
				}
			eventList.add(pos+1,ev); 
			}
		// generate next arrival
		Event ev = new Event("arrival_", getIATime_()+clock);
		
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
	
	void depart_(){
		if (serverQueue2.containsKey(qSize_)) {
			int value = serverQueue2.get(qSize_)+1;
			serverQueue2.put(qSize_, value);
		} else 
			serverQueue2.put(qSize_,1);
		
		qSize_--;
		departures_++;
		//verify end of program
		/*if(departures_ == MAX_DEPARTURES){
			stop();
			return;
		}*/
		
		server2.setBusy(false);
		if(qSize_>0){
			Event ev = new Event("departure_", server2.getServiceTime()+clock);
			server2.setBusy(true);
			int pos = -1;
			
			for (Event event: eventList){
				if(event.getTime()<ev.getTime())
					pos = eventList.indexOf(event);
			}
			eventList.add(pos+1, ev);
			}
	}
		
	void stop() {
		System.out.println("Server queue\n" + serverQueue);
		System.out.println("Server queue2\n" + serverQueue2);
		
		double probability = (double)numOver / (double)MAX_DEPARTURES;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Probability of being over 0.3: " + df.format(probability * 100) + "%");
		probability = (double)numOver_ / (double)MAX_DEPARTURES;
		df = new DecimalFormat("#.##");
		System.out.println("Probability of being over 0.2: " + df.format(probability * 100) + "%");
	}
	

	double getIATime() {
		if(IATimes.isEmpty())
			// if there are no more arrivals return -1
			return -1;  
		return IATimes.remove(0);
	}
	
	double getIATime_() {
		if(IATimes_.isEmpty())
			// if there are no more arrivals return -1
			return -1;  
		return IATimes_.remove(0);
	}

	void initialize() {
		importTimes();
		//the arrival of the first customer is set to 0
		Event e = new Event("arrival", 0);
		Event e1 = new Event("print", 500);
		Event e2 = new Event("print", 5000);
		Event e3 = new Event("print", 10000);
		IATimes.remove(0); 
		eventList.add(e);
		eventList.add(e1);
		eventList.add(e2);
		eventList.add(e3);
	}
	
	void initialize_(){
		importTimes();
		Event e4 = new Event("arrival_", 0);
		Event e5 = new Event("print_", 500);
		Event e6 = new Event("print_", 5000);
		Event e7 = new Event("print_", 10000);
		IATimes_.remove(0);
		eventList.add(e4);
		eventList.add(e5);
		eventList.add(e6);
		eventList.add(e7);
	}

	void importTimes() {
		Scanner scaS,scaIA, scaS2; 
		String path = "src/files/";
		String service = "serviceTimes-100K.txt";
		String service2 = "serviceTimes2-100K.txt";
		String interArrivals = "interArrivalTimes-100K.txt";
		FileReader frS=null;
		FileReader frS2=null; 
		FileReader frIA=null;
		try {
			frS = new FileReader(path + service);
			frS2 = new FileReader(path + service2);
			frIA = new FileReader(path + interArrivals); 
			}	catch(FileNotFoundException e){
				System.out.println("error opening file" + e); 
				}
		scaS = new Scanner(frS); 
		scaS2 = new Scanner(frS2);
		scaIA = new Scanner(frIA);
		ArrayList<Double> serviceTimes = new ArrayList<>();
		ArrayList<Double> serviceTimes2 = new ArrayList<>();
		
		for (int i = 0; i < MAX_DEPARTURES; i++){ 
			serviceTimes.add(scaS.nextDouble());
			serviceTimes2.add(scaS2.nextDouble());
			Double ia = scaIA.nextDouble();
			IATimes.add(ia);
			IATimes_.add(ia);
			
			}
		server.setServiceTimes(serviceTimes);
		server2.setServiceTimes(serviceTimes2);
		
		scaS.close();
		scaS2.close();
		scaIA.close();
	}
}