package path_finding_visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Map extends JPanel implements MouseListener, MouseMotionListener {

    private visual_path_finding vpf;

    public Map(visual_path_finding vpf) {
        this.vpf = vpf;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < vpf.cells; x++) {
            for (int y = 0; y < vpf.cells; y++) {
                switch (vpf.map[x][y].getCellType()) {
                    case 0:
                        g.setColor(Color.GREEN);
                        break;
                    case 1:
                        g.setColor(Color.RED);
                        break;
                    case 2:
                        g.setColor(Color.BLACK);
                        break;
                    case 3:
                        g.setColor(Color.WHITE);
                        break;
                    case 4:
                        g.setColor(Color.CYAN);
                        break;
                    case 5:
                        g.setColor(Color.YELLOW);
                        break;
                }

                g.fillRect(x * vpf.CSIZE, y * vpf.CSIZE, vpf.CSIZE, vpf.CSIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x * vpf.CSIZE, y * vpf.CSIZE, vpf.CSIZE, vpf.CSIZE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        vpf.resetMap();
        try {
            int x = e.getX() / vpf.CSIZE;
            int y = e.getY() / vpf.CSIZE;
            Node current = vpf.map[x][y];
            switch (vpf.tool) {
                case 0: {
                    if (current.getCellType() != 2) {
                        if (vpf.startX > -1 && vpf.startY > -1) {
                            vpf.map[vpf.startX][vpf.startY].setCellType(3);
                            vpf.map[vpf.startX][vpf.startY].setHops(-1);
                        }
                        current.setHops(0);
                        if (current.getCellType() == 1) {
                            vpf.finishX = -1;
                            vpf.finishY = -1;
                        }
                        vpf.startX = x;
                        vpf.startY = y;
                        current.setCellType(0);
                    }
                    break;
                }
                case 1: {
                    if (current.getCellType() != 2) {
                        if (vpf.finishX > -1 && vpf.finishY > -1)
                            vpf.map[vpf.finishX][vpf.finishY].setCellType(3);
                        vpf.finishX = x;
                        vpf.finishY = y;
                        if (current.getCellType() == 0) {
                            vpf.startX = -1;
                            vpf.startY = -1;
                        }
                        current.setCellType(1);
                    }
                    break;
                }
                default: {
                    if (current.getCellType() != 0 && current.getCellType() != 1)
                        current.setCellType(vpf.tool);
                    break;
                }
            }
            vpf.update();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            int x = e.getX() / vpf.CSIZE;
            int y = e.getY() / vpf.CSIZE;
            Node current = vpf.map[x][y];
            if ((vpf.tool == 2 || vpf.tool == 3) && (current.getCellType() != 0 && current.getCellType() != 1))
                current.setCellType(vpf.tool);
            vpf.update();
        } catch (ArrayIndexOutOfBoundsException aie) {
            System.out.println("OutSide of Map");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
