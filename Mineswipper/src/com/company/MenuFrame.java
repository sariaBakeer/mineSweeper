package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuFrame extends JFrame {


    JButton b1;
    JButton b2;
    JLabel label;
    JPanel textPanel;
    JLabel textfield;
    Game game ;
    MenuFrame2 menu;

    MenuFrame() {
        ImageIcon image = new ImageIcon("b1.jpg");
        ImageIcon image2 = new ImageIcon("b2.jpg");
        ImageIcon imageL = new ImageIcon("Media/logo.png");
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        textfield=new JLabel();


        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Minesweeper Game");
        this.getContentPane().setBackground(new Color(192,192,192));
        this.setIconImage(imageL.getImage());
        textfield.setFont(new Font("thoma",Font.BOLD,20));
        textfield.setForeground(new Color(95,95,95));
        textfield.setBounds(90,30,500,100);
        textfield.setText("\" welcome To Minesweeper \"              ");
        textfield.setBounds(110,10,400,100);


        b1.setBounds(170,140,145,100);
        b1.setBorderPainted(true);
        b1.setBorder(BorderFactory.createCompoundBorder() );
        b1.setIcon(image);
        b1.setFocusPainted(false);



        b2.setBounds(170,270,145,100);
        b2.setIcon(image2);
        b2.setBorderPainted(true);
        b2.setBorder(BorderFactory.createCompoundBorder() );
        b2.setFocusPainted(false);
        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
             menu=new MenuFrame2();
             close();
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.add(textfield);
        this.add(b1);
        this.add(b2);
        this.setVisible(true);
        this.add(textfield,BorderLayout.NORTH);
        this.setLocationRelativeTo(null);
    }
    public void close(){
        this.setVisible(false);
    }













}




