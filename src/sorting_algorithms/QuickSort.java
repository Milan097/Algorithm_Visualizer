package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class QuickSort extends Sort {
    String space = "               ";

    public QuickSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(nlogn)\n" + space + "Worst Case: O(n^2)\n" + space + "Average: O(nlogn)";
    }

    @Override
    public void sort_method() {
        if (!vs.sorting)
            return;
        int low = 0;
        int high = vs.len - 1;
        quick(low, high);
    }

    public void quick(int low, int high) {
        if (low > high)
            return;
        if (!vs.sorting)
            return;
        vs.cur = high;
        int p = partition(low, high);
        quick(low, p - 1);
        quick(p + 1, high);
    }

    public int partition(int low, int high) {
        int pivot = vs.array[high];
        vs.access++;
        int i = low - 1;
        for (int j = low; j <= high; j++) {
            if (!vs.sorting)
                return 0;
            if (vs.array[j] < pivot) {
                i++;
                swap(i, j);
            }
            vs.access++;
            vs.compare++;
            vs.num = j;
            vs.update();
            vs.delay();
        }
        swap(i + 1, high);
        return (i + 1);
    }
}
