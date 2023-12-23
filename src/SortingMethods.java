    import java.util.ArrayList;
    import java.util.List;
    public class SortingMethods<E extends Comparable<E>> implements Demonstrable {
    private static final int UPPER_LIMIT_FOR_SHOW = 15;

    StringBuilder steps = new StringBuilder();

    /** DO NOT CHANGE THIS METHOD */
    public String show() {

        steps.setLength(steps.length() - 1);

        return steps.toString();
    }

    /**

     * This is a bottom-up implementation of the mergesort algorithm where multiple small sublists of the same size

     * are merged in a single pass. The demonstration of this algorithm is done by showing each stage of the merge on

     * sublists of the same size (except possibly the very last sublist, if the input list has odd number of elements).

     * For example, if the input is [5, 2, 4, 6, 7, 1, 3], a call to show() returns the string:

     * <p>

     * [5, 2, 4, 6, 7, 1, 3]

     * [2, 5, 4, 6, 1, 7, 3]

     * [2, 4, 5, 6, 1, 3, 7]

     * [1, 2, 3, 4, 5, 6, 7]

     * </p>

     * The first line shows the input, the second line demonstrates the pairwise merges creating sorted sublists of

     * size 2 (except the very last sublist, which is [3]), the third line demonstrates merges of size-2 lists together

     * to create [2, 4, 5, 6] and [1, 3, 7] (again, the very last sublist was not a pair because the input has an odd

     * number of elements), and the fourth line merges the size-4 sublists together, which is the sorted list.

     *

     * @param elements the input list to be sorted

     */

    public void mergeSort(List<E> elements) {

        int size = elements.size(); // Get the size of the list

        List<E> helperList = new ArrayList<>(elements); // Create an helperList list to help with merging

        // Iterate over sublist sizes:
        for (int i = 1; i < size; i *= 2) {

            // Iterate over sublists; start at 0, increase by double the current length each time
            for (int left = 0; left < size - i; left += i * 2) {

                int mid = left + i - 1; // Calculate the midpoint

                int right = Math.min(left + i * 2 - 1, size - 1); // Calculate the end of the second sublist

                merge(elements, helperList, left, mid, right); // Merge the two sublists

            }

            if (elements.size() < UPPER_LIMIT_FOR_SHOW) {

                steps.append(elements).append('\n');

            }
        }
    }
    //I will implement the Bottom-Up Merge sort algorithm
    private void merge(List<E> elements, List<E> helperList, int left, int mid, int right) {

        // Copy elements from the original list to the helperList
        for (int i = left; i <= right; i++) {

            helperList.set(i, elements.get(i));

        }

        int i = left, j = mid + 1; // Set up pointers for each sublist

        // Merge the sublists back into the original list
        for (int k = left; k <= right; k++) {

            if (i > mid)
                elements.set(k, helperList.get(j++)); // Take from the second sublist if the first is exhausted

            else if (j > right)
                elements.set(k, helperList.get(i++)); // Take from the first sublist if the second is exhausted

            else if (helperList.get(j).compareTo(helperList.get(i)) < 0)
                elements.set(k, helperList.get(j++)); // Take from the second sublist if its current element is smaller

            else
                elements.set(k, helperList.get(i++)); // Take from the first sublist otherwise

        }
    }

    /**

     * This is an implementation of the insertion sort algorithm. The steps are demonstrated by adding a line to the

     * string representation whenever the unsorted portion of the input list decreases in size by 1. For example, if

     * the input is [8, 3, 15, 0, 9], then show() returns the string:

     * <p>

     * [8, 3, 15, 0, 9]

     * [3, 8, 15, 0, 9]

     * [3, 8, 15, 0, 9]

     * [0, 3, 8, 15, 9]

     * [0, 3, 8, 9, 15]

     * </p>

     *

     * @param elements the input list to be sorted

     */
    public void insertionSort(List<E> elements) {

        if (elements.size() < UPPER_LIMIT_FOR_SHOW) {

            steps.append(elements).append('\n');

        }

        // Starting from the second element as the boundary
        int boundary = 1;

        while (boundary < elements.size()) {

            // Inserting the element at the current boundary into its correct position
            insert(elements, boundary);

            boundary++;

            if (elements.size() < UPPER_LIMIT_FOR_SHOW) {

                steps.append(elements).append('\n');

            }
        }
    }

    protected void insert(List<E> elements, int boundary) {

        // Remove the element at the boundary and keep it for insertion
        E toBeInserted = elements.remove(boundary);

        int check = boundary - 1;

        // Find the correct position to insert the element
        while (check >= 0 && toBeInserted.compareTo(elements.get(check)) < 0) {

            check--;

        }

        // Insert the element at the correct position
        elements.add(check + 1, toBeInserted);
    }

    public void selectionSort(List<E> elements) {

        if (elements.size() < UPPER_LIMIT_FOR_SHOW) {

            steps.append(elements).append('\n');

        }

        int boundary = 0;

        while (boundary < elements.size() - 1) {

            int minIndex = findMinIndex(elements, boundary);

            swap(elements, boundary++, minIndex);

            if (elements.size() < UPPER_LIMIT_FOR_SHOW)

                steps.append(elements).append('\n');

        }

    }
    private int findMinIndex(List<E> elements, int boundary) {

        int minIndex = boundary;

        if (boundary == elements.size() - 1)

            return minIndex;

        E min = elements.get(minIndex);

        for (int i = boundary + 1; i < elements.size(); i++) {

            E e = elements.get(i);

            if (e.compareTo(min) < 0) {min = e; minIndex = i;}

        }

        return minIndex;

    }

    private void swap(List<E> elements, int i, int j) {

        if (i < 0 || j < 0 || i >= elements.size() || j >= elements.size()) {

            String err = String.format("Cannot swap elements between positions %d and %d in list of %d elements.",

                    i, j, elements.size());

            throw new IndexOutOfBoundsException(err);

        }

        E tmp = elements.get(i);

        elements.set(i, elements.get(j));

        elements.set(j, tmp);

    }

    }
