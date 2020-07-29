package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class SelectionSort extends Sort {
    String space = "               ";

    public SelectionSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(n^2)\n" + space + "Worst Case: O(n^2)\n" + space + "Average: O(n^2)";
    }

    @Override
    public void sort_method() {
//        System.out.println("Selection Sort");
        if (!vs.sorting)
            return;
        for (int i = 0; i < vs.len; i++) {
            int minElement = vs.array[i];
            vs.access++;
            int minPos = i;
            vs.cur = i;
            for (int j = i; j < vs.len; j++) {
                if (!vs.sorting)
                    return;
                if (vs.array[j] < minElement) {
                    minElement = vs.array[j];
                    minPos = j;
                }
                vs.access += 2;
                vs.compare++;
                vs.num = j;
                vs.update();
                vs.delay();
            }
            if (i != minPos)
                swap(i, minPos);
        }
    }
}
