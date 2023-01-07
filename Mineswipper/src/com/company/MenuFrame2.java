package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuFrame2 extends JFrame {


    JButton b1;
    JButton b2;
    JLabel label;
    JPanel textPanel;
    JLabel textfield;
    JLabel textfield2;
    MenuFrame3 menu2;
    MultiPlayerGame multiPlayerGame;


    MenuFrame2() {
        ImageIcon imageL = new ImageIcon("Media/logo.png");
        ImageIcon image1 = new ImageIcon("b3.jpg");
        ImageIcon image2 = new ImageIcon("b4.jpg");
        JButton b1 = new JButton();
        JButton b2 = new JButton();
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
        textfield.setText("          \" Game Menu \"");
        textfield.setBounds(110,10,400,100);

        b1.setText("single player");
        b1.setBounds(175,140,145,100);
        b1.setFocusPainted(false);
        b1.setBorderPainted(true);
        b1.setBorder(BorderFactory.createCompoundBorder() );
        b1.setIcon(image1);



        b2.setText("Multi player");
        b2.setBounds(175,290,145,100);
        b2.setFocusPainted(false);
        b2.setBorderPainted(true);
        b2.setBorder(BorderFactory.createCompoundBorder() );
        b2.setIcon(image2);


        b1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            menu2 = new MenuFrame3();
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
                                multiPlayerGame = new MultiPlayerGame(20,20,64);
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
        this.setVisible(true);
        this.add(textfield,BorderLayout.NORTH);
        this.setLocationRelativeTo(null);
    }
    public void close(){

        this.setVisible(false);
    }













}




