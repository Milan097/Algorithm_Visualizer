package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class OddevenSort extends Sort {
    String space = "               ";

    public OddevenSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(n)\n" + space + "Worst Case: O(n^2)\n" + space + "Average: O(n^2)";
    }

    @Override
    public void sort_method() {
        boolean isSorted = false;
        if (!vs.sorting)
            return;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i <= vs.len - 2; i += 2) {
                if (!vs.sorting)
                    return;
                vs.cur = i;
                vs.num = i + 1;
                vs.compare++;
                vs.access += 2;
                vs.update();
                vs.delay();
                if (vs.array[i] > vs.array[i + 1]) {
                    swap(i, i + 1);
                    isSorted = false;
                }
            }
            for (int i = 0; i <= vs.len - 2; i += 2) {
                if (!vs.sorting)
                    return;
                vs.cur = i;
                vs.num = i + 1;
                vs.compare++;
                vs.access += 2;
                vs.update();
                vs.delay();
                if (vs.array[i] > vs.array[i + 1]) {
                    swap(i, i + 1);
                    isSorted = false;
                }
            }
        }
    }
}
