package lab5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by CraigBook on 2017-11-06.
 */
public class AddressBookController{
    private AddressBook book;
    private AddressBookView view;
    private GuiBook gui;
    private int id = 0;

    public AddressBookController(AddressBook ab, GuiBook gui_, AddressBookView view_)
    {
        System.out.println("CREATE CONTROLLER");
        book = ab;
        gui = gui_;
        view = view_;
        gui._add(new AddListener());
        gui._create(new CreateListener());
        gui._display(new DisplayListener());
        gui._save(new SaveListener());
        view._delete(new DeleteListener());
        view._edit(new EditListener());
    }

    private class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            delete();
        }
    }

    private class SaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            save();
        }
    }

    private class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            add();
        }
    }

    private class CreateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            create();
        }
    }


    private class DisplayListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            display();
        }
    }

    public class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            edit();
        }
    }

    private void add() {
        System.out.println("Adding buddy info");
        String firstName = "";
        while(firstName == null || firstName == "")
        {
            firstName = (String) JOptionPane.showInputDialog(
                    view,
                    "Enter the first name\n",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
        }

        String lastName = "";
        while(lastName == null || lastName == "")
        {
            lastName = (String)JOptionPane.showInputDialog(
                    view,
                    "Enter the last name\n",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
        }

        book.AddBuddy(firstName, lastName, id);
        id++;
    }

    public void create(){
        System.out.println("Creating addressbook");
        book = new AddressBook();
    }

    public void save() {
        System.out.println("Saving addressbook");
        String s = book.toString();
        System.out.println(s);
        try {
            String labLocation = "M:\\SYSC3110\\Labs\\Lab5\\addressBookText.txt";
            String macLocation = "addressBookText.txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(macLocation));
            out.write(s);
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }

    public void display(){
        System.out.println("Displaying contact list");
        view.showList(book.ContactList);
    }

    public void delete(){
        System.out.println("Deleting item");
        int index = view.getList().getSelectedIndex();
        System.out.println(index);
        view.getListModel().remove(index);
    }

    public void edit(){
        System.out.println("Editing item");
        int index = view.getList().getSelectedIndex();
        String lastName = "";
        while(lastName == null || lastName == "")
        {
            lastName = (String)JOptionPane.showInputDialog(
                    view,
                    "Enter the last name\n",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");
        }
        book.editBuddy(index, lastName);
    }
}
