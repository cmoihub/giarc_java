package lab8;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;


public class AddressBookTest {

	@Before
	public void setUp() throws Exception {
		AddressBook ab = new AddressBook();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		AddressBook ab = new AddressBook();
		ab.addBuddy(new BuddyInfo("sss","ssa",2000));
		ab.addBuddy(new BuddyInfo("jazz","sza",1000));
		ab.export("cmoi.txt");
		AddressBook addbook = ab;
		ab.Import("cmoi.txt");
		assertEquals(ab.toString(),addbook.toString());
		//fail("Not yet implemented");
	}
	
	@Test
	public void serializationTest(){
		AddressBook ab = new AddressBook();
		ab.addBuddy(new BuddyInfo("sss","...",111));
		ab.writeAddressBook("cmoi.txt");
//		File f = "cmoi.txt";
		
		AddressBook book = new AddressBook();
		book = book.readAddressBook("cmoi.txt");
		assertEquals(ab.toString(),book.toString());
		}

	@Test
	public void xmlTest(){
		AddressBook ab = new AddressBook();
		AddressBook e = null;
		BuddyInfo Mike = new BuddyInfo("123","456",789);
		BuddyInfo CMOI = new BuddyInfo("1234","56",789);
		BuddyInfo Just = BuddyInfo.Import("Arc,def,1");
		ab.addBuddy(CMOI);
		ab.addBuddy(Just);
		ab.addBuddy(Mike);
		try {
			ab.exportToXMLFile("new.xml");
			e = AddressBook.importFromXMLFileDOM(new File("new.xml"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertNotNull(e);
	}
}
