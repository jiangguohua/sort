import java.util.Random;
import java.util.Stack;

public class SortTest {
    private static final int quick_sort = 0;
    private static final int buble_sort = 1;
    private static final int select_sort = 2;
    private static final int shell_sort = 3;
    private static final int insert_sort = 4;
    private static final int double_select_sort = 5;
    private static final int quick_sort2 = 6;
    private static final int quick_sort3 = 7;
    
    
    private int[] target = null;
    private int[] original = null;
    Random rand = new Random();
    
    private void generateRandomArray(int len) {
        target = new int[len];
        original = new int[len];
        Random ran = new Random();
        for (int i = 0; i < len; i++) {
            original[i] = ran.nextInt(500000);
        }
    }
    
    public void setArrayCopy() {
        System.arraycopy(this.original, 0, this.target, 0, original.length);
    }
    
    int randInt(int l, int u) {
        return (rand.nextInt(u - l) + l);
    }
    
    public void quickSort3(int l, int u) {
        int i,m;
        if (l >= u) {
          return;
        }
        swap(l, randInt(l, u));
        m = l;
        for (i = l+1; i <= u; i++) {
            if (target[i] < target[l]) {
                swap(++m, i);
            }
        }
        swap(l, m);
        quickSort3(l, m-1);
        quickSort3(m+1, u);
    }
    
    public void diaplay() {
        if (target != null) {
            for(int i = 0; i < target.length; System.out.print(target[i]),i++) {
                if(i != 0) {
                   System.out.print("_");
                }
            }
            System.out.println("");
        }
        
    }
    
    public void sort(int method) {
        switch(method) {
            case quick_sort:
                quickSort(0, target.length - 1);
                break;
            case buble_sort:
                bubleSort();
                break;
            case select_sort:
                selectSort();
                break;
            case shell_sort:
                shellSort();
                break;
            case insert_sort:
                insertSort();
                break;
            case double_select_sort:
                doubleSelectSort();
                break;
            case quick_sort2:
                quickSort2(0, target.length - 1);
                break;
            case quick_sort3:
                quickSort3(0, target.length - 1);
                break;
            default:
                break;
        }
    }
    private void doubleSelectSort() {
        int min;
        int max;
        int min_index;
        int max_index;
        for (int i = 0; i < target.length / 2; i++) {
            min = target[i];
            min_index = i;
            max = target[target.length-1-i];
            max_index = target.length-1-i;
            for (int j = i; j < target.length - 1 - i; j++) {
                if (min > target[j]) {
                    min = target[j];
                    min_index = j;
                }
                if(max<target[j]) {
                    max = target[j];
                    max_index=j;
                }
            }
            if (i != min_index) {
                swap(i, min_index);
            }
            if (max_index != target.length-1-i) {
                swap(max_index,target.length-1-i);
            }
        }
    }
    
    private void quickSort2(int start, int end) {
        if(end - start < 9) {
          insertSort(start, end);
        } else {
            int median = medianOfThree(start, end);
            int index = partition2(start, end, median);
            quickSort2(start, index - 1);
            quickSort2(index + 1, end);
        }
    }
    
    public int partition2(int start, int end, int pivot) {
        int left = start;
        int right = end-1;
         while(true) {
            while(target[++left] < pivot);
            while(target[--right] > pivot);
            if (left >= right) {
              break;
            } else {
                swap(left, right);
            }
         }
         swap(left,end-1);
         return left;
    }
    
    private int medianOfThree(int start, int end) {
        int mid_index = (start + end) / 2;
        if (target[start] > target[end]) swap(start,end);
        if(target[start] > target[mid_index]) swap(start,mid_index);
        if(target[mid_index] > target[end]) swap(mid_index,end);
        swap(mid_index,end - 1);
        return target[end - 1];
    }
    
    private void insertSort() {
        insertSort(0,target.length -1);
    }
    
    private void insertSort(int start, int end) {
        for(int i = start + 1; i <= end; i++) {
            int tmp = target[i];
            int loop_2 = i - 1;
            for(; loop_2 >= 0; loop_2--) {
                if(target[loop_2] > tmp) {
                   target[loop_2 + 1] = target[loop_2];
                } else {
                  break;
                }
            }
            
            if (loop_2 != i - 1) target[loop_2 + 1] = tmp;
        }
    }
    
    private void selectSort() {
        int min;
        int min_index = 0;
        for(int i = 0; i < target.length - 1; i++) {
            min = target[i];
            min_index = i;
            for(int j = i + 1; j < target.length - 1; j++) {
                if(min > target[j]) {
                    min = target[j];
                    min_index = j;
                }
            }
            
            if (i != min_index) {
                swap(i, min_index);
            }
        }
    }
    
    private void bubleSort() {
        if (target.length <= 1) {
            return;
        } else if (target.length == 2) {
            if( target[0] > target[1]) swap(0,1);
        } else {
            for(int i = 0; i < target.length - 2; i ++)
                for(int j = target.length - 1; j > i; j--) {
                    if(target[j] < target[j-1])
                        swap(j,j-1);
                }
        }
    }
    private void quickSort(int start, int end) {
        if (end - start <= 0) {
            return; // noothing to do for one item
        } else {
            int boundrary = partition(start, end, target[end]);
            quickSort(start, boundrary - 1);
            quickSort(boundrary + 1, end);
        }
    }
    
    private void shellSort() {
        Stack<Integer> s = new Stack<Integer>();
        int step = 1;
        while(step < target.length) {
            s.push(new Integer(step));
            step = 3 * step + 1;
        }
        while(s.size() > 0) {
            Integer sStep = s.pop();
            for(int i = 0; i < target.length - sStep -1; i += sStep) {
                if(target[i] > target[i+sStep]) swap(i,i+sStep);
            }
        }
    }
    
    private void swap(int index1, int index2) {
        int tmp = target[index1];
        target[index1] = target[index2];
        target[index2] = tmp;
    }
    
    public int partition(int start, int end, int pivot) {
        int left = start - 1;
        int right = end;
         while (true) {
            while(target[++left] < pivot);
            while(right > 0 && target[--right] > pivot);
            if (left >= right) break;
            else
                swap(left, right);
         }
         swap(left,end);
         return left;
    }
    
    public static void main(String[] args) {
        SortTest qs = new SortTest();
        if (args.length < 1) {
            // we use default value 100
            qs.generateRandomArray(100);
        } else {
            qs.generateRandomArray(Integer.parseInt(args[0]));
        }
        System.out.println("Sort a array of " + Integer.parseInt(args[0]) + " items: ");
        
        // shell sort
        qs.setArrayCopy();
        //qs.diaplay();
        long bt = System.currentTimeMillis();
        qs.sort(shell_sort);
        long ut = System.currentTimeMillis() - bt;
        System.out.println("shell_sort: " + ut + "ms.");
        //qs.diaplay();
        
        // quick sort
        qs.setArrayCopy();
        //qs.diaplay();
         bt = System.currentTimeMillis();
        qs.sort(quick_sort);
        //qs.diaplay();
        ut = System.currentTimeMillis() - bt;
        System.out.println("quick_sort: " + ut + "ms.");
        
        // quick_insert sort
        qs.setArrayCopy();
        //qs.diaplay();
        bt = System.currentTimeMillis();
        qs.sort(quick_sort2);
        ut = System.currentTimeMillis() - bt;
        System.out.println("quick_insert: " + ut + "ms.");
        
        // quich sort 3
        qs.setArrayCopy();
        //qs.diaplay();
        bt = System.currentTimeMillis();
        qs.sort(quick_sort3);
        ut = System.currentTimeMillis() - bt;
        System.out.println("quick_sort_from_c: " + ut + "ms.");
        
        
        
/*  too slow for the following sort
bash-3.00$ java SortTest 20000
Sort a array of 20000 items:
shell_sort: 20ms.
quick_sort: 15ms.
quick_insert: 18ms.
quick_sort_from_c: 22ms.
insert_sort: 2126ms.
double_select_sort: 2525ms.
select_sort: 3334ms.
buble_sort: 5215ms.

bash-3.00$ java SortTest 80000
Sort a array of 80000 items:
shell_sort: 25ms.
quick_sort: 43ms.
quick_insert: 39ms.
quick_sort_from_c: 66ms.
insert_sort: 36415ms.
double_select_sort: 42599ms.
select_sort: 56747ms.
buble_sort: 87888ms.

        
        // insert sort
        qs.setArrayCopy();
        //qs.diaplay();
        bt = System.currentTimeMillis();
        qs.sort(insert_sort);
        ut = System.currentTimeMillis() - bt;
        System.out.println("insert_sort: " + ut  + "ms.");
        //qs.diaplay();
        
        // double select sort
        qs.setArrayCopy();
        //qs.diaplay();
        bt = System.currentTimeMillis();
        qs.sort(double_select_sort);
        ut = System.currentTimeMillis() - bt;
        System.out.println("double_select_sort: " + ut + "ms.");
        //qs.diaplay();
        
        // select sort
        qs.setArrayCopy();
        //qs.diaplay();
        bt = System.currentTimeMillis();
        qs.sort(select_sort);
        ut = System.currentTimeMillis() - bt;
        System.out.println("select_sort: " + ut + "ms.");
        //qs.diaplay();
        
        // buble sort
        qs.setArrayCopy();
        //qs.diaplay();
        bt = System.currentTimeMillis();
        qs.sort(buble_sort);
        ut = System.currentTimeMillis() - bt;
        System.out.println("buble_sort: " + ut + "ms.");
        //qs.diaplay();
 */
    }
}
