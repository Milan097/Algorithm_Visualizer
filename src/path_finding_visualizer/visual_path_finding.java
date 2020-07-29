package path_finding_visualizer;

import algorithm_visualizer.Algorithm_Visualizer;
import path_finding_algorithms.Astar;
import path_finding_algorithms.Dijakstra;
import path_finding_algorithms.PathFinding;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

public class visual_path_finding {

    public final int HEIGHT = 700;
    public final int MSIZE = 600;
    // FRAME
    public JFrame jFrame;
    public JPanel toolBar = new JPanel();
    // General Variables
    public int cells = 20;
    public int delay = 30;
    public double dense = 0.2;
    public double density = (cells * cells) * 0.5;
    public int startX = -1;
    public int startY = -1;
    public int finishX = -1;
    public int finishY = -1;
    public int tool = 0;
    public int checks = 0;
    public int length = 0;
    public int curAlg = 0;
    public int WIDTH = 900;
    public int CSIZE = MSIZE / cells;
    public boolean solving = false;

    // Arrays
    public String[] algorithms = {"Dijkstra", "A*"};
    public String[] tools = {"Start", "Finish", "Wall", "Eraser"};
    public JSlider size = new JSlider(1, 5, 2);
    public JSlider speed = new JSlider(0, 500, delay);
    public JSlider obstacles = new JSlider(1, 60, 30);
    public JComboBox algorithmsBx = new JComboBox(algorithms);
    public JComboBox toolBx = new JComboBox(tools);
    public Node[][] map;
    public PathFinding Alg = new PathFinding();
    // GUI components
    JLabel algL = new JLabel("Algorithms");
    JLabel toolL = new JLabel("Toolbox");
    JLabel sizeL = new JLabel("Size:");
    JLabel cellsL = new JLabel(cells + "x" + cells);
    JLabel delayL = new JLabel("Delay:");
    JLabel msL = new JLabel(delay + "ms");
    JLabel obstacleL = new JLabel("Dens:");
    JLabel checkL = new JLabel("Checks: " + checks);
    JLabel lengthL = new JLabel("Path Length: " + length);
    JButton searchB = new JButton("Start Search");
    JButton resetB = new JButton("Reset");
    JButton genMapB = new JButton("Generate Map");
    JButton clearMapB = new JButton("Clear Map");
    JButton back = new JButton("Back");
    JLabel densityL = new JLabel(obstacles.getValue() + "%");
    Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    Map canvas;
    // Utilities
    Random r = new Random();

    public visual_path_finding() {
        clearMap();
        initiaize();
    }

    public void generateMap() {
        clearMap();
        for (int i = 0; i < density; i++) {
            Node current;
            do {
                int x = r.nextInt(cells);
                int y = r.nextInt(cells);
                current = map[x][y];
            } while (current.getCellType() == 2);
            current.setCellType(2);
        }
    }

    public void clearMap() {    //CLEAR MAP
        finishX = -1;    //RESET THE START AND FINISH
        finishY = -1;
        startX = -1;
        startY = -1;
        map = new Node[cells][cells];    //CREATE NEW MAP OF NODES
        for (int x = 0; x < cells; x++) {
            for (int y = 0; y < cells; y++) {
                map[x][y] = new Node(this, 3, x, y);    //SET ALL NODES TO EMPTY
            }
        }
        reset();
    }

    public void resetMap() {    //RESET MAP
        for (int x = 0; x < cells; x++) {
            for (int y = 0; y < cells; y++) {
                Node current = map[x][y];
                if (current.getCellType() == 4 || current.getCellType() == 5) {
                    map[x][y] = new Node(this, 3, x, y);
                }
            }
        }

        if (startX != -1 && startY != -1) {
            map[startX][startY] = new Node(this, 0, startX, startY);
            map[startY][startY].setHops(0);
        }
        if (finishX != -1 && finishY != -1) {
            map[finishX][finishY] = new Node(this, 1, finishX, finishY);
        }
        reset();
    }


    public void initiaize() {
        jFrame = new JFrame();
        jFrame.setResizable(false);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setTitle("Path Finding");
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.getContentPane().setLayout(null);

        toolBar.setBorder(BorderFactory.createTitledBorder(loweredetched, "Controls"));
        int space = 25;
        int buff = 50;
        toolBar.setLayout(null);
        toolBar.setBounds(10, 10, 250, 648);

        searchB.setBounds(65, space, 120, 25);
        toolBar.add(searchB);
        space += buff;

        resetB.setBounds(65, space, 120, 25);
        toolBar.add(resetB);
        space += buff;

        genMapB.setBounds(65, space, 120, 25);
        toolBar.add(genMapB);
        space += buff;

        clearMapB.setBounds(65, space, 120, 25);
        toolBar.add(clearMapB);
        space += buff;

        algL.setBounds(95, space, 120, 25);
        toolBar.add(algL);
        space += 25;

        algorithmsBx.setBounds(65, space, 120, 25);
        toolBar.add(algorithmsBx);
        space += 40;

        toolL.setBounds(105, space, 120, 25);
        toolBar.add(toolL);
        space += 25;

        toolBx.setBounds(65, space, 120, 25);
        toolBar.add(toolBx);
        space += buff;

        sizeL.setBounds(25, space, 40, 25);
        toolBar.add(sizeL);
        size.setMajorTickSpacing(10);
        size.setBounds(60, space, 130, 25);
        toolBar.add(size);
        cellsL.setBounds(190, space, 40, 25);
        toolBar.add(cellsL);
        space += buff;

        delayL.setBounds(25, space, 40, 25);
        toolBar.add(delayL);
        speed.setMajorTickSpacing(5);
        speed.setBounds(60, space, 130, 25);
        toolBar.add(speed);
        msL.setBounds(190, space, 40, 25);
        toolBar.add(msL);
        space += buff;


        obstacleL.setBounds(25, space, 40, 25);
        toolBar.add(obstacleL);
        obstacles.setMajorTickSpacing(5);
        obstacles.setBounds(60, space, 130, 25);
        toolBar.add(obstacles);
        densityL.setBounds(190, space, 40, 25);
        toolBar.add(densityL);
        space += buff;


        checkL.setBounds(25, space, 100, 25);
        toolBar.add(checkL);
        space += 40;

        lengthL.setBounds(25, space, 100, 25);
        toolBar.add(lengthL);

        back.setBounds(75, 600, 100, 25);
        toolBar.add(back);

        jFrame.add(toolBar);

        canvas = new Map(this);
        canvas.setBounds(277, 35, MSIZE + 1, MSIZE + 1);
        jFrame.add(canvas);
        jFrame.setVisible(true);

        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                if (startX != -1 && startY != -1 && finishX != -1 && finishY != -1) {
                    solving = true;
                }
            }
        });

        resetB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMap();
                update();
            }
        });

        genMapB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMap();
                update();
            }
        });

        clearMapB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMap();
                update();
            }
        });

        algorithmsBx.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                curAlg = algorithmsBx.getSelectedIndex();
                update();
            }
        });

        toolBx.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                tool = toolBx.getSelectedIndex();
            }
        });

        size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                cells = size.getValue() * 10;
                clearMap();
                reset();
                update();
            }
        });

        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                delay = speed.getValue();
                update();
            }
        });

        obstacles.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dense = (double) obstacles.getValue() / 100;
                update();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Algorithm_Visualizer();
                    }
                }).start();
            }
        });

        startSearch();
    }


    public void startSearch() {
        if (solving) {
            switch (curAlg) {
                case 0:
                    Alg = new Dijakstra(this);
                    Alg.findPath();
                    break;
                case 1:
                    Alg = new Astar(this);
                    Alg.findPath();
                    ;
                    break;
            }
        }
        pause();    //PAUSE STATE
    }

    public void pause() {
        int i = 0;
        while (!solving) {
            i++;
            if (i > 500)
                i = 0;
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
        startSearch();
    }

    public void reset() {
        solving = false;
        length = 0;
        checks = 0;
    }

    public void update() {
        density = (cells * cells) * dense;
        CSIZE = MSIZE / cells;
        canvas.repaint();
        cellsL.setText(cells + "x" + cells);
        msL.setText(delay + "ms");
        lengthL.setText("Path Length: " + length);
        densityL.setText(obstacles.getValue() + "%");
        checkL.setText("Checks: " + checks);
    }

    public void delay() {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        new visual_path_finding();
//    }
}
