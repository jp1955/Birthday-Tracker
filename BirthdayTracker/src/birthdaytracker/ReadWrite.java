//package birthdaytracker;
//
////With a Multi-dimensional Array
//
//import java.awt.*;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
//import javax.swing.SpringLayout;
//
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.FileInputStream;
//import java.io.FileWriter;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//
//
///**
// * @author Mark O'Reilly
// */
//
//public class Main extends Frame implements ActionListener, WindowListener
//{
//    int maxEntries = 100;
//    int numberOfEntries = 0;
//    int currentEntry = 0;
//
//    //Declaration of one 2D array for storing the PC/IP data - array has maxEntries by 3 columns
//    String[][] PCInfo = new String[maxEntries][3];
//
//    Label lblPCName, lblPCID, lblIP, lblFind;
//    TextField txtPCName, txtPCID, txtIP, txtFind;
//    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast;
//
//    public static void main(String[] args)
//    {
//        Frame myFrame = new Main();
//        myFrame.setSize(470,250);
//        myFrame.setLocation(400, 200);
//        myFrame.setResizable(false);
//        myFrame.setVisible(true);
//    }
//
//    public Main()
//    {
//        setTitle("IP Address Database");
//        setBackground(Color.yellow);
//
//        SpringLayout myLayout = new SpringLayout();
//        setLayout(myLayout);
//
//        LocateLabels(myLayout);
//        LocateTextFields(myLayout);
//        LocateButtons(myLayout);
//
//        this.addWindowListener(this);
//    }
//
//
//    // ------------------------------------------------------------------------------------------
////
////    public void LocateLabels(SpringLayout myLabelLayout)
////    {
////        lblPCName = LocateALabel(myLabelLayout, lblPCName, "PC Name", 30, 25);
////        lblPCID = LocateALabel(myLabelLayout, lblPCID, "PC ID", 30, 50);
////        lblIP = LocateALabel(myLabelLayout, lblIP, "IP", 30, 75);
////        lblFind = LocateALabel(myLabelLayout, lblFind, "Search", 30, 120);
////    }
////
////    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
////    {
////        myLabel = new Label(LabelCaption);
////        add(myLabel);
////        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
////        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
////        return myLabel;
////    }
////
////    public void LocateTextFields(SpringLayout myTextFieldLayout)
////    {
////        txtPCName  = LocateATextField(myTextFieldLayout, txtPCName, 20, 130, 25);
////        txtPCID = LocateATextField(myTextFieldLayout, txtPCID, 20, 130, 50);
////        txtIP = LocateATextField(myTextFieldLayout, txtIP, 20, 130, 75);
////        txtFind = LocateATextField(myTextFieldLayout, txtFind, 20, 130, 120);
////    }
////
////    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
////    {
////        myTextField = new TextField(width);
////        add(myTextField);
////        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
////        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
////        return myTextField;
////    }
////
////    public void LocateButtons(SpringLayout myButtonLayout)
////    {
////        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 320, 25, 80, 25);
////        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 320, 50, 80, 25);
////        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 320, 75, 80, 25);
////        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 320, 100, 80, 25);
////        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 320, 170, 80, 25);
////        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 140, 170, 30, 25);
////        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 180, 170, 30, 25);
////        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 220, 170, 30, 25);
////        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 260, 170, 30, 25);
////    }
////
////    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h)
////    {
////        myButton = new Button(ButtonCaption);
////        add(myButton);
////        myButton.addActionListener(this);
////        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
////        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
////        myButton.setPreferredSize(new Dimension(w,h));
////        return myButton;
////    }
//
//
//    // ------------------------------------------------------------------------------------------
//
//    public void actionPerformed(ActionEvent e)
//    {
//        // BUTTON FIRST
//        if(e.getSource() == btnFirst)
//        {
//            currentEntry = 0;
//            displayEntry(currentEntry);
//        }
//
//        // BUTTON PREVIOUS
//        if(e.getSource() == btnPrev)
//        {
//            if(currentEntry > 0)
//            {
//                currentEntry--;
//                displayEntry(currentEntry);
//            }
//        }
//
//        // BUTTON NEXT
//        if (e.getSource()== btnNext)
//        {
//            if(currentEntry < numberOfEntries - 1)
//            {
//                currentEntry++;
//                displayEntry(currentEntry);
//            }
//        }
//
//        // BUTTON LAST
//        if(e.getSource() == btnLast)
//        {
//            currentEntry = numberOfEntries - 1;
//            displayEntry(currentEntry);
//        }
//
//        // BUTTON NEW
//        if(e.getSource() == btnNew)
//        {
//            if (numberOfEntries < maxEntries - 1)
//            {
//                numberOfEntries++;
//                currentEntry = numberOfEntries - 1;
//
//                // *** Reference to the 3 columns of the 2D Array ***
//                PCInfo[currentEntry][0] = "";
//                PCInfo[currentEntry][1] = "";
//                PCInfo[currentEntry][2] = "";
//                displayEntry(currentEntry);
//            }
//        }
//
//        // BUTTON SAVE
//        if(e.getSource() == btnSave)
//        {
//            saveEntry(currentEntry);
//        }
//
//        // BUTTON DELETE
//        if(e.getSource()== btnDel)
//        {
//            for (int index = currentEntry; index < numberOfEntries; index++)
//            {
//                // *** Reference to the 3 columns of the 2D Array ***
//                PCInfo[index][0] = PCInfo[index + 1][0];
//                PCInfo[index][1]  = PCInfo[index + 1][1];
//                PCInfo[index][2] = PCInfo[index + 1][2];
//            }
//            numberOfEntries--;
//            if (currentEntry > numberOfEntries - 1)
//            {
//                currentEntry = numberOfEntries - 1;
//            }
//            displayEntry(currentEntry);
//        }
//
//        // BUTTON FIND
//        if(e.getSource() == btnFind)
//        {
//            boolean found = false;
//            int index = 0;
//            while (index < numberOfEntries && found == false)
//            {
//                // *** Reference to the the first column of the 2D Array ***
//                if (PCInfo[index][0].equals( txtFind.getText()))
//                {
//                    found = true;
//                }
//                index++;
//            }
//            if (found)
//            {
//                currentEntry = index - 1;
//                displayEntry(currentEntry);
//            }
//        }
//
//        // BUTTON EXIT
//        if(e.getSource() == btnExit)
//        {
//            writeFile();
//            System.exit(0);
//        }
//
//    }
//
//
//    // ------------------------------------------------------------------------------------------
//
//    public void windowClosing(WindowEvent we)
//    {
//        writeFile();
//        System.exit(0);
//    }
//
//    public void windowIconified(WindowEvent we)
//    {
//    }
//
//    public void windowOpened(WindowEvent we)
//    {
//        readFile();
//        displayEntry(currentEntry);
//    }
//
//    public void windowClosed(WindowEvent we)
//    {
//    }
//
//    public void windowDeiconified(WindowEvent we)
//    {
//    }
//
//    public void windowActivated(WindowEvent we)
//    {
//    }
//
//    public void windowDeactivated(WindowEvent we)
//    {
//    }
//
//
//    // ------------------------------------------------------------------------------------------
//
//    public void displayEntry(int index)
//    {
//        // *** Reference to the 3 columns of the 2D Array ***
//        txtPCName.setText(PCInfo[index][0]);
//        txtPCID.setText(PCInfo[index][1]);
//        txtIP.setText(PCInfo[index][2]);
//    }
//
//    public void saveEntry(int index)
//    {
//        // *** Reference to the 3 columns of the 2D Array ***
//        PCInfo[index][0] = txtPCName.getText();
//        PCInfo[index][1] = txtPCID.getText();
//        PCInfo[index][2] = txtIP.getText();
//        writeFile();
//    }
//
//    public void readFile()
//    {
//        try
//        {
//            FileInputStream fstream = new FileInputStream("IPAddresses.txt");
//            DataInputStream in = new DataInputStream(fstream);
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            int i = 0;
//            String line;
//            while ((line = br.readLine()) != null)
//            {
//                String[] temp = line.split(",");
//
//                // *** Reference to the 3 columns of the 2D Array ***
//                PCInfo[i][0] = temp[0];
//                PCInfo[i][1] = temp[1];
//                PCInfo[i][2] = temp[2];
//                i++;
//            }
//            numberOfEntries = i;
//            in.close();
//        }
//        catch (Exception e)
//        {
//            System.err.println("Error Reading File: " + e.getMessage());
//        }
//    }
//
//    public void writeFile()
//    {
//        try
//        {
//            PrintWriter out = new PrintWriter(new FileWriter("IPAddresses_New.txt"));
//
//            // *** Reference to the 3 columns of the 2D Array ***
//            for(int m = 0; m < numberOfEntries; m++){
//                out.println(PCInfo[m][0] +"," + PCInfo[m][1] + "," + PCInfo[m][2]);
//            }
//            out.close();
//        }
//        catch (Exception e)
//        {
//            System.err.println("Error Writing File: " + e.getMessage());
//        }
//    }
//
//}
//
