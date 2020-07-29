package path_finding_algorithms;

import path_finding_visualizer.Node;
import path_finding_visualizer.visual_path_finding;

import java.util.ArrayList;

public class PathFinding {

    visual_path_finding vpf;

    public PathFinding() {
    }

    public void findPath() {
    }


    public ArrayList<Node> exploreNeighbors(Node current, int hops) {
        ArrayList<Node> explored = new ArrayList<Node>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int xBound = current.getX() + i;
                int yBound = current.getY() + j;
                if (xBound > -1 && xBound < vpf.cells && yBound > -1 && yBound < vpf.cells) {
                    Node neighbour = vpf.map[xBound][yBound];
                    if ((neighbour.getHops() == -1 || neighbour.getHops() > hops) && neighbour.getCellType() != 2) {
                        explore(neighbour, current.getX(), current.getY(), hops);
                        explored.add(neighbour);
                    }
                }
            }
        }

        return explored;
    }

    public void explore(Node current, int lastx, int lasty, int hops) {    //EXPLORE A NODE
        if (current.getCellType() != 0 && current.getCellType() != 1) {
            current.setCellType(4);
        }
        current.setLastX(lastx);
        current.setLastY(lasty);
        current.setHops(hops);
        vpf.checks++;
        if (current.getCellType() == 1) {
            backtrack(current.getLastX(), current.getLastY(), hops);
        }
    }

    public void backtrack(int lx, int ly, int hops) {    //BACKTRACK
        vpf.length = hops;
        while (hops > 0) {
            Node current = vpf.map[lx][ly];
            current.setCellType(5);
            lx = current.getLastX();
            ly = current.getLastY();
            hops--;
        }
        vpf.solving = false;
    }

}
