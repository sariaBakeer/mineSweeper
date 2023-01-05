package com.company;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame {

    //------------Logic--------------


    //متغيرات عدد الاسطر والاعمدة والالغام
    private int rows, columns, numOfMines;
    //مصفوفة الاعداد التي تكتب على الازرار
    int[][] board;
    //متغير يجعل استدعاء ال makeBoard يتم مرة واحدة
    boolean isCalled;
    //مصفوفة تمنع الاوفرفلو من تابع floodFill
    boolean[][] b;
    //
    boolean[][] f;
    boolean[][] unF;
    //

    boolean isGameOver, isWin ;
    //
    Timer t ;
    TimerTask ts;
    //
    int countF = 0;
    //
    int constMine;


    //------------Front---------------

    //وسائط
    ImageIcon imageL = new ImageIcon("Media/logo.png");
    ImageIcon imageM = new ImageIcon("Media/mine.png");
    ImageIcon imageMW = new ImageIcon("Media/mineMW.jpg");
    ImageIcon imageF = new ImageIcon("Media/flag.png");
    ImageIcon imageFX = new ImageIcon("Media/flagX.jpg");
    ImageIcon imageB = new ImageIcon("Media/button.jpg");
    ImageIcon image0 = new ImageIcon("Media/zero.jpg");
    ImageIcon image1 = new ImageIcon("Media/one.jpg");
    ImageIcon image2 = new ImageIcon("Media/tow.jpg");
    ImageIcon image3 = new ImageIcon("Media/three.jpg");
    ImageIcon image4 = new ImageIcon("Media/four.jpg");
    ImageIcon image5 = new ImageIcon("Media/five.jpg");
    ImageIcon image6 = new ImageIcon("Media/six.jpg");
    ImageIcon image7 = new ImageIcon("Media/seven.jpg");
    ImageIcon image8 = new ImageIcon("Media/eight.jpg");
    ImageIcon image13 = new ImageIcon("Media/smile.jpg");
    Game game;


    JButton[][] button;
    JPanel buttonPanel;
    JLabel textFild;

    JLabel iconsmile;
    JPanel textPanel;
    JLabel timericone;

    JPanel p1;
    JButton b1;

    JMenuBar menuPanel;
    JMenuItem newFile = new JMenuItem("New Game");
    JMenuItem open = new JMenuItem("Load Game");
    JMenuItem select = new JMenuItem("Select ");



    //-----------Constructor------------
    public Game(int rows, int columns, int numOfMines)  {

        //------------Logic--------------
        this.rows = rows;
        this.columns = columns;
        this.numOfMines = numOfMines;

        constMine = numOfMines;
        board = new int[rows + 5][columns + 5];
        this.b = new boolean[rows + 1][columns + 1];
        this.f = new boolean[rows + 1][columns + 1];
        this.unF = new boolean[rows + 1][columns + 1];

        t = new Timer();
        ts = new TimerTask() {
            int i=0;
            @Override
            public void run() {
                if (isGameOver)
                    ts.cancel();
                 timericone.setText(Integer.toString(i++));
            }
        };


        t = new Timer();
        ts = new TimerTask() {
            int i=0;
            @Override
            public void run() {
                System.out.println(++i);

            }
        };

        //------------Front--------------

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Minesweeper Game");
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setIconImage(imageL.getImage());
        this.getContentPane().setBackground(Color.gray);



        buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        buttonPanel.setLayout(new GridLayout(rows, columns));
        buttonPanel.setBackground(Color.gray);

        buttonPanel.setBounds(0,80,700,680);
        button= new JButton[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                button[i][j] = new JButton();
                button[i][j].setVisible(true);
                button[i][j].setFocusable(false);
                button[i][j].setSize(30, 30);
                button[i][j].setIcon(imageB);
                buttonPanel.add(button[i][j]);
            }
        }

        textPanel = new JPanel();
        textPanel.setVisible(true);
        textPanel.setBackground(Color.gray);

        textPanel.setBounds(0,0,700,130);
        textPanel.revalidate();
        textPanel.setLayout(null);


        textFild = new JLabel();
        textFild.setHorizontalAlignment(JLabel.LEFT);
        textFild.setFont(new Font("MV Boli", Font.BOLD, 20));
        textFild.setForeground(Color.red);
        textFild.setIcon(image13);


        iconsmile = new JLabel();
        iconsmile.setHorizontalAlignment(JLabel.CENTER);
        iconsmile.setIcon(image13);
        iconsmile.setBounds(340, 10, 40, 40);


        timericone = new JLabel();
        timericone.setHorizontalAlignment(JLabel.RIGHT);
        timericone.setBounds(550, 10, 90, 40);
        timericone.setBackground(Color.white);
        timericone.setOpaque(true);
        timericone.setVisible(true);
         timericone.setText("0");
        timericone.setForeground(Color.black);
        timericone.setLayout(null);



        menuPanel = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        menuPanel.add(file);
        menuPanel.add(edit);
        menuPanel.setBounds(10, 10, 700, 80);

        newFile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game = new Game(18, 18, 70);
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

        file.add(newFile);
        file.add(open);
        edit.add(select);
        menuPanel.setBackground(Color.gray);

        textPanel.add(iconsmile);
        textPanel.add(timericone);
        //textPanel.add(b1);

        this.add(buttonPanel);
        this.add(textPanel);
        textPanel.add(textFild);

        if (rows == 9 && columns == 9)
            this.setSize(450, 450);
        else if (rows == 18 && columns == 18)

            this.setSize(715, 805);
        else
            this.setSize(975, 975);



        this.add(menuPanel, BorderLayout.NORTH);
        this.revalidate();
        this.setLocationRelativeTo(null);
        this.setLayout(null);

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

                        if (!isCalled)

                            t.schedule(ts, new Date(),1000);

                        //تابع توزيع الارقام وزرع القنابل يستدعى مرة واحدة
                        makeBoard(finalI, finalJ);

                        //الزر اليميني يضع flag
                        if (SwingUtilities.isRightMouseButton(e)) {

                            if (!isGameOver) {

                                if (!f[finalI][finalJ] && !unF[finalI][finalJ]) {
                                    countF++;
                                    if (countF == constMine) {
                                        button[finalI][finalJ].setIcon(imageF);
                                        win();
                                    }
                                    else {
                                        button[finalI][finalJ].setIcon(imageF);
                                        unF[finalI][finalJ] = true;
                                    }
                                } else if (!f[finalI][finalJ] && unF[finalI][finalJ]) {
                                    button[finalI][finalJ].setIcon(imageB);
                                    unF[finalI][finalJ] = false;
                                    countF--;
                                }

                            }
                        }

                        //الزر اليساري لل click
                        if (SwingUtilities.isLeftMouseButton(e)) {

                            if (!unF[finalI][finalJ]) {

                                if (board[finalI][finalJ] == 0) {
                                    floodFill(finalI, finalJ);
                                } else
                                    getColor(finalI, finalJ);
                            }
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

        if (!isCalled) {
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
                //عدم اختيار قنبلة من اول ضغطة + عمل floodFill

                if (rand1 == x && rand2 ==y || rand1 == x-1 && rand2 == y-1 ||
                    rand1 == x-1 && rand2 == y || rand1 == x-1 && rand2 == y+1 ||
                    rand1 == x && rand2 == y+1 || rand1 == x+1 && rand2 == y+1 ||
                    rand1 == x+1 && rand2 == y || rand1 == x+1 && rand2 == y-1 ||
                    rand1 == x && rand2 == y-1) {
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
            isCalled = true;
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
            if (board[i - 1][j - 1] == 0 && !b[i - 1][j - 1])
                floodFill(i - 1, j - 1);
            else {
                //متلها ولتحت تنسيقات
                getColor(i - 1, j - 1);

            }

        }
        //2222222222
        if (board[i - 1][j] != -1) {
            if (board[i - 1][j] == 0 && !b[i - 1][j])
                floodFill(i - 1, j);
            else {
                getColor(i - 1, j);
            }

        }
        //3333333
        if (board[i - 1][j + 1] != -1) {
            if (board[i - 1][j + 1] == 0 && !b[i - 1][j + 1])
                floodFill(i - 1, j + 1);
            else {
                getColor(i - 1, j + 1);
            }

        }
        //4444444
        if (board[i][j + 1] != -1) {
            if (board[i][j + 1] == 0 && !b[i][j + 1])
                floodFill(i, j + 1);
            else {
                getColor(i, j + 1);
            }

        }
        //55555555
        if (board[i + 1][j + 1] != -1) {
            if (board[i + 1][j + 1] == 0 && !b[i + 1][j + 1])
                floodFill(i + 1, j + 1);
            else {
                getColor(i + 1, j + 1);
            }

        }
        //66666666666
        if (board[i + 1][j] != -1) {
            if (board[i + 1][j] == 0 && !b[i + 1][j])
                floodFill(i + 1, j);
            else {
                getColor(i + 1, j);
            }
        }
        //77777777
        if (board[i + 1][j - 1] != -1) {
            if (board[i + 1][j - 1] == 0 && !b[i + 1][j - 1])
                floodFill(i + 1, j - 1);
            else {
                getColor(i + 1, j - 1);
            }
        }
        //88888888
        if (board[i][j - 1] != -1) {
            if (board[i][j - 1] == 0 && !b[i][j - 1])
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
            button[i][j].setIcon(image0);
        }

        if (board[i][j] == 1 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image1);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 2 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image2);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 3 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image3);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 4 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image4);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 5 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image5);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 6 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image6);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 7 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image7);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 8 && !unF[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(image8);
            button[i][j].setBackground(Color.white);
        }

        if (board[i][j] == 9 && !f[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(imageM);
            button[i][j].setBackground(Color.red);

            gameOver(i,j);
        }
    }

    public void gameOver(int x, int y) {
        ts.cancel();
        isGameOver = true;


        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {

                if (x == i && y == j)
                    continue;

                if (board[i][j] == 9) {
                    f[i][j] = true;
                    if (!unF[i][j])
                        button[i][j].setIcon(imageMW);
                    else
                        button[i][j].setIcon(imageF);

                }

                if (board[i][j] != 9 && unF[i][j])

                    button[i][j].setIcon(imageFX);
                else
                    getColor(i, j);
            }
        }
    }


    public void win() {

        isGameOver = true;
        t.cancel();
        isWin = true;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (board[i][j]!=9)
                    getColor(i,j);
            }
        }
    }


}