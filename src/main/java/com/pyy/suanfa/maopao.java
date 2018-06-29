package com.pyy.suanfa;

import java.util.Arrays;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/29 10:47
 * @Description: 冒泡排序
 *
 */
public class maopao {

    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param nums
     */
    public static void bubbleSort(Integer[] nums){
        int temp = 0;
        int size = nums.length;
        for(int i = 0; i<size - 1; i++){
            for(int j =0; j < size - i -1; j ++){
                if(nums[j] > nums[j+1]){
                    temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }

    /******************快速排序开始*************************/
    public static int getMiddle(Integer[] number, int low, int high){
        int temp = number[low]; //数组的第一个作为中轴
        while (low < high){
            while ( low < high && number[high] > temp){
                high--;
            }
            number[low] = number[high];//比中轴小的记录移到低端
            while ( low < high && number[low] < temp){
                low++;
            }
            number[high] = number[low];//比中轴大的记录移到高端
        }
        number[low] = temp;//中轴记录到尾
        return low;// 返回中轴的位置
    }

    public static void quickSort(Integer[] numbers,int low, int high){
        if(low < high){
            int middle = getMiddle(numbers, low, high); //将numbers数组进行一分为二
            quickSort(numbers, low, middle-1);//对低字段表进行递归排序
            quickSort(numbers,middle+1, high);//对高字段表进行递归排序
        }
    }

    public static void quick(Integer[] numbers){
        if (numbers.length > 0){
            quickSort(numbers,0, numbers.length-1);
        }
    }
    /******************快速排序结束*************************/

    /******************选择排序开始*************************/
    /**
     * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，
     * 如此循环到倒数第二个数和最后一个数比较为止
     * @param numbers
     */
    public static void chooseSort(Integer[] numbers){

        int size = numbers.length; //数组长度
        int temp = 0;//中间变量
        for(int i=0; i<size; i++){
            int k = i; //待确定的位置
            //选择出应该在第i个位置的数
            for(int j = size -1; j>i; j--){
                if(numbers[j] < numbers[k]){
                    k = j;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }
    /******************选择排序结束*************************/


    /******************插入排序开始*************************/
    /**
     * 基本思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），直到全部插入排序完为止。
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     */
    public static void insertSort(Integer[] numbers){
        int size = numbers.length;
        int temp = 0;
        int j = 0;
        for(int i=0; i<size -1; i++){
            temp = numbers[i];
            for( j = i; j > 0 && temp < numbers[j - 1]; j--){
                numbers[j] = numbers[j-1];
            }
            numbers[j] = temp;
        }
    }
    /******************插入排序结束*************************/


    /******************希尔排序结束*************************/
    /**
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录“基本有序”时，
     * 再对全体记录进行依次直接插入排序。
     * 希尔排序的原理:根据需求，如果你想要结果从大到小排列，它会首先将数组进行分组，然后将较大值移到前面，较小值
     * 移到后面，最后将整个数组进行插入排序，这样比起一开始就用插入排序减少了数据交换和移动的次数，可以说希尔排序是加强
     * 版的插入排序
     * 拿数组5, 2, 8, 9, 1, 3，4来说，数组长度为7，当increment为3时，数组分为两个序列
     * 5，2，8和9，1，3，4，第一次排序，9和5比较，1和2比较，3和8比较，4和比其下标值小increment的数组值相比较
     * 此例子是按照从大到小排列，所以大的会排在前面，第一次排序后数组为9, 2, 8, 5, 1, 3，4
     * 第一次后increment的值变为3/2=1,此时对数组进行插入排序，
     */
    public static void shellSort(Integer[] numbers){
        int j = 0;
        int temp = 0;
        for(int increment = numbers.length / 2; increment >0; increment /= 2){
            for(int i = increment; i < numbers.length; i++){
                temp = numbers[i];
                for(j = i; j>= increment; j-= increment){
                    if(temp > numbers[j - increment]){
                        numbers[j] = numbers[j - increment];
                    }else{
                        break;
                    }
                    numbers[j] = temp;
                }
            }
        }
    }
    /******************希尔排序结束*************************/
    public static void main(String[] args){
        Integer[] nums = {49,38,65,97,76,13,27};
        long startTime = System.currentTimeMillis();
        //bubbleSort(nums);
        //quick(nums);
        //chooseSort(nums);
//        insertSort(nums);
        shellSort(nums);
        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.deepToString(nums));
    }
}
