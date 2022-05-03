package birthdaytracker;

//import sun.org.mozilla.javascript.internal.UintMap;
//import sun.org.mozilla.javascript.internal.json.JsonParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class BirthdayTracker extends JFrame implements ActionListener, WindowListener {

    JButton btnExit, btnFirst, btnLast, btnNext, btnPrevious, btnFind, btnNew, btnSave, btnDelete, btnBinarySearch, btnMonthSearch, btnDisplay;
    JLabel lblTitle, lblTitleImage, lblName, lblLikes, lblDislikes, lblBirthdayDay, lblBirthdayMonth, lblBirthdayList, lblFind;
    JTextField txtName, txtLikes, txtDislikes, txtBirthdayDay, txtBirthdayMonth, txtFind, txtMonthSearch;
    JTextArea txtBirthdayList,txtAreaName, txtAreaLikes, txtAreaDislikes, txtAreaBirthday, txtAreaBirthMonth;
    JScrollPane scrollPane;
    JComboBox boxOfMonths;
    SpringLayout myLayout = new SpringLayout(); // use to setup the layout for the application, allows to play with position of objects

    // Declartion for read/write/display purposes
    int maxEntries = 100;
    int numberOfEntries = 0;
    int currentEntry = 0;

    //Declaration of one 2D array for storing the PC/IP data - array has maxEntries by 5 columns
    String[][] personInfo = new String[maxEntries][5];
    String[] sortArray = new String[maxEntries];
    String[] monthsArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    // constructor
    public BirthdayTracker() {
        this.setSize(600,500);
        this.setResizable(false);
        this.setLayout(myLayout); // set layout or one button will be size of full screen
        this.getContentPane().setBackground(Color.decode("#E6E6E6"));

        setTitle("Birthday Tracker");
        createJButtons();
        createJTextFields();
        createJLabels();
        createJComboBox();

        // create the buttons first then read/load, otherwise the JTextFields are null
        readFile();
        displayEntry(currentEntry);

        this.setVisible(true); // set at bottom

    }


    // Methods for buttons, labels and textfields
    private void createJButtons() {
        btnExit = UIComponentLibrary.CreateJButton("Exit", 125,40, 445, 405, this, this, myLayout);
        btnFirst = UIComponentLibrary.CreateJButton("|<", 45,30, 25, 238, this, this, myLayout);
        btnLast = UIComponentLibrary.CreateJButton(">|", 45,30, 166, 238, this, this, myLayout);
        btnNext = UIComponentLibrary.CreateJButton(">", 45,30, 119, 238, this, this, myLayout);
        btnPrevious = UIComponentLibrary.CreateJButton("<", 45,30, 72, 238, this, this, myLayout);
        btnFind = UIComponentLibrary.CreateJButton("Find", 65,25, 504, 55, this, this, myLayout);
        btnNew = UIComponentLibrary.CreateJButton("New", 144, 25, 425, 105, this, this, myLayout);
        btnSave = UIComponentLibrary.CreateJButton("Save", 144, 25, 425, 140, this, this, myLayout);
        btnDelete = UIComponentLibrary.CreateJButton("Delete", 144, 25, 425, 175, this, this, myLayout);
        btnBinarySearch = UIComponentLibrary.CreateJButton("Binary Search", 144, 25, 425, 280, this, this, myLayout);
        btnMonthSearch = UIComponentLibrary.CreateJButton("Birthday Month", 144, 25, 425, 310, this, this, myLayout);
    }
    private void createJTextFields() {
        txtName = UIComponentLibrary.CreateAJTextField(17,215, 82,this,myLayout);
        txtLikes = UIComponentLibrary.CreateAJTextField(17,215, 113,this,myLayout);
        txtDislikes = UIComponentLibrary.CreateAJTextField(17,215, 144,this,myLayout);
        txtBirthdayDay = UIComponentLibrary.CreateAJTextField(9,215, 175,this,myLayout);
        txtBirthdayMonth = UIComponentLibrary.CreateAJTextField(9,215, 206,this,myLayout);
        txtFind = UIComponentLibrary.CreateAJTextField(8,478, 32,this,myLayout); // 8 or 9 depending on computer, not sure why different computer makes the size different

        txtBirthdayList = UIComponentLibrary.CreateAJTextArea(8, 33, 25, 310, this, myLayout);
//        txtBirthdayList.setLineWrap(true); wrap lines
        scrollPane = UIComponentLibrary.CreateAJScrollPane(txtBirthdayList, 25, 310, this, myLayout);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // can have as needed or always for the scroll bar

        // Set font to bold for text area
        Font boldFont = new Font(txtBirthdayList.getFont().getName(), Font.BOLD, txtBirthdayList.getFont().getSize());
        txtBirthdayList.setFont(boldFont);
        txtBirthdayList.setText("Enter a month and press the \"Birthday Month\" button to find birthdays \nin a particular month");

    }
    private void createJLabels() {

//        lblTitle = UIComponentLibrary.CreateAJLabel("Birthday Calendar", 25, 25, 25, this, myLayout);
//        lblTitle.setForeground(Color.decode("#81AB67"));

        lblTitleImage = UIComponentLibrary.CreateAJLabel(null, 65,4,1, this,myLayout);
        lblTitleImage.setIcon(new ImageIcon("src/main/resources/birthdayTracker_Title2.png"));
        lblName = UIComponentLibrary.CreateAJLabel("Name:", 25, 80, 20, this, myLayout);
        lblName.setPreferredSize(new Dimension(185,25));
        lblName.setOpaque(true); // use Opaque to be able to see background of JLabel
        lblName.setBackground(Color.decode("#81AB67"));
        lblName.setForeground(Color.WHITE);
        lblName.setIcon(new ImageIcon("src/main/resources/nameIcon_3.png"));

        lblLikes = UIComponentLibrary.CreateAJLabel("Likes:", 25, 110, 20, this, myLayout);
        lblLikes.setPreferredSize(new Dimension(185,25));
        lblLikes.setOpaque(true);
        lblLikes.setBackground(Color.decode("#81AB67"));
        lblLikes.setForeground(Color.WHITE);
        lblLikes.setIcon(new ImageIcon("src/main/resources/likeIcon.png"));

        lblDislikes = UIComponentLibrary.CreateAJLabel("Dislikes:", 25, 140, 20, this, myLayout);
        lblDislikes.setPreferredSize(new Dimension(185,25));
        lblDislikes.setOpaque(true);
        lblDislikes.setBackground(Color.decode("#81AB67"));
        lblDislikes.setForeground(Color.WHITE);
        lblDislikes.setIcon(new ImageIcon("src/main/resources/dislikeIcon.png"));

        lblBirthdayDay = UIComponentLibrary.CreateAJLabel("Birthday Day:", 25, 170, 20, this, myLayout);
        lblBirthdayDay.setPreferredSize(new Dimension(185,25));
        lblBirthdayDay.setOpaque(true);
        lblBirthdayDay.setBackground(Color.decode("#81AB67"));
        lblBirthdayDay.setForeground(Color.WHITE);
        lblBirthdayDay.setIcon(new ImageIcon("src/main/resources/birthdayIcon.png"));

        lblBirthdayMonth = UIComponentLibrary.CreateAJLabel("Birthday Month:", 25, 200, 20, this, myLayout);
        lblBirthdayMonth.setPreferredSize(new Dimension(185,25));
        lblBirthdayMonth.setOpaque(true);
        lblBirthdayMonth.setBackground(Color.decode("#81AB67"));
        lblBirthdayMonth.setForeground(Color.WHITE);
        lblBirthdayMonth.setIcon(new ImageIcon("src/main/resources/birthdayIcon.png"));

        lblBirthdayList = UIComponentLibrary.CreateAJLabel("Birthday List:", 25, 280, 20, this, myLayout);
        lblBirthdayList.setPreferredSize(new Dimension(250,25));
        lblBirthdayList.setOpaque(true);
        lblBirthdayList.setBackground(Color.decode("#81AB67"));
        lblBirthdayList.setForeground(Color.WHITE);
        lblBirthdayList.setIcon(new ImageIcon("src/main/resources/listIcon.png"));

        lblFind = UIComponentLibrary.CreateAJLabel("Find:", 425, 30, 20, this, myLayout);
        lblFind.setPreferredSize(new Dimension(50,25));
        lblFind.setOpaque(true);
        lblFind.setBackground(Color.decode("#81AB67"));
        lblFind.setForeground(Color.WHITE);
    }
    private void createJComboBox() {
        boxOfMonths = UIComponentLibrary.CreateJComboBox(450, 340, this, myLayout);
        for (int i = 0; i < monthsArray.length; i++) {
            boxOfMonths.addItem(monthsArray[i]);
        }
    }
    // Drew a rectangle in the right place but not sure why everything disappearing
    // Something to do with this method being underneath this.setVisible(true);
//    public void paint(Graphics g){
//        g.drawRect(426,301,157,110);
//    }

    // read/write methods
    public void displayEntry(int index) {
        // *** Reference to the 5 columns of the 2D Array ***
        txtName.setText(personInfo[index][0]);
        txtLikes.setText(personInfo[index][1]);
        txtDislikes.setText(personInfo[index][2]);
        txtBirthdayDay.setText(personInfo[index][3]);
        txtBirthdayMonth.setText(personInfo[index][4]);
    }

    public void saveEntry(int index)
    {
        // *** Reference to the 5 columns of the 2D Array ***
        personInfo[index][0] = txtName.getText();
        personInfo[index][1] = txtLikes.getText();
        personInfo[index][2] = txtDislikes.getText();
        personInfo[index][3] = txtBirthdayDay.getText();
        personInfo[index][4] = txtBirthdayMonth.getText();
        writeFile();
    }

    public void readFile()
    {
        try
        {
            FileInputStream fstream = new FileInputStream("BirthdayTracker.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int i = 0;
            String line;
            while ((line = br.readLine()) != null)
            {
                // Hold what is read in a temporary string array
                String[] temp = line.split(",");
//                System.out.println(temp[0]);

                // *** Reference to the 5 columns of the 2D Array ***
                personInfo[i][0] = temp[0];
                personInfo[i][1] = temp[1];
                personInfo[i][2] = temp[2];
                personInfo[i][3] = temp[3];
                personInfo[i][4] = temp[4];
                i++;

            }
            numberOfEntries = i;
            in.close();
        }
        catch (Exception e)
        {
            System.err.println("Error Reading File: " + e.getMessage());
        }
    }

    public void writeFile()
    {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter("BirthdayTracker.txt"));

            // *** Reference to the 5 columns of the 2D Array ***
            for(int m = 0; m < numberOfEntries; m++){
                out.println(personInfo[m][0] +"," + personInfo[m][1] + "," + personInfo[m][2] + "," + personInfo[m][3] + "," + personInfo[m][4]);
            }
            out.close();
        }
        catch (Exception e)
        {
            System.err.println("Error Writing File: " + e.getMessage());
        }
    }

    // Logic for buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        // button first
        if(e.getSource() == btnFirst)
        {
            currentEntry = 0;
            displayEntry(currentEntry);
        }

        // button previous
        if(e.getSource() == btnPrevious)
        {
            if(currentEntry > 0)
            {
                currentEntry--;
                displayEntry(currentEntry);
            }
        }

        // button next
        if (e.getSource()== btnNext)
        {
            if(currentEntry < numberOfEntries - 1)
            {
                currentEntry++;
                displayEntry(currentEntry);
            }
        }

        // button last
        if(e.getSource() == btnLast)
        {
            currentEntry = numberOfEntries - 1;
            displayEntry(currentEntry);
        }

        // button new
        if (e.getSource() == btnNew) {
            if (numberOfEntries < maxEntries - 1) {
                numberOfEntries++;
                currentEntry = numberOfEntries -1;

                // Reference to the 5 columns in 2D Array
                personInfo[currentEntry][0] = "";
                personInfo[currentEntry][1] = "";
                personInfo[currentEntry][2] = "";
                personInfo[currentEntry][3] = "";
                personInfo[currentEntry][4] = "";
                displayEntry(currentEntry);
            }
        }

        // button save, error handling for if any fields is empty
        if (e.getSource() == btnSave) {
            if (txtName.getText().equalsIgnoreCase("")) {
                UIComponentLibrary.CreateInfoBox("Error, please enter a name.", "Error 201");
            }
            else if (txtLikes.getText().equalsIgnoreCase("")) {
                UIComponentLibrary.CreateInfoBox("Error, please enter likes.", "Error 201");
            }
            else if (txtDislikes.getText().equalsIgnoreCase("")) {
                UIComponentLibrary.CreateInfoBox("Error, please enter dislikes.", "Error 201");
            }
            else if (txtBirthdayDay.getText().equalsIgnoreCase("")) {
                UIComponentLibrary.CreateInfoBox("Error, please enter birthday.", "Error 201");
            }
            else if (txtBirthdayMonth.getText().equalsIgnoreCase("")) {
                UIComponentLibrary.CreateInfoBox("Error, please enter the month.", "Error 201");
            }
            else {
                saveEntry(currentEntry);
            }
        }

        // button delete
        if (e.getSource() == btnDelete) {
            for (int index = currentEntry; index < numberOfEntries; index++){

                personInfo[index][0] = personInfo[index + 1][0];
                personInfo[index][1] = personInfo[index + 1][1];
                personInfo[index][2] = personInfo[index + 1][2];
                personInfo[index][3] = personInfo[index + 1][3];
                personInfo[index][4] = personInfo[index + 1][4];

            }

            numberOfEntries--;

            if (currentEntry > numberOfEntries -1){
                currentEntry = numberOfEntries -1;
            }
            displayEntry(currentEntry);
        }

        // button find, has error handling for if the find field is empty, or if user enter an entry that doesn't exist
        if (e.getSource() == btnFind) {
            boolean found = false;
            int index = currentEntry;

            while (index < numberOfEntries && found == false) {
                // Reference to first column of 2D Array
                if (txtFind.getText().equals(""))
                {
                    UIComponentLibrary.CreateInfoBox("Error, please enter a valid name!", "Error 201");
                    break;
                }
                else if (personInfo[index][0].equalsIgnoreCase(txtFind.getText()) && currentEntry != index) { //
                    found = true;
                }
                index++;

            }

            if (!found) {
                index = 0;
                // run loop while hasn't been found
                while (index < numberOfEntries && found == false) {
                    // Reference to first column of 2D Array
                    if (personInfo[index][0].equalsIgnoreCase(txtFind.getText())) {
                        found = true;
                    }
                    index++;
                }
                UIComponentLibrary.CreateInfoBox("Error, no more entry with that name found.", "Error 205");
            }

            if (found) {
                currentEntry = index -1; // because in the loop above it increments one EVEN if it is found, so -1 to go back to the correct one
                displayEntry(currentEntry);
            }


        }

        // button binary search
        if (e.getSource() == btnBinarySearch)
        {
            for(int i = 0; i < numberOfEntries; i++)
            {
                sortArray[i] = personInfo[i][0];
            }
            Arrays.sort(sortArray, 0, numberOfEntries);// error before where it wasn't sorting because can't sort 2D array
            txtBirthdayList.setText("Below is the sorted names of the database in \nalphabetical order.\nIf you get a result of -1 it means the entry does not exist.");
            txtBirthdayList.append("\n-------------------------------------------------------------------------\n");
            for(int i = 0; i < numberOfEntries; i++)
            {
                txtBirthdayList.append(sortArray[i] + "\n");
            }

            int result = Arrays.binarySearch(sortArray, 0, numberOfEntries, txtFind.getText());
            txtBirthdayList.append("\nBinary Search result = " + result);
        }

        // button month search
        if (e.getSource() == btnMonthSearch) {
            String selectedMonth = (String) boxOfMonths.getSelectedItem();
            txtBirthdayList.setText(null);
            txtBirthdayList.setText("Birthdays for the month of: " + selectedMonth);
            txtBirthdayList.append("\n -----------------------------------------------------------------------------------------------------------");
            txtBirthdayList.append("\nName\tLikes\tDislikes\tBirthday Day\tBirthday Month");
            txtBirthdayList.append("\n -----------------------------------------------------------------------------------------------------------\n");

            for (int i = 0; i < numberOfEntries; i++) {
                if (personInfo[i][4].equalsIgnoreCase(selectedMonth)) {
                    txtBirthdayList.append(personInfo[i][0] + "\t" + personInfo[i][1] + "\t" + personInfo[i][2] + "\t" + personInfo[i][3] + "\t" + personInfo[i][4] + "\n");
                }
            }
        }

        // button exit
        if (e.getSource() == btnExit) {
            // Need an error message so when a user hasn't entered all the fields and exit it doesn't save
            if (txtName.getText().equalsIgnoreCase("") || txtLikes.getText().equalsIgnoreCase("")
                    || txtDislikes.getText().equalsIgnoreCase("") || txtBirthdayDay.getText().equalsIgnoreCase("")
                    || txtBirthdayMonth.getText().equalsIgnoreCase("")) {
                UIComponentLibrary.CreateInfoBox("Fields cannot be empty before exiting the program!", "Error 201");
            } else {
                writeFile();
                System.exit(0);
            }
        }
    }

    // window listeners, has read on window open, write on window close

    @Override
    public void windowOpened(WindowEvent e) {
        readFile();
        displayEntry(currentEntry);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        writeFile();
        System.exit(0); // So when the window is closed application automatically stops, otherwise you can close the window but application still running
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
