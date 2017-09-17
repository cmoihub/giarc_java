
public class BuddyInfo {
	public String name;
	public String address;
	public int phoneNo;
	
	public BuddyInfo(){
		
	}

	public static void main(String[] args) {
		BuddyInfo bi = new BuddyInfo();
		bi.setName("Homer");
		System.out.println("Hello " + bi.getName());
	}

	public String getName() {
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

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
}