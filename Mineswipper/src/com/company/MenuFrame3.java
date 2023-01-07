package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuFrame3 extends JFrame {


    JButton b1;
    JButton b2;
    JButton b3;
    JLabel label;
    JPanel textPanel;
    JLabel textfield;
    Game game ;


    MenuFrame3() {
        ImageIcon imageL = new ImageIcon("Media/logo.png");
        ImageIcon image1 = new ImageIcon("easy.jpg");
        ImageIcon image2 = new ImageIcon("normal.jpg");
        ImageIcon image3 = new ImageIcon("hard.jpg");
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        textfield=new JLabel();


        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Minesweeper Game");
        this.setIconImage(imageL.getImage());
        this.getContentPane().setBackground(new Color(192,192,192));

        textfield.setFont(new Font("Arrival",Font.BOLD,20));
        textfield.setForeground(new Color(95,95,95));
        textfield.setText("         \" Chose Difficulty \"");
        textfield.setBounds(90,10,400,100);

        b1.setText("Easy");
        b1.setBounds(160,120,160,80);
        b1.setFocusPainted(false);
        b1.setBorderPainted(true);
        b1.setBorder(BorderFactory.createCompoundBorder() );
        b1.setIcon(image1);



        b2.setText("");
        b2.setBounds(160,230,147,80);
        b2.setFocusPainted(false);
        b2.setBorderPainted(true);
        b2.setBorder(BorderFactory.createCompoundBorder() );
        b2.setIcon(image2);





        b3.setText("s");
        b3.setBounds(160,340,159,80);
        b3.setFocusPainted(false);
        b3.setBorderPainted(true);
        b3.setBorder(BorderFactory.createCompoundBorder() );
        b3.setIcon(image3);

        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = new Game(11,11,9);
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
        b2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = new Game(20,20,64);
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
        b3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = new Game(27,27,200);
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
        b2.setFocusPainted(false);
        this.add(textfield);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.setVisible(true);
        this.add(textfield,BorderLayout.NORTH);
        this.setLocationRelativeTo(null);
    }
    public void close(){

        this.setVisible(false);
    }













}




