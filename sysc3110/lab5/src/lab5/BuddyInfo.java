package lab5;

public class BuddyInfo {
    private String FirstName = "";
    private String LastName = "";
    private int Id = 0;

    public BuddyInfo(String firstName, String lastName, int id)
    {
        FirstName = firstName;
        LastName = lastName;
        Id = id;
    }

    public String GetFirstName()
    {
        return FirstName;
    }

    public void SetFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String GetLastName()
    {
        return LastName;
    }

    public void SetLastName(String lastName)
    {
        LastName = lastName;
    }

    public int GetId()
    {
        return Id;
    }

    public void SetId(int id)
    {
        Id = id;
    }

    @Override
    public String toString()
    {
        return "ID: " + GetId() + " FirstName: " + GetFirstName() + " LastName: " + GetLastName() + "\n";
    }

    public static void main(String[] args)
    {
		/*BuddyInfo buddy = new BuddyInfo("Jas");
		System.out.print("Hello " + buddy.GetFirstName());*/
    }

}
