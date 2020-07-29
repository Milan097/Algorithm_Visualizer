/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting_visualizer;

import algorithm_visualizer.Algorithm_Visualizer;
import sorting_algorithms.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

/**
 * @author MILAN BHAI
 */
public class visual_sorting {
    // FRAME
    public JFrame jFrame;
    public JPanel toolBar = new JPanel();

    // General Variables
    public int len = 50;
    public int spd = 15;
    public int compare = 0;
    public int access = 0;
    public int cur = -1;
    public int num = -1;
    public int SIZE = 600;
    public int width = SIZE / len;
    public boolean sorting;         // Sorting is started or not.
    public boolean shuffled;        // Array is shuffled or not.
    public Sort curAlgo = new SelectionSort(this);

    // Arrays
    public int[] array;             // array to be sorted.
    // GUI components
    public JLabel algoLable = new JLabel("Algorithms");
    public JLabel delayLable = new JLabel("Delay :");
    public JLabel msLable = new JLabel(spd + " ms");
    public JLabel sizeLable = new JLabel("Size :");
    public JLabel lenLable = new JLabel(len + "");
    public JLabel compLable = new JLabel("Comparisons      : " + compare);
    public JLabel acLable = new JLabel("Array Accesses : " + access);
    public JTextArea complexity = new JTextArea(curAlgo.getSortComplexity());
    public JButton sortButton = new JButton("SORT");
    public JButton suffleButton = new JButton("SUFFLE");
    public JButton backButton = new JButton("BACK");
    public JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);
    public JSlider delaySlider = new JSlider(JSlider.HORIZONTAL, 1, 100, spd);
    public graph_canvas canvas;
    // Utilities
    Random r = new Random();
    private String[] algorithms = {
            "Selection Sort",
            "Bubble Sort",
            "Cocktail Sort",
            "Odd Even Sort",
            "Insertion Sort",
            "Tim Sort",
            "Quick Sort",
            "Heap Sort",
            "Merge Sort",
            "Pigeonhole Sort",
            "Radix Sort(MSB)"
    };
    public JComboBox algos = new JComboBox(algorithms);

    public visual_sorting() {
        makeArray();
        initialize();
    }

    public void makeArray() {
        // create basic array
        array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = i + 1;
        }

        // re-arrange an array
        for (int i = 0; i < len * 3; i++) {
            // swap ith element with random element
            for (int j = 0; j < len; j++) {
                int rr = r.nextInt(len);
                int tmp = array[rr];
                array[rr] = array[j];
                array[j] = tmp;
            }
        }
        sorting = false;
        shuffled = true;
    }

    public void initialize() {
        // Frame
        jFrame = new JFrame();
        jFrame.setSize(900, 700);
        jFrame.setTitle("Sorting Visulizer");
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setLayout(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Toolbar
        toolBar.setLayout(null);
        toolBar.setBounds(10, 10, 250, 648);
        toolBar.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Controls")
        );

        algoLable.setHorizontalAlignment(JLabel.CENTER);
        algoLable.setBounds(50, 20, 145, 25);
        toolBar.add(algoLable);
        algos.setBounds(30, 50, 190, 25);
        toolBar.add(algos);

        sortButton.setBounds(50, 120, 150, 40);
        toolBar.add(sortButton);
        suffleButton.setBounds(50, 190, 150, 40);
        toolBar.add(suffleButton);

        delayLable.setHorizontalAlignment(JLabel.LEFT);
        delayLable.setBounds(15, 275, 40, 40);
        toolBar.add(delayLable);
        delaySlider.setMajorTickSpacing(5);
        delaySlider.setBounds(60, 277, 120, 40);
        toolBar.add(delaySlider);
        msLable.setHorizontalAlignment(JLabel.LEFT);
        msLable.setBounds(185, 275, 50, 40);
        toolBar.add(msLable);

        sizeLable.setHorizontalAlignment(JLabel.LEFT);
        sizeLable.setBounds(15, 310, 40, 40);
        toolBar.add(sizeLable);
        sizeSlider.setMajorTickSpacing(50);
        sizeSlider.setBounds(60, 312, 120, 40);
        toolBar.add(sizeSlider);
        lenLable.setHorizontalAlignment(JLabel.LEFT);
        lenLable.setBounds(185, 310, 50, 40);
        toolBar.add(lenLable);

        compLable.setHorizontalAlignment(JLabel.LEFT);
        compLable.setBounds(15, 360, 245, 40);
        toolBar.add(compLable);

        acLable.setHorizontalAlignment(JLabel.LEFT);
        acLable.setBounds(15, 390, 245, 40);
        toolBar.add(acLable);

        complexity.setBounds(25, 450, 200, 125);
        complexity.setEditable(false);
        toolBar.add(complexity);

        backButton.setBounds(75, 600, 100, 25);
        toolBar.add(backButton);

        jFrame.add(toolBar);

        canvas = new graph_canvas(this);
        canvas.setBounds(277, 35, SIZE + 1, SIZE + 1);
        canvas.setBorder(BorderFactory.createLineBorder(Color.black));

        jFrame.add(canvas);
        jFrame.setVisible(true);
        visual_sorting thisObject = this;
        // Now All Listeners...
        algos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                makeArray();
                switch (algos.getSelectedIndex()) {
                    case 0:
                        curAlgo = new SelectionSort(thisObject);
                        break;
                    case 1:
                        curAlgo = new BubbleSort(thisObject);
                        break;
                    case 2:
                        curAlgo = new CocktailSort(thisObject);
                        break;
                    case 3:
                        curAlgo = new OddevenSort(thisObject);
                        break;
                    case 4:
                        curAlgo = new InsertionSort(thisObject);
                        break;
                    case 5:
                        curAlgo = new TimSort(thisObject);
                        break;
                    case 6:
                        curAlgo = new QuickSort(thisObject);
                        break;
                    case 7:
                        curAlgo = new HeapSort(thisObject);
                        break;
                    case 8:
                        curAlgo = new MergeSort(thisObject);
                        break;
                    case 9:
                        curAlgo = new PigeonholeSort(thisObject);
                        break;
                    case 10:
                        curAlgo = new RadixSort(thisObject);
                        break;
                    default:
                        break;
                }
                complexity.setText(curAlgo.getSortComplexity());
                update();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shuffled) {
                    sorting = true;
                    compare = 0;
                    access = 0;
                } else {
                    makeArray();
                }
            }
        });

        suffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeArray();
                update();
            }
        });

        delaySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                spd = (int) delaySlider.getValue();
                msLable.setText(spd + " ms");
            }
        });

        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newSize = sizeSlider.getValue();
                len = newSize * 50;
                lenLable.setText(len + "");
                makeArray();
                update();
            }
        });

        backButton.addActionListener(new ActionListener() {
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

        sortArray();
    }

    public void delay() {
        try {
            Thread.sleep(spd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        width = SIZE / len;
        // repaint canvas
        canvas.repaint();
        compLable.setText("Comparisons      : " + compare);
        acLable.setText("Array Accesses : " + access);
    }

    public void stop() {
        while (!sorting) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sortArray();
    }

    public void reset() {
        sorting = false;
        cur = -1;
        num = -1;
        update();
    }

    public void sortArray() {
        if (sorting) {
            curAlgo.sort_method();
        }
        reset();
        stop();
    }

//    public static void main(String[] args) {
//        new visual_sorting();
//    }
}
