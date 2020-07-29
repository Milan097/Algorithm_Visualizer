/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_visualizer;

import path_finding_visualizer.visual_path_finding;
import sorting_visualizer.visual_sorting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author MILAN BHAI
 */
public class Algorithm_Visualizer {

    JFrame algo_visualizer;

    public Algorithm_Visualizer() {
        algo_visualizer = new JFrame();
        algo_visualizer.setSize(400, 200);
        algo_visualizer.setTitle("Algorithm Visualizer");
        algo_visualizer.setLocationRelativeTo(null);
        algo_visualizer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        algo_visualizer.setLayout(null);


        JLabel selection = new JLabel("Select Algorithm");
        selection.setBounds(153, 30, 100, 25);
        JButton sortButton = new JButton("Sorting");
        JButton pathButton = new JButton("Path Finding");
        sortButton.setBounds(60, 75, 120, 25);
        pathButton.setBounds(220, 75, 120, 25);

        algo_visualizer.add(sortButton);
        algo_visualizer.add(pathButton);
        algo_visualizer.add(selection);
        algo_visualizer.setResizable(false);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algo_visualizer.setVisible(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new visual_sorting();
                    }
                }).start();
            }
        });

        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algo_visualizer.setVisible(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new visual_path_finding();
                    }
                }).start();
            }
        });

        algo_visualizer.setVisible(true);
    }

    public static void main(String[] args) {
        new Algorithm_Visualizer();
    }

}
