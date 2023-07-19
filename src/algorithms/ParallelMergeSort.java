package algorithms;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {
    private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void sort(Comparable[] array) {
        FORK_JOIN_POOL.invoke(new MergeSortTask(array, 0, array.length - 1));
    }

    private static class MergeSortTask extends RecursiveAction {

        private final Comparable[] array;
        private final int left;
        private final int right;

        public MergeSortTask(Comparable[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }


        @Override
        protected void compute() {
            if (left < right) {
                int mid = (left + right) / 2;

                // Division of the two sorted parts
                MergeSortTask leftTask = new MergeSortTask(array, left, mid);
                MergeSortTask rightTask = new MergeSortTask(array, mid + 1, right);

                // Execution of the subtasks in parallel
                invokeAll(leftTask, rightTask);


                // Merging the two sorted parts
                merge(left, mid, right);
            }
        }

        private void merge(int left, int mid, int right) {
            Comparable[] temp = Arrays.copyOfRange(array, left, right + 1);

            int i = 0;
            int j = mid - left + 1;
            int k = left;

            while (i <= mid - left && j <= right - left) {
                if (temp[i].compareTo(temp[j]) <= 0) {
                    array[k] = temp[i];
                    i++;
                } else {
                    array[k] = temp[j];
                    j++;
                }
                k++;
            }

            while (i <= mid - left) {
                array[k] = temp[i];
                i++;
                k++;
            }

        }
    }
}


/*
    public static void main(String[] args) {
        Integer[] array = { 5, 2, 9, 1, 5, 6, 3, 7, 8 };

        System.out.println("Unsortiertes Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        sort(array);

        System.out.println("Sortiertes Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
 */