package lab8;
import java.util.*;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import org.w3c.dom.*;

import java.io.*;

public class AddressBook implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static List<BuddyInfo> buddies;
	public AddressBook() {
//		tag = new HTML.Tag("addressBook");
		buddies = new ArrayList<>();	
	}
	
	
	public void addBuddy(BuddyInfo buddy)
	{
		if(buddy != null && buddies.contains(buddy)==false) buddies.add(buddy);
	}
	
	public String getBuddyInfo(){
		String s = "";
		for(BuddyInfo buddy:buddies){
			return s = "" + buddy.toString() + "\n";
		}
		return s;
	}
	
	public void removeBuddy(int index)
	{
		if(index>=0 && index <buddies.size())
			buddies.remove(index);
	}
	
	public int size()
	{
		return buddies.size();
	}
	
	static List<BuddyInfo> getBuddies() {
		return buddies;
	}

	public void clear()
	{
		buddies.removeAll(buddies);
	}
	
	public String toString()
	{
		String s = "";
		for (BuddyInfo bi : buddies)
		{
			s = bi.toString() + "\n";
		}
		return s;
	}
	
	public String toXML()
	{
//		String s = tag.open();
		String s = "<AddressBook>";
		for(BuddyInfo bi: buddies)
		{
			s += bi.toXML();
		}
		return s+"\n</AddressBook>";
	}
	
	public void export(String file)
	{
		System.out.println("saving the buddy info to " + file);
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(file));
			
			for (BuddyInfo b : buddies ){
				out.write(
						b.splittable() + "\n"
						);
			//out.write(this.toString());
			}
			out.close();
		} catch (IOException e1) { e1.printStackTrace(); }
	}
	
	public void exportToXMLFile(String file) throws Exception
	{
		System.out.println("saving the buddy info to " + file);
		BufferedWriter out = new BufferedWriter (new FileWriter(file));
		out.write(toXML());
		out.close();
	}
	
	public void Import(String file)
	{
		BufferedReader br;
		BuddyInfo bi;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					file)));
			String line;
			while ((line = br.readLine()) != null) {
				bi = BuddyInfo.Import(line);
				this.addBuddy(bi);
			}
		} catch (IOException e1) { e1.printStackTrace(); }	
	}
	
	public static AddressBook importFromXMLFileDOM(File f) throws Exception{
//		https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(f);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("BuddyInfo");
		AddressBook ab = new AddressBook ();
		for (int i = 0; i< nList.getLength(); i++){
			Node nNode = nList.item(i);
			Element eElement = (Element) nNode;
			String name = eElement.getElementsByTagName("name").item(0).getTextContent();
			String address = eElement.getElementsByTagName("address").item(0).getTextContent();
			int phone = Integer.parseInt(eElement.getElementsByTagName("phone").item(0).getTextContent());
			BuddyInfo bi = new BuddyInfo(name, address, phone);
			ab.addBuddy(bi);
		}
		return ab;		
	}
	
	public void writeAddressBook(String file) {
		try {
			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
			obj.writeObject(this);
			obj.close();
		}
		 catch (IOException e) { e.printStackTrace(); }
	}

	public AddressBook readAddressBook(String file) {
		try {
			ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file));
			AddressBook add = (AddressBook) obj.readObject();
			obj.close();
			return add;
		} 
		catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
		return null;
	}

	public boolean equals (Object o){
		 if (!(o instanceof AddressBook) || o ==null)
    		 return false;
    	 AddressBook ab = (AddressBook) o;
		return this.getBuddyInfo().equals(AddressBook.getBuddies());
	}
}