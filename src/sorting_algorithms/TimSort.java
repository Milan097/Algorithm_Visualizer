package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class TimSort extends Sort {
    String space = "               ";
    int RUN = 32;
    int n;

    public TimSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(n)\n" + space + "Worst Case: O(nlogn)\n" + space + "Average: O(nlogn)";
    }

    @Override
    public void sort_method() {
        n = vs.len;
        if (!vs.sorting)
            return;
        for (int i = 0; i < n; i += RUN) {
            _insertionSort(i, Math.min(i + RUN - 1, n - 1));
        }

        for (int size = RUN; size < n; size = 2 * size) {
            if (!vs.sorting)
                return;
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(n - 1, left + size - 1);
                int right = Math.min((left + (2 * size) - 1), (n - 1));
                _merge(left, mid, right);
            }
        }
    }

    public void _insertionSort(int left, int right) {
        if (!vs.sorting)
            return;
        for (int i = left + 1; i <= right; i++) {
            int key = vs.array[i];
            int j = i - 1;
            vs.cur = j;
            vs.access++;
            while (j >= left && vs.array[j] > key) {
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

    private void _merge(int l, int mid, int r) {
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
