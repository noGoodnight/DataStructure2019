package Chapter7;

public class Sort {

    public static void binarySort(int[] sources) {
        for (int i = 1; i < sources.length; i++) {
            sources = binaryInsert(sources, i + 1, sources[i]);
        }
    }

    private static int[] binaryInsert(int[] sources, int currentSize, int key) {
        int trueSize = currentSize - 1;
        int left = 0;
        int right = trueSize - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (sources[mid] < key) {
                left = mid + 1;
            } else if (sources[mid] > key) {
                right = mid - 1;
            } else {
                return insert(sources, key, mid + 1, currentSize - 1);
            }
        }
        return insert(sources, key, left, currentSize - 1);
    }

    private static int[] insert(int[] sources, int key, int from, int to) {
        for (int i = to; i > from; i--) {
            sources[i] = sources[i - 1];
        }
        sources[from] = key;
        return sources;
    }

    public static void quickSort(int[] sources, int left, int right) {
        int pivot = (left + right) / 2;
        int standard = sources[pivot];
        int i = left;
        int j = right;

        sources[pivot] = sources[left];
        sources[left] = standard;

        while (i != j) {
            if (sources[i] == standard) {
                if (sources[j] >= standard) {
                    j--;
                } else {
                    sources[i] = sources[j];
                    sources[j] = standard;
                    i++;
                }
            } else {
                if (sources[i] <= standard) {
                    i++;
                } else {
                    sources[j] = sources[i];
                    sources[i] = standard;
                    j--;
                }
            }
        }

        pivot = i;

        if (left != right) {
            if (pivot > left) {
                quickSort(sources, left, pivot - 1);
            }
            if (pivot < right) {
                quickSort(sources, pivot + 1, right);
            }
        }
    }

    public static void shellSort(int[] sources) {
        int gap = sources.length / 2;

        while (gap > 0) {
            for (int i = 0; i < gap; i++) {
                for (int j = 0; i + j * gap < sources.length; j++) {
                    int k = 0;
                    while (k != j) {
                        if (sources[i + k * gap] > sources[i + j * gap]) {
                            int num = sources[i + j * gap];
                            int location = i + k * gap;
                            int l = j;
                            while (k < l) {
                                sources[i + l * gap] = sources[i + (l-1) * gap];
                                l--;
                            }
                            sources[location] = num;
                            break;
                        }
                        k++;
                    }
                }
            }
            gap--;
        }
    }

}