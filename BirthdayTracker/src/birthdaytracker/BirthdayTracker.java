package birthdaytracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BirthdayTracker extends JFrame implements ActionListener {

    JButton btnExit, btnFirst, btnLast, btnNext, btnPrevious, btnFind, btnNew, btnSave, btnDelete;
    JLabel lblTitle, lblName, lblLikes, lblDislikes, lblBirthdayDay, lblBirthdayMonth, lblBirthdayList, lblFind;
    JTextField txtName, txtLikes, txtDislikes, txtBirthdayDay, txtBirthdayMonth, txtBirthdayList, txtFind;
    SpringLayout myLayout = new SpringLayout(); // use to setup the layout for the application, allows to play with position of objects

    public BirthdayTracker() { // constructor
        this.setSize(600,500);
        this.setResizable(false);
        this.setLayout(myLayout); // set layout or one button will be size of full screen

        createJButtons();
        createJTextFields();
        createJLabels();


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
    }

    private void createJTextFields() {
        txtName = UIComponentLibrary.CreateAJTextField(17,215, 82,this,myLayout);
        txtLikes = UIComponentLibrary.CreateAJTextField(17,215, 113,this,myLayout);
        txtDislikes = UIComponentLibrary.CreateAJTextField(17,215, 144,this,myLayout);
        txtBirthdayDay = UIComponentLibrary.CreateAJTextField(9,215, 175,this,myLayout);
        txtBirthdayMonth = UIComponentLibrary.CreateAJTextField(9,215, 206,this,myLayout);
        txtFind = UIComponentLibrary.CreateAJTextField(8,478, 32,this,myLayout);

    }
    private void createJLabels() {
        lblTitle = UIComponentLibrary.CreateAJLabel("Birthday Calendar", 25, 25, 25, this, myLayout);
        lblTitle.setForeground(Color.decode("#81AB67"));

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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
