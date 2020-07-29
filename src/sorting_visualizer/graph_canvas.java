/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting_visualizer;

import javax.swing.*;
import java.awt.*;

/**
 * @author MILAN BHAI
 */
public class graph_canvas extends JPanel {
    public visual_sorting vs;

    public graph_canvas(visual_sorting vs) {
        this.vs = vs;
        setBackground(Color.lightGray);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < vs.len; i++) {
            int curH = vs.array[i] * vs.width;
            g.setColor(Color.gray);
            if (i == vs.num) {
                g.setColor(Color.red);
            }
            if (i == vs.cur) {
                g.setColor(Color.blue);
            }
            g.fillRect(i * vs.width, vs.SIZE - curH, vs.width, curH);
            g.setColor(Color.black);
            g.fillRect(i * vs.width, vs.SIZE - curH, vs.width, vs.width);
            g.setColor(Color.lightGray);
            g.drawRect(i * vs.width, vs.SIZE - curH, vs.width, curH);
        }
    }
}
