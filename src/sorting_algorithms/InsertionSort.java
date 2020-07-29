package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class InsertionSort extends Sort {
    String space = "               ";

    public InsertionSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(n)\n" + space + "Worst Case: O(n^2)\n" + space + "Average: O(n^2)";
    }

    @Override
    public void sort_method() {
        if (!vs.sorting)
            return;
        for (int i = 1; i < vs.len; i++) {
            int key = vs.array[i];
            int j = i - 1;
            vs.cur = j;
            vs.access++;
            while (j >= 0 && vs.array[j] > key) {
                if (!vs.sorting)
                    return;
                vs.array[j + 1] = vs.array[j];
                vs.num = j;
                vs.access += 3;
                vs.compare++;
                vs.update();
                vs.delay();
                j--;
            }
            vs.array[j + 1] = key;
            vs.access++;
            vs.update();
            vs.delay();
        }
    }
}
