package lab5;

/**
 * Created by CraigBook on 2017-11-06.
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        AddressBook book = new AddressBook();
        GuiBook gui = new GuiBook();
        AddressBookView view = new AddressBookView(book);
        AddressBookController controller = new AddressBookController(book, gui, view);
    }
}
