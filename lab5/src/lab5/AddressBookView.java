package lab5;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by CraigBook on 2017-11-06.
 */
public class AddressBookView extends JFrame implements Observer {
    private static final long serialVersionUID = -3343910527353248785L;
    private AddressBook book;
    private JList<BuddyInfo> list;
    private JFrame frame;
    private AddressBookController controller;
    private BuddyInfo selectedValue;
    DefaultListModel<BuddyInfo> listModel;
    JMenu buddyInfo;
    public JMenuItem delete;
    public JMenuItem edit;

    public AddressBookView(AddressBook ab)
    {
        System.out.println("CREATE VIEW");
        book = ab;
//        controller = new AddressBookController(book, this);
//        frame = new JFrame();
        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        buddyInfo = new JMenu( "BuddyInfo" );
        bar.add( buddyInfo );
        delete = new JMenuItem ( "Delete" );
//        item.addActionListener(controller);
        buddyInfo.add(delete);
        edit = new JMenuItem ( "Edit" );
//        item.addActionListener(controller);
        buddyInfo.add(edit);
        System.out.println("CREATE LIST");
//        showList(book.ContactList);
    }

    void _delete(ActionListener listener) {
        delete.addActionListener(listener);
    }

    void _edit(ActionListener listener) {
        edit.addActionListener(listener);
    }

    public void showList(DefaultListModel<BuddyInfo> entries)
    {
         listModel = entries;

        //create the list
        list = new JList<BuddyInfo>(listModel);
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if(!e.getValueIsAdjusting()) {
                    selectedValue = (BuddyInfo) list.getSelectedValue();
//                    System.out.println(selectedValue.toString());
                }
            }
        });

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        this.add(list);
        JPanel panel = new JPanel();
        panel.add(list);
        this.add(panel);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("JList Example");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JList getList(){
        return list;
    }

    public DefaultListModel<BuddyInfo> getListModel(){
        return listModel;
    }



    @Override
    public void update(Observable arg0, Object arg1)
    {
        System.out.println("UPDATING");

    }
}
