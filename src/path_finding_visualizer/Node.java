package path_finding_visualizer;

public class Node {

    // 0 = start, 1 = finish, 2 = wall, 3 = empty, 4 = checked, 5 = finalpath
    private int cellType = 0;
    private int hops;
    private int x;
    private int y;
    private int lastX;
    private int lastY;
    private double dToEnd = 0;
    private visual_path_finding vpf;

    public Node(visual_path_finding vpf, int type, int x, int y) {
        this.vpf = vpf;
        cellType = type;
        this.x = x;
        this.y = y;
        hops = -1;
    }

    public double getEuclidDist() {        //CALCULATES THE EUCLIDIAN DISTANCE TO THE FINISH NODE
        int xDif = Math.abs(x - vpf.finishX);
        int yDif = Math.abs(y - vpf.finishY);
        dToEnd = Math.sqrt((xDif * xDif) + (yDif * yDif));
        return dToEnd;
    }

    public int getCellType() {
        return cellType;
    }

    public void setCellType(int cellType) {
        this.cellType = cellType;
    }

    public int getHops() {
        return hops;
    }

    public void setHops(int hops) {
        this.hops = hops;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

}
