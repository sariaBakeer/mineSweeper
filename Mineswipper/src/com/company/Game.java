package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
//fuck you
public class Game extends JFrame {

    //------------Logic--------------

    //متغيرات عدد الاسطر والاعمدة والالغام
    private int rows, columns, numOfMines;
    //مصفوفة الاعداد التي تكتب على الازرار
    int[][] board;
    //متغير يجعل استدعاء ال makeBoard يتم مرة واحدة
    boolean mark = true;
    //مصفوفة تمنع الاوفرفلو من تابع floodFill
    boolean[][] b;
    boolean[][] f;


    //------------Front---------------

    //وسائط
    ImageIcon image = new ImageIcon("logo.png");
    ImageIcon image1 = new ImageIcon("one.jpg");
    ImageIcon image2 = new ImageIcon("tow.jpg");
    ImageIcon image3 = new ImageIcon("three.jpg");
    ImageIcon image4 = new ImageIcon("four.jpg");
    ImageIcon image5 = new ImageIcon("five.jpg");
    ImageIcon image6 = new ImageIcon("six.jpg");
    ImageIcon image7 = new ImageIcon("seven.jpg");
    ImageIcon image8 = new ImageIcon("eight.jpg");
    ImageIcon image9 = new ImageIcon("mine.png");
    ImageIcon image10 = new ImageIcon("flag.png");

    JButton[][] button;
    JPanel buttonPanel;
    JLabel textField;
    JPanel textPanel;
    JMenuBar menuPanel;
    JPanel header;
    JMenuItem newFile = new JMenuItem("New Game");
    JMenuItem open = new JMenuItem("Load Game");
    JMenuItem select = new JMenuItem("Select ");

    //Constructor
    public Game(int rows, int columns, int numOfMines) {

        //------------Logic--------------

        this.rows = rows;
        this.columns = columns;
        this.numOfMines = numOfMines;
        this.f = new boolean[rows + 1][columns + 1];
        this.b = new boolean[rows + 1][columns + 1];
        board = new int[rows + 5][columns + 5];

        //------------Front---------------

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Minesweeper Game");
        this.setResizable(false);
        this.getContentPane().setBackground(Color.red);
        this.setIconImage(image.getImage());

        textPanel = new JPanel();
        textPanel.setVisible(true);
        textPanel.setBackground(Color.BLACK);


        buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        buttonPanel.setLayout(new GridLayout(rows, columns, 3, 3));
        buttonPanel.setBackground(Color.black);


        Border border = BorderFactory.createLineBorder(Color.gray, 5);
        textField = new JLabel();
        textField.setBounds(0, 570, 10, 10);
        textField.setBorder(border);
        button = new JButton[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                button[i][j] = new JButton();
                button[i][j].setVisible(true);
                button[i][j].setFocusable(false);
                button[i][j].setSize(30, 30);
                button[i][j].setBackground(new Color(192, 192, 192));
                buttonPanel.add(button[i][j]);
            }
        }
        menuPanel = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        menuPanel.add(file);
        menuPanel.add(edit);
        menuPanel.setBounds(10, 10, 10, 10);
        file.add(newFile);
        file.add(open);
        edit.add(select);

        header = new JPanel();
        header.setVisible(true);
        header.add(menuPanel);
        header.add(textField);
        header.setBounds(10, 10, 10, 10);

        // textPanel.add(textField);
        this.add(buttonPanel);
        if (rows==9 && columns==9)
            this.setSize(450, 450);
        else if (rows==18 && columns==18)
            this.setSize(700, 700);
        else
            this.setSize(975,975);

        this.add(menuPanel, BorderLayout.NORTH);
        //this.add(header,BorderLayout.NORTH);
        this.revalidate();
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        click();
    }

    //عمل الماوس
    public void click() {

        //المرور على جميع الازرار
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                final int finalI = i;
                final int finalJ = j;

                button[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        //تابع توزيع الارقام وزرع القنابل يستدعى مرة واحدة
                        makeBoard(finalI, finalJ);

                        //الزر اليميني يضع علم
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (!f[finalI][finalJ])

                                button[finalI][finalJ].setIcon(image10);


                        }

                        //الزر اليساري لل click
                        if (SwingUtilities.isLeftMouseButton(e)) {

                            if (board[finalI][finalJ] == 0) {
                                floodFill(finalI, finalJ);
                            }
                            else
                                getColor(finalI, finalJ);

                        }
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

            }
        }
    }

    //انشاء المربعات
    public void makeBoard(int x, int y) {

        if (mark) {
            //اعطاء المصفوفة قيم ابتدائية
            for (int i = 0; i < board.length; i++)
                for (int j = 0; j < board[i].length; j++)
                    board[i][j] = -1;

            //زرع القنابل + عدم الضغط على قنبلة من اول مرة
            while (numOfMines-- != 0) {
                //احداثيات عشوائية للقنابل
                int rand1 = (int) (Math.random() * (rows - 1 + 1) + 1);
                int rand2 = (int) (Math.random() * (columns - 1 + 1) + 1);
                //عدم وجود قنبلتين في نفس المربع
                if (board[rand1][rand2] == 9) {
                    numOfMines++;
                    continue;
                }
                //عدم اختيار قنبلة من اول ضغطة
                if (x == rand1 && y == rand2) {
                    numOfMines++;
                    continue;
                }
                //زرع القنبلة
                board[rand1][rand2] = 9;
            }

            //توزيع الارقام حول القنابل
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    int count = 0;

                    if (board[i][j] == 9)
                        continue;

                    else {
                        if (board[i - 1][j - 1] == 9)
                            count++;
                        if (board[i - 1][j] == 9)
                            count++;
                        if (board[i - 1][j + 1] == 9)
                            count++;
                        if (board[i][j + 1] == 9)
                            count++;
                        if (board[i + 1][j + 1] == 9)
                            count++;
                        if (board[i + 1][j] == 9)
                            count++;
                        if (board[i + 1][j - 1] == 9)
                            count++;
                        if (board[i][j - 1] == 9)
                            count++;

                        board[i][j] = count;
                    }

                }
            }

            //اعطاء قيمة false حتى لايستدعى التابع مرة اخرة
            mark = false;
        }

        //تخرج من التابع في حال كان مستدعى من قبل
        else
            return;
    }

    //الملأ بالطوفان
    public void floodFill(int i, int j) {

        //كل صفر يتم فتحه يؤشر عليه ب true حتى لايدخل اليه التابع مرة ثانية
        b[i][j] = true;

        //تنسيقات
        getColor(i, j);

        //111111111111
        if (board[i - 1][j - 1] != -1) {
            if (board[i - 1][j - 1] == 0 && b[i - 1][j - 1] == false)
                floodFill(i - 1, j - 1);
            else {
                //متلها ولتحت تنسيقات
                getColor(i - 1, j - 1);

            }

        }
        //2222222222
        if (board[i - 1][j] != -1) {
            if (board[i - 1][j] == 0 && b[i - 1][j] == false)
                floodFill(i - 1, j);
            else {
                getColor(i - 1, j);
            }

        }
        //3333333
        if (board[i - 1][j + 1] != -1) {
            if (board[i - 1][j + 1] == 0 && b[i - 1][j + 1] == false)
                floodFill(i - 1, j + 1);
            else {
                getColor(i - 1, j + 1);
            }

        }
        //4444444
        if (board[i][j + 1] != -1) {
            if (board[i][j + 1] == 0 && b[i][j + 1] == false)
                floodFill(i, j + 1);
            else {
                getColor(i, j + 1);
            }

        }
        //55555555
        if (board[i + 1][j + 1] != -1) {
            if (board[i + 1][j + 1] == 0 && b[i + 1][j + 1] == false)
                floodFill(i + 1, j + 1);
            else {
                getColor(i + 1, j + 1);
            }

        }
        //66666666666
        if (board[i + 1][j] != -1) {
            if (board[i + 1][j] == 0 && b[i + 1][j] == false)
                floodFill(i + 1, j);
            else {
                getColor(i + 1, j);
            }
        }
        //77777777
        if (board[i + 1][j - 1] != -1) {
            if (board[i + 1][j - 1] == 0 && b[i + 1][j - 1] == false)
                floodFill(i + 1, j - 1);
            else {
                getColor(i + 1, j - 1);
            }
        }
        //88888888
        if (board[i][j - 1] != -1) {
            if (board[i][j - 1] == 0 && b[i][j - 1] == false)
                floodFill(i, j - 1);
            else {
                getColor(i, j - 1);
            }

        }
    }

    //اعطاء الالوان والتنسيقات
    public void getColor(int i, int j) {

        if (board[i][j] == 0) {
            f[i][j] = true;
            button[i][j].setBackground(Color.WHITE);
            button[i][j].setEnabled(false);
        }

        if (board[i][j] == 1) {
            f[i][j] = true;
            button[i][j].setIcon(image1);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 2) {
            f[i][j] = true;
            button[i][j].setIcon(image2);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 3) {
            f[i][j] = true;
            button[i][j].setIcon(image3);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 4) {
            f[i][j] = true;
            button[i][j].setIcon(image4);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 5) {
            f[i][j] = true;
            button[i][j].setIcon(image5);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 6) {
            f[i][j] = true;
            button[i][j].setIcon(image6);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 7) {
            f[i][j] = true;
            button[i][j].setIcon(image7);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 8) {
            f[i][j] = true;
            button[i][j].setIcon(image8);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 9) {
            gameOver();
        }
    }

    public void gameOver() {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (board[i][j] == 9) {
                    button[i][j].setIcon(image9);
                    button[i][j].setBackground(Color.red);
                    textField.setForeground(Color.RED);
//                    button[i][j].setEnabled(false);
//                    textField.setText("Game Over!");
                }
                else
                    getColor(i,j);
            }
        }
    }



}



//    public void check(int i, int j) {
//        boolean over = false;
//        if (board[i][j] == (rows + 1)) {
//            gameOver(false);
//            over = true;
//        }
//
//        if (!over) {
//            getColor(i, j);
//        }
//    }
//
//    public void gameOver(boolean won) {
//        if (!won) {
//            textField.setForeground(Color.RED);
//            textField.setText("Game Over!");
//        } else {
//            textField.setForeground(Color.GREEN);
//            textField.setText("You Win!");
//        }
//        for (int i = 1; i <= rows; i++) {
//            for (int j = 1; j <= columns; j++) {
//                button[i][j].setEnabled(false);
//                button[i][j].setBackground(Color.BLACK);
//                button[i][j].setText("*");
//            }
//        }
//    }