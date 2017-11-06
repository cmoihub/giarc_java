package lab5;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by CraigBook on 2017-11-06.
 */
public class GuiBook extends JFrame{
    private static final long serialVersionUID = 281173259372442797L;
    JMenuItem add;
    JMenuItem display;
    JMenuItem save;
    //    private AddressBook book;
//    private AddressBookController controller;
//    private AddressBookView view;
    JMenu addressBook;
    JMenu buddyInfo;
    JMenuItem create;
    int id = 0;
    public GuiBook()
    {
        JFrame jf = new JFrame("AddressBook");
        jf.setSize(400, 400);
        JMenuBar bar = new JMenuBar();
        jf.setJMenuBar(bar);
        addressBook = new JMenu( "AddressBook" );
        bar.add( addressBook );
        create = new JMenuItem ( "Create" );
        addressBook.add(create);
        save = new JMenuItem("Save");
        addressBook.add(save);
        display = new JMenuItem ( "Display" );
        addressBook.add(display);
        buddyInfo = new JMenu( "BuddyInfo" );
        bar.add( buddyInfo );
        add = new JMenuItem ( "Add" );
        buddyInfo.add(add);

        jf.setVisible(true);
    }

    public JMenu getBuddyMenu(){
        return buddyInfo;
    }

    public JMenu getAddressBook(){
        return addressBook;
    }

    void _add(ActionListener listener) {
        add.addActionListener(listener);
    }

    void _display(ActionListener listener) {
        display.addActionListener(listener);
    }

    void _create(ActionListener listener) {
        create.addActionListener(listener);
    }

    void _save(ActionListener listener) {
        save.addActionListener(listener);
    }
}
