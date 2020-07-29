package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class PigeonholeSort extends Sort {
    String space = "               ";

    public PigeonholeSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: --\n" + space + "Worst Case: O(n+2^k)\n" + space + "Average: O(n+2^k))";
    }

    @Override
    public void sort_method() {
        int min = 0;
        int size = vs.len - min + 1;
        int[] holes = new int[size];
        for (int x : vs.array) {
            vs.access++;
            holes[x - min]++;
        }

        int i = 0;
        for (int count = 0; count < size; count++) {
            while (holes[count] > 0 && vs.sorting) {
                holes[count]--;
                vs.cur = i;
                vs.array[i] = count + min;
                vs.access++;
                i++;
                vs.update();
                vs.delay();
            }
        }
    }
}
