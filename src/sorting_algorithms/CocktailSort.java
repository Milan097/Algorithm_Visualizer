package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class CocktailSort extends Sort {
    String space = "               ";

    public CocktailSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(n)\n" + space + "Worst Case: O(n^2)\n" + space + "Average: O(n^2)";
    }

    @Override
    public void sort_method() {
        boolean swapped = true;
        int start = 0;
        int end = vs.len - 1;
        if (!vs.sorting)
            return;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (!vs.sorting)
                    return;
                vs.num = i;
                vs.cur = i + 1;
                vs.compare++;
                vs.access += 2;
                vs.update();
                vs.delay();
                if (vs.array[i] > vs.array[i + 1]) {
                    swap(i, i + 1);
                    swapped = true;
                }
            }

            if (!swapped)
                break;

            swapped = false;

            --end;
            for (int i = end - 1; i >= start; i--) {
                if (!vs.sorting)
                    return;
                vs.num = i + 1;
                vs.cur = i;
                vs.compare++;
                vs.access += 2;
                vs.update();
                vs.delay();
                if (vs.array[i] > vs.array[i + 1]) {
                    swap(i, i + 1);
                    swapped = true;
                }
            }

            ++start;
        }
    }
}
