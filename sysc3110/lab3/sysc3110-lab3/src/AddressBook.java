//import java.util.*;
public class AddressBook {
	private List<BuddyInfo> buddyInfo;
	
	public AddressBook() {
		this.buddyInfo = new ArrayList<BuddyInfo>();
	}
	
	public void addBuddy(BuddyInfo bi) {
		if(bi != null) {
			this.buddyInfo.add(bi);
		}
	}
	
	public BuddyInfo removeBuddy(int index) {
		if(index >= 0 && index < this.buddyInfo.size()) {
			return this.buddyInfo.remove(index);
		}
		return null;
	}
	
	public static void main (String args[]) {
		BuddyInfo buddy = new BuddyInfo();
		AddressBook ab = new AddressBook();
		ab.addBuddy(buddy);
		ab.removeBuddy(0);
	}
}
