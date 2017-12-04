package lab8;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.text.html.HTML.Tag;

public class BuddyInfo {
	String name;
	String address;
	String greeting = "";
	int phone;
	int age;
//	Tag phoneTag;
//	Tag nameTag;
//	Tag addressTag;
	
	public BuddyInfo(String name, String address, int phone){
		this.name = name;
//		nameTag = new Tag("Name");
        this.address = address;
//        addressTag = new Tag("Address");
        this.phone = phone;
//        phoneTag = new Tag("Phone");
	}
	
	public BuddyInfo(String name, String address, int phone, int age){
		this(name,address,phone);
        this.age = age;
	}
	
	public BuddyInfo(BuddyInfo bi)
	{
		name = bi.getName();
		address = bi.getAddress();
		phone = bi.getPhone();
		age = bi.getAge();
	}
	
	public String toXML()
	{
		String s = "\t<BuddyInfo>\n";
		String s_ = "\t</BuddyInfo>";
		String nameTag = "\t\t<name>" + getName()  + "</name>\n";
		String addressTag = "\t\t<address>" + getAddress()  + "</address>\n";
		String phoneTag = "\t\t<phone>" + getPhone()  + "</phone>\n";
		return "\n" + s + nameTag + addressTag + phoneTag + s_;
	}
	
	public String toString(){
		return "" +"Name:" + getName() +
				" Address:" + getAddress() +
				" Phone:" + getPhone();	
	}
	
	public String splittable()
	{
		return "" + getName() + "," + getAddress() + "," + getPhone();
	}
	
	public boolean equals(Object o){
		if(this == o) return true;
		else if(o instanceof BuddyInfo)
		{
			BuddyInfo buddy = (BuddyInfo) o;
			return name.equals(buddy.name) && 
					address.equals(buddy.address)
					&& (Integer.compare(phone,buddy.phone)==0);
		}
		return false;
	}
	
	public static BuddyInfo Import(String s)
	{
		
		Pattern p = Pattern.compile(",");
		String[] strings = p.split(s);
		return new BuddyInfo(strings[0],strings[1],Integer.valueOf(strings[2]));
		
		/*
		Scanner scan = new Scanner(s).useDelimiter("[\\s\\$]+");
		BuddyInfo b = new BuddyInfo(scan.next(), scan.next(), scan.nextInt());	
		scan.close();
		return b;*/
	}
	
	public boolean over18()
	{
		if (age>18) return true;
		return false;
	}
	
	public String getGreeting()
	{
		return greeting;
	}
	
	public void setGreeting(String greeting)
	{
		this.greeting = greeting;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public static void main(String args[]){
		BuddyInfo bi = new BuddyInfo("ass", "butt", 6);
//		BuddyInfo b = BuddyInfo.Import(bi.toString());
		System.out.println(bi.toXML());
	}
}