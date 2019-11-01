/**
 * sort
 */
import java.util.Arrays;

public class sort {
    static int[] sorted; // 병합정렬을 위한 전역배열

    // 선택정렬
    public static void selectionSort(int[] array) {
        int min = 0,index = 0,temp = 0;

        for(int i=0;i<array.length;i++) {
            min = 9999;
            for(int j=i;j<array.length;j++) {
                if(min>array[j]) {
                    min = array[j];
                    index = j;
                }
            }
            temp = array[i];
            array[i] = min;
            array[index] = temp;
        }
    }

    // 거품정렬
    public static void bubbleSort(int[] array) {
        int temp=0;

        for(int i=0;i<array.length;i++) {
            for(int j=0;j<array.length-(i+1);j++) {
                if(array[j]>array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    // 삽입정렬
    public static void insertionSort(int[] array) {
        int temp=0;

        for(int i=0;i<array.length-1;i++) {
            for(int j=i;j>=0;j--) {
                if(array[j]>array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    // 퀵정렬
    public static void quickSort(int[] array, int start, int end) {
        if(start >= end) return;

        int key = start;
        int i=start+1;
        int j=end;
        int temp=0;

        while(i<=j) {
            while(i <= end && array[i] <= array[key]) i++;
            while(j > start && array[j] >= array[key]) j--;

            if(i>j) {
                temp = array[j];
                array[j] = array[key];
                array[key] = temp;
            } else {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }

        quickSort(array, start, j-1);
        quickSort(array, j+1, end);

    }

    // 병합과정
    public static void merge(int[] array, int m, int middle, int n) {
        int i=m, j=middle+1, k=m;

        while(i<=middle && j<=n) {
            if(array[i]<=array[j]) {
                sorted[k] = array[i];
                i++;
            } else {
                sorted[k] = array[j];
                j++;
            }
            k++;
        }

        if(i > middle) {
            for(int t=j;t<=n;t++) {
                sorted[k] = array[t];
                k++;
            }
        } else {
            for(int t=i;t<=middle;t++) {
                sorted[k] = array[t];
                k++;
            }
        }

        for(int t=m;t<=n;t++) {
            array[t] = sorted[t];
        }
    }

    // 병합정렬 (분할하여 병합)
    public static void mergeSort(int[] array, int m, int n) {
        if(m<n) {
            int middle = (m+n)/2;
            mergeSort(array,m,middle);
            mergeSort(array,middle+1,n);
            merge(array,m,middle,n);
        }
        
    }

    // 최대힙 (특정 노드 기준)
    public static void maxHeapify(int[] array, int i, int size) {
        int left = 2*i+1, right = 2*i+2;
        int large = i;

        if(left < size && array[left] > array[large]) large = left;
        if(right < size && array[right] > array[large]) large = right;
        
        if(large != i) {
            int temp = array[i];
            array[i] = array[large];
            array[large] = temp;

            if(large*2+1 < size) maxHeapify(array, large, size);
        }
    }

    // 최대힙 (전체 노드 기준 -> 전체 노드 중 상위 절반만 수행)
    public static void maxHeap(int[] array, int size) {
        for(int i=size/2-1;i>=0;i--) {
            maxHeapify(array, i, size);
        }
    }

    // 힙정렬
    public static void heapSort(int[] array) {
        // 최초 전체노드 최대힙 수행
        maxHeap(array, array.length);
        // 루트(가장큰값)을 끝으로
        int temp = array[array.length-1];
        array[array.length-1] = array[0];
        array[0] = temp;

        // 루트값만 변경되었으므로 루트에 대해서만 최대힙 수행
        for(int i=array.length-1;i>0;i--) {
            maxHeapify(array,0,i);
            temp = array[i-1];
            array[i-1] = array[0];
            array[0] = temp;
        }
    }

    // 계수정렬
    public static int[] countingSort(int[] array) {
        int[] count = new int[10];
        int[] return_array = new int[10];
        int index=0;

        for(int i=0;i<10;i++) {
            count[array[i]-1]++;
        }
        
        for(int i=0;i<count.length;i++) {
            if(count[i] != 0) {
                for(int j=0;j<count[i];j++) {
                    return_array[index++] = i+1;
                }
            }
        }

        return return_array;
    }

    public static void main(String[] args) {
        int[] selection = {1,10,5,9,2,4,7,3,8,6};
        int[] bubble = {1,10,5,9,2,4,7,3,8,6};
        int[] insertion = {1,10,5,9,2,4,7,3,8,6};
        int[] quick = {1,10,5,9,2,4,7,3,8,6};
        int[] merge = {1,10,5,9,2,4,7,3,8,6};
        int[] heap = {1,10,5,9,2,4,7,3,8,6};
        int[] counting = {1,10,5,9,2,4,7,3,8,6};

        sorted = new int[merge.length];

        selectionSort(selection);
        bubbleSort(bubble);
        insertionSort(insertion);
        quickSort(quick, 0, quick.length-1);
        mergeSort(merge, 0, merge.length-1);
        heapSort(heap);
        int[] count = countingSort(counting);

        System.out.print("Selection Sort  : ");
        System.out.println(Arrays.toString(selection));
        System.out.print("Bubble Sort  : ");
        System.out.println(Arrays.toString(bubble));
        System.out.print("Insertion Sort  : ");
        System.out.println(Arrays.toString(insertion));
        System.out.print("Quick Sort  : ");
        System.out.println(Arrays.toString(quick));
        System.out.print("Merge Sort  : ");
        System.out.println(Arrays.toString(merge));
        System.out.print("Heap Sort  : ");
        System.out.println(Arrays.toString(heap));
        System.out.print("Count Sort  : ");
        System.out.println(Arrays.toString(count));
    }
}