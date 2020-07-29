package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class BubbleSort extends Sort {
    String space = "               ";

    public BubbleSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(n)\n" + space + "Worst Case: O(n^2)\n" + space + "Average: O(n^2)";
    }

    @Override
    public void sort_method() {
        if (!vs.sorting)
            return;
        for (int i = 0; i < vs.len; i++) {
            for (int j = 0; j < vs.len - i - 1; j++) {
                if (!vs.sorting)
                    return;
                vs.cur = j;
                vs.num = j + 1;
                vs.compare++;
                vs.access += 2;
                vs.update();
                vs.delay();
                if (vs.array[j] > vs.array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }
}
