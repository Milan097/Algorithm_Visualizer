package sorting_algorithms;

import sorting_visualizer.visual_sorting;

public class HeapSort extends Sort {
    String space = "               ";

    public HeapSort(visual_sorting vs) {
        this.vs = vs;
        this.sortComplexity = "\n\n" + space + "Best Case: O(nlogn)\n" + space + "Worst Case: O(nlogn)\n" + space + "Average: O(nlogn)";
    }

    @Override
    public void sort_method() {
        if (!vs.sorting)
            return;
        heapSort();
    }

    public void heapSort() {
        heapify(vs.len);
        int end = vs.len - 1;
        while (end > 0 && vs.sorting) {
            vs.num = end;
            swap(end, 0);
            end--;
            shiftDown(0, end);
            vs.update();
            vs.delay();
        }
    }

    public void heapify(int n) {
        int start = iParent(n - 1);
        while (start >= 0 && vs.sorting) {
            shiftDown(start, n - 1);
            start--;
            vs.update();
            vs.delay();
        }
    }

    public void shiftDown(int start, int end) {
        int root = start;
        while (iLeftChild(root) <= end && vs.sorting) {
            int child = iLeftChild(root);
            int largest = root;
            vs.cur = root;
            if (vs.array[largest] < vs.array[child])
                largest = child;
            if (child + 1 <= end && vs.array[largest] < vs.array[child + 1])
                largest = child + 1;
            if (largest == root)
                return;
            else {
                swap(root, largest);
                vs.cur = root;
                root = largest;
            }
            vs.compare += 3;
            vs.access += 4;
            vs.update();
            vs.delay();
        }
    }

    public int iParent(int i) {
        return ((i - 1) / 2);
    }

    public int iLeftChild(int i) {
        return 2 * i + 1;
    }
}
