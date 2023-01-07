package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MultiPlayerGame extends JFrame {

    //-----------MultiPlayer------------
    int counter = 1;
    int p1Point = 0, p2Point = 0;

    boolean[][] isClick;

    int visibleNumberOfMine;
    //--------------------------

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

    boolean isGameOver;
    //
    Timer t;
    TimerTask ts;
    //
    int countF = 0;
    //
    int constMine;


    //------------Front---------------

    //وسائط
            ImageIcon imageL = new ImageIcon("Media/logo.png");
    ImageIcon imageM = new ImageIcon("mine22.jpg");
    ImageIcon imageMW = new ImageIcon("mine2.jpg");
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
    ImageIcon image13 = new ImageIcon("smile.jpg");

    Game game;


    JButton[][] button;
    JPanel buttonPanel;
    JLabel textFild;

    JLabel iconsmile;
    JPanel textPanel;
    JLabel timericone;

    //--------------------MultiPlayer-----------------
    JLabel WhichPlayer;
    JLabel pointer;
    JLabel role;
    //-------------------------------


    JPanel p1;
    JButton b1;

    JMenuBar menuPanel;
    JMenuItem newFile = new JMenuItem("New Game");
    JMenuItem open = new JMenuItem("Load Game");
    JMenuItem select = new JMenuItem("Select ");


    //-----------Constructor-----------
    public MultiPlayerGame(int rows, int columns, int numOfMines) {
        //------------Logic--------------
        this.rows = rows;
        this.columns = columns;
        this.numOfMines = numOfMines;

        constMine = numOfMines;
        board = new int[rows + 5][columns + 5];
        this.b = new boolean[rows + 1][columns + 1];
        this.f = new boolean[rows + 1][columns + 1];
        this.unF = new boolean[rows + 1][columns + 1];
        isClick = new boolean[rows + 1][columns + 1];

        t = new Timer();
        ts = new TimerTask() {
            int i = 0;
            int j = 0;

            @Override
            public void run() {
                if (isGameOver)
                    ts.cancel();
                timericone.setText(j + " : " + Integer.toString(i++));
                if (i == 60) {
                    i = 0;
                    j++;
                }

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

        buttonPanel.setBounds(0, 80, 700, 680);
        button = new JButton[rows + 1][columns + 1];
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

        textPanel.setBackground(Color.lightGray);



        textPanel.setBounds(0, 0, 700, 130);
        textPanel.revalidate();
        textPanel.setLayout(null);


        textFild = new JLabel();
        textFild.setHorizontalAlignment(JLabel.LEFT);
        textFild.setFont(new Font("MV Boli", Font.BOLD, 20));
        textFild.setForeground(Color.red);



                iconsmile = new JLabel();
        iconsmile.setHorizontalAlignment(JLabel.CENTER);
        iconsmile.setIcon(image13);

        iconsmile.setBounds(340, 5, 50, 50);


                timericone = new JLabel();
        timericone.setHorizontalAlignment(JLabel.RIGHT);
        timericone.setBounds(550, 10, 90, 40);

        timericone.setBackground(Color.BLACK);
        timericone.setOpaque(true);
        timericone.setVisible(true);
        timericone.setText("00:00");
        timericone.setForeground(Color.red);
        timericone.setFont(new Font("Arrival", Font.BOLD, 35));
        timericone.setLayout(null);


        //--------------Multi Player-------------
        WhichPlayer = new JLabel();
        WhichPlayer.setHorizontalAlignment(JLabel.LEFT);
        WhichPlayer.setBounds(120, 10, 200, 40);
        WhichPlayer.setBackground(Color.lightGray);
        WhichPlayer.setFont(new Font("Arrival", Font.BOLD, 24));
        WhichPlayer.setOpaque(true);
        WhichPlayer.setVisible(true);
        WhichPlayer.setText("0");
        WhichPlayer.setForeground(Color.red);
        WhichPlayer.setLayout(null);

        pointer = new JLabel();
        pointer.setHorizontalAlignment(JLabel.LEFT);
        pointer.setBounds(20, 10, 90, 40);
        pointer.setBackground(Color.BLACK);
        pointer.setFont(new Font("Arrival", Font.BOLD, 24));
        pointer.setOpaque(true);
        pointer.setVisible(true);
        pointer.setText("0 : 0");
        pointer.setForeground(Color.RED);
        pointer.setLayout(null);


        role = new JLabel();
        role.setHorizontalAlignment(JLabel.LEFT);
        role.setBounds(150, 10, 80, 40);
        role.setBackground(Color.lightGray);
        role.setFont(new Font("Arrival", Font.BOLD, 24));
        role.setOpaque(true);
        role.setVisible(true);
        role.setText("One");
        role.setForeground(Color.black);
        role.setLayout(null);
        textPanel.add(role);

        //------------------------------------------


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
        textPanel.add(pointer);


        this.add(buttonPanel);
        this.add(textPanel);
        textPanel.add(textFild);


        if (rows == 11 && columns == 11) {
            this.setSize(535, 560);
            buttonPanel.setBounds(0, 80, 520, 430);
            iconsmile.setBounds(242, 5, 50, 50);
            timericone.setBounds(420, 10, 90, 40);

        } else if (rows == 20 && columns == 20)

            this.setSize(720, 810);
        else
            this.setSize(715, 805);





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

                            t.schedule(ts, new Date(), 1000);

                        //تابع توزيع الارقام وزرع القنابل يستدعى مرة واحدة
                        makeBoard(finalI, finalJ);

                        //الزر اليميني يضع flag
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (!isGameOver) {

                                if (counter % 2 != 0) {
                                    if (!f[finalI][finalJ] && !unF[finalI][finalJ]) {
                                        countF++;
                                        if (countF == constMine) {
                                            button[finalI][finalJ].setIcon(imageF);
                                            win();
                                        } else {
                                            button[finalI][finalJ].setIcon(imageF);
                                            unF[finalI][finalJ] = true;
                                        }
                                    } else if (!f[finalI][finalJ] && unF[finalI][finalJ]) {
                                        button[finalI][finalJ].setIcon(imageB);
                                        unF[finalI][finalJ] = false;
                                        countF--;
                                    }
                                    counter++;
                                } else {
                                    if (!f[finalI][finalJ] && !unF[finalI][finalJ]) {
                                        countF++;
                                        if (countF == constMine) {
                                            button[finalI][finalJ].setIcon(imageF);
                                            win();
                                        } else {
                                            button[finalI][finalJ].setIcon(imageF);
                                            unF[finalI][finalJ] = true;
                                        }
                                    } else if (!f[finalI][finalJ] && unF[finalI][finalJ]) {
                                        button[finalI][finalJ].setIcon(imageB);
                                        unF[finalI][finalJ] = false;
                                        countF--;
                                    }
                                    counter++;
                                }

                            }
                        }

                        //الزر اليساري لل click
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            if (!unF[finalI][finalJ]) {

                                if (counter % 2 != 0) {
                                    if (board[finalI][finalJ] == 0) {
                                        floodFill(finalI, finalJ);
                                    } else
                                        getColor(finalI, finalJ);
                                    counter++;
                                } else {
                                    if (board[finalI][finalJ] == 0) {
                                        floodFill(finalI, finalJ);
                                    } else
                                        getColor(finalI, finalJ);
                                    counter++;
                                }

                            }
                        }

                        visibleNumberOfMine = constMine - countF;
                        System.out.println(visibleNumberOfMine);

                        if (counter % 2 != 0 && !f[finalI][finalJ])
                            role.setText("One");
                        else
                            role.setText("Tow");
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

                if (rand1 == x && rand2 == y || rand1 == x - 1 && rand2 == y - 1 ||
                        rand1 == x - 1 && rand2 == y || rand1 == x - 1 && rand2 == y + 1 ||
                        rand1 == x && rand2 == y + 1 || rand1 == x + 1 && rand2 == y + 1 ||
                        rand1 == x + 1 && rand2 == y || rand1 == x + 1 && rand2 == y - 1 ||
                        rand1 == x && rand2 == y - 1) {
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

        if (counter == 1) {

            //كل صفر يتم فتحه يؤشر عليه ب true حتى لايدخل اليه التابع مرة ثانية
            b[i][j] = true;

            //تنسيقات
            getColorFloodFill(i, j);

            //111111111111
            if (board[i - 1][j - 1] != -1) {
                if (board[i - 1][j - 1] == 0 && !b[i - 1][j - 1])
                    floodFill(i - 1, j - 1);
                else {
                    //متلها ولتحت تنسيقات
                    getColorFloodFill(i - 1, j - 1);

                }

            }
            //2222222222
            if (board[i - 1][j] != -1) {
                if (board[i - 1][j] == 0 && !b[i - 1][j])
                    floodFill(i - 1, j);
                else {
                    getColorFloodFill(i - 1, j);
                }

            }
            //3333333
            if (board[i - 1][j + 1] != -1) {
                if (board[i - 1][j + 1] == 0 && !b[i - 1][j + 1])
                    floodFill(i - 1, j + 1);
                else {
                    getColorFloodFill(i - 1, j + 1);
                }

            }
            //4444444
            if (board[i][j + 1] != -1) {
                if (board[i][j + 1] == 0 && !b[i][j + 1])
                    floodFill(i, j + 1);
                else {
                    getColorFloodFill(i, j + 1);
                }

            }
            //55555555
            if (board[i + 1][j + 1] != -1) {
                if (board[i + 1][j + 1] == 0 && !b[i + 1][j + 1])
                    floodFill(i + 1, j + 1);
                else {
                    getColorFloodFill(i + 1, j + 1);
                }

            }
            //66666666666
            if (board[i + 1][j] != -1) {
                if (board[i + 1][j] == 0 && !b[i + 1][j])
                    floodFill(i + 1, j);
                else {
                    getColorFloodFill(i + 1, j);
                }
            }
            //77777777
            if (board[i + 1][j - 1] != -1) {
                if (board[i + 1][j - 1] == 0 && !b[i + 1][j - 1])
                    floodFill(i + 1, j - 1);
                else {
                    getColorFloodFill(i + 1, j - 1);
                }
            }
            //88888888
            if (board[i][j - 1] != -1) {
                if (board[i][j - 1] == 0 && !b[i][j - 1])
                    floodFill(i, j - 1);
                else {
                    getColorFloodFill(i, j - 1);
                }

            }
        } else {
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

    }

    //اعطاء الالوان والتنسيقات
    public void getColor(int i, int j) {

        int countPoint = 0;

        if (board[i][j] == 0) {
            f[i][j] = true;
            if (!f[i][j])
                countPoint = board[i][j];
            button[i][j].setIcon(image0);
        }

        if (board[i][j] == 1 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image1);
            button[i][j].setBackground(Color.white);

        }

        if (board[i][j] == 2 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image2);
            button[i][j].setBackground(Color.white);

        }

        if (board[i][j] == 3 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image3);
            button[i][j].setBackground(Color.white);

        }

        if (board[i][j] == 4 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image4);
            button[i][j].setBackground(Color.white);

        }

        if (board[i][j] == 5 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image5);
            button[i][j].setBackground(Color.white);

        }

        if (board[i][j] == 6 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image6);
            button[i][j].setBackground(Color.white);

        }

        if (board[i][j] == 7 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image7);
            button[i][j].setBackground(Color.white);

        }

        if (board[i][j] == 8 && !unF[i][j]) {
            if (!f[i][j])
                countPoint = board[i][j];
            f[i][j] = true;
            button[i][j].setIcon(image8);
            button[i][j].setBackground(Color.white);

        }

        if (counter % 2 != 0) {
            p1Point += countPoint;
            pointer.setText(Integer.toString(p1Point) + " : " + Integer.toString(p2Point));
        } else {
            p2Point += countPoint;
            pointer.setText(Integer.toString(p1Point) + " : " + Integer.toString(p2Point));
        }

        if (board[i][j] == 9 && !f[i][j]) {
            f[i][j] = true;
            button[i][j].setIcon(imageM);
            button[i][j].setBackground(Color.red);

            gameOver(i, j);
        }
    }

    public void getColorFloodFill(int i, int j) {

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
    }

    public void gameOver(int x, int y) {

        role.setVisible(false);

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
                    getColorFloodFill(i, j);
            }
        }

        textPanel.add(WhichPlayer);
        if (counter % 2 != 0)
            WhichPlayer.setText("player one Lose");
        else
            WhichPlayer.setText("player tow Lose");
    }

    public void win() {

        role.setVisible(false);

        isGameOver = true;
        t.cancel();

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (board[i][j] != 9)
                    getColorFloodFill(i, j);
            }
        }

        textPanel.add(WhichPlayer);
        if (p1Point > p2Point)
            WhichPlayer.setText("player one Win : " + p1Point);
        else
            WhichPlayer.setText("player tow Win : " + p2Point);
    }
}