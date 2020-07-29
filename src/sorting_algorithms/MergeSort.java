package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class MergeSort extends Sort {
    String space = "               ";

    public MergeSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(nlogn)\n" + space + "Worst Case: O(nlogn)\n" + space + "Average: O(nlogn)";
    }

    @Override
    public void sort_method() {
        if (!vs.sorting)
            return;
        int left = 0;
        int right = vs.len - 1;
        mergeSort(left, right);
    }

    private void mergeSort(int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            vs.cur = r;
            mergeSort(l, mid);
            mergeSort(mid + 1, r);
            merge(l, mid, r);
        }
    }

    private void merge(int l, int mid, int r) {
        int l1 = mid - l + 1;
        int l2 = r - mid;

        int[] L = new int[l1];
        int[] R = new int[l2];

        for (int i = 0; i < l1; i++)
            L[i] = vs.array[l + i];
        for (int j = 0; j < l2; j++)
            R[j] = vs.array[mid + 1 + j];
        vs.access += (l1 + l2);

        int i = 0, j = 0, k = l;
        while (i < l1 && j < l2) {
            if (!vs.sorting)
                return;
            if (L[i] > R[j]) {
                vs.array[k] = R[j];
                j++;
            } else {
                vs.array[k] = L[i];
                i++;
            }
            k++;
            vs.access++;
            vs.compare++;
            vs.num = k;
            vs.update();
            vs.delay();
        }

        while (i < l1) {
            vs.array[k] = L[i];
            i++;
            k++;
            vs.access++;
            vs.num = k;
            vs.update();
            vs.delay();
        }
        while (j < l2) {
            vs.array[k] = R[j];
            j++;
            k++;
            vs.access++;
            vs.num = k;
            vs.update();
            vs.delay();
        }
    }
}
