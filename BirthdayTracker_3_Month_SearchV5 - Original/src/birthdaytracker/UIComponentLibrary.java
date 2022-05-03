package birthdaytracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UIComponentLibrary
{
    public static JButton CreateJButton(String name, int sizeX, int sizeY, int posX, int posY, ActionListener listener, Frame frame, SpringLayout layout)
    {
        JButton myButton = new JButton(name); //Creates button and sets text
        myButton.addActionListener(listener); //Adds action listener to register click events
        myButton.setPreferredSize(new Dimension(sizeX,sizeY)); //Sets button size
        layout.putConstraint(SpringLayout.WEST,myButton,posX, SpringLayout.WEST,  frame); //Sets button's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myButton,posY, SpringLayout.NORTH,  frame); //Sets button's Y Coordinates
        frame.add(myButton); //Adds button to frame
        return myButton; //Returns completed button to caller.
    }

    public static JTextField CreateAJTextField(int size, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JTextField myTextField = new JTextField(size); //Creates JTextField and sets size
        layout.putConstraint(SpringLayout.WEST,myTextField,posX, SpringLayout.WEST,  frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myTextField,posY, SpringLayout.NORTH,  frame);//Sets text field's Y Coordinates
        frame.add(myTextField); //Adds text field to frame
        return myTextField; //Returns completed text field to caller
    }

    public static JTextArea CreateAJTextArea(int rows, int columns, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JTextArea myTextArea = new JTextArea(rows, columns);
        layout.putConstraint(SpringLayout.WEST, myTextArea, posX, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, myTextArea, posY, SpringLayout.NORTH, frame);
        frame.add(myTextArea);
        return myTextArea;
    }



    public static JLabel CreateAJLabel(String text, int posX, int posY, int size, Frame frame, SpringLayout layout)
    {
        JLabel myLabel = new JLabel(text); //Creates label and sets text
        myLabel.setFont(new Font("Serif", Font.BOLD, size)); // uses Font class object, pass an int of size
        layout.putConstraint(SpringLayout.WEST,myLabel,posX, SpringLayout.WEST,  frame);//Sets labels's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myLabel,posY, SpringLayout.NORTH,  frame);//Sets label's Y Coordinates
        frame.add(myLabel); // Adds label to frame
        return myLabel; //Returns completed label to caller.
    }

    public static JScrollPane CreateAJScrollPane(JTextArea textArea, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JScrollPane scrollPane = new JScrollPane(textArea);
        layout.putConstraint(SpringLayout.WEST, scrollPane, posX, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, posY, SpringLayout.NORTH, frame);
        frame.add(scrollPane);
        return scrollPane;
    }

    public static void CreateInfoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static JComboBox CreateJComboBox(int posX, int posY, Frame frame, SpringLayout layout) {
        JComboBox comboBox = new JComboBox();
        layout.putConstraint(SpringLayout.WEST, comboBox, posX, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, comboBox, posY, SpringLayout.NORTH, frame);
        frame.add(comboBox);
        return comboBox;
    }
}

//
//
//    // Store what's read into variables
//    // Use an Array so that you can store many and press next or previous to cycle through them
//    String[] name, likes, dislikes, birthdayDay, birthdayMonth;
//    int numberOfEntries = 0;
//
//    // To read file
//    String file = "src/main/resources/BirthdayTracker.csv";
//    BufferedReader reader = null;
//    BufferedReader crunchifyBuffer = null;
//    String line = "";
//    // read and write file methods
//    private void readFile() {
//        try {
//            int i = 0; // use to count how many entries have been read per line
//            reader = new BufferedReader(new FileReader(file));
//            while ((line = reader.readLine()) != null) {
//
//                String[] row = line.split(",");
//                System.out.println(row[0]);
//
//                txtName.setText(row[0]);
//                txtLikes.setText(row[1]);
//                txtDislikes.setText(row[2]);
//                txtBirthdayDay.setText(row[3]);
//                txtBirthdayMonth.setText(row[4]);
//                i++;
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // display something if an error occurs
//
//            // close the FileReader after using it
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
