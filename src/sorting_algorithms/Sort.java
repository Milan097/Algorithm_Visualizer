package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class Sort {
    String sortComplexity = "";
    visual_sorting vs;

    public Sort() {
    }

    public void sort_method() {
    }

    public void swap(int i, int j) {
        int temp = vs.array[i];
        vs.array[i] = vs.array[j];
        vs.array[j] = temp;
        vs.access += 4;
    }

    public String getSortComplexity() {
        return sortComplexity;
    }
}
