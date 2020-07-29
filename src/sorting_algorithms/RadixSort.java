package sorting_algorithms;

import sorting_visualizer.visual_sorting;

import java.util.Arrays;

public class RadixSort extends Sort {
    String space = "               ";

    public RadixSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: --\n" + space + "Worst Case: O(n*(k/d))\n" + space + "Average: O(n*(k/d))";
    }

    @Override
    public void sort_method() {
        int m = getMax();

        for (int exp = 1; m / exp > 0; exp *= 10) {
            if (!vs.sorting)
                return;
            countSort(exp);
            vs.delay();
            vs.update();
        }
    }

    private int getMax() {
        int mx = vs.array[0];
        vs.access++;
        for (int i = 1; i < vs.len; i++) {
            vs.compare++;
            vs.access++;
            if (mx < vs.array[i]) {
                mx = vs.array[i];
                vs.access++;
                vs.access += 2;
            }
        }
        return mx;
    }

    private void countSort(int exp) {
        int[] output = new int[vs.len];
        int[] count = new int[vs.len];
        Arrays.fill(count, 0);

        for (int i = 0; i < vs.len; i++) {
            count[(vs.array[i] / exp) % 10]++;
            vs.access++;
        }

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = vs.len - 1; i >= 0; i--) {
            output[count[(vs.array[i] / exp) % 10] - 1] = vs.array[i];
            count[(vs.array[i] / exp) % 10]--;
            vs.access += 3;
        }

        for (int i = 0; i < vs.len; i++) {
            if (!vs.sorting)
                return;
            vs.array[i] = output[i];
            vs.access++;
            vs.cur = i;
            vs.update();
            vs.delay();
        }
    }
}
