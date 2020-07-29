package path_finding_algorithms;

import path_finding_visualizer.Node;
import path_finding_visualizer.visual_path_finding;

import java.util.ArrayList;

public class Dijakstra extends PathFinding {

    public Dijakstra(visual_path_finding vpf) {
        this.vpf = vpf;
    }

    @Override
    public void findPath() {
        ArrayList<Node> priority = new ArrayList<Node>();

        priority.add(vpf.map[vpf.startX][vpf.startY]);
        while (vpf.solving) {
            if (priority.size() <= 0) {
                vpf.solving = false;
                break;
            }
            int hops = priority.get(0).getHops() + 1;
            ArrayList<Node> explored = exploreNeighbors(priority.get(0), hops);    //CREATE AN ARRAYLIST OF NODES THAT WERE EXPLORED
            if (explored.size() > 0) {
                priority.remove(0);
                priority.addAll(explored);
                vpf.update();
                vpf.delay();
            } else {
                priority.remove(0);
            }
        }
    }
}
