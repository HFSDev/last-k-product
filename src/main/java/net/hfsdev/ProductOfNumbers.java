package net.hfsdev;

/**
 * LeetCode Question </br>
 * Author: <a href="https://github.com/HFSDev">HFSDev</a></br>
 *</br>
 * Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.
 *</br>
 * Implement the ProductOfNumbers class:
 *</br>
 *     ProductOfNumbers() Initializes the object with an empty stream.
 *     void add(int num) Appends the integer num to the stream.
 *     int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always
 *     the current list has at least k numbers.
 *</br>
 * The test cases are generated so that, at any time, the product of any contiguous sequence of numbers will fit into a
 * single 32-bit integer without overflowing.
 *</br>
 *</br>
 * Example:
 *</br>
 * Input
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]</br>
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]</br>
 *</br>
 * Output
 * [null,null,null,null,null,null,20,40,0,null,32]
 *</br>
 * Explanation</br>
 * ProductOfNumbers productOfNumbers = new ProductOfNumbers();</br>
 * productOfNumbers.add(3);        // [3]</br>
 * productOfNumbers.add(0);        // [3,0]</br>
 * productOfNumbers.add(2);        // [3,0,2]</br>
 * productOfNumbers.add(5);        // [3,0,2,5]</br>
 * productOfNumbers.add(4);        // [3,0,2,5,4]</br>
 * productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20</br>
 * productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40</br>
 * productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0</br>
 * productOfNumbers.add(8);        // [3,0,2,5,4,8]</br>
 * productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32</br>
 *</br>
 * Constraints:
 *</br>
 *     0 <= num <= 100</br>
 *     1 <= k <= 4 * 104</br>
 *     At most 4 * 104 calls will be made to add and getProduct.</br>
 *     The product of the stream at any point in time will fit in a 32-bit integer.
 *</br>
 * Follow-up: Can you implement both GetProduct and Add to work in O(1) time complexity instead of O(k) time complexity?
 */
public class ProductOfNumbers {

    public static final int MAX_ELEMENTS = 40000;

    private final int[] productOfNumbersArray;
    private int nextIndex;
    private int lastZeroElementIndex;
    private int lastAddedElement = 0;

    public ProductOfNumbers() {
        productOfNumbersArray = new int[MAX_ELEMENTS];
        nextIndex = 0;
        lastZeroElementIndex = -1;
    }

    public void add(int num){
        if(num < 0 || num > 100){
            throw new IllegalArgumentException("Num needs to be between 0 and 100");
        }
        if(num == 0){
            this.lastZeroElementIndex = nextIndex;
        } else {
            if (this.nextIndex == 0) {
                this.productOfNumbersArray[this.nextIndex] = num;
            } else {
                int lastProduct = productOfNumbersArray[this.nextIndex - 1];
                if(lastProduct == 0){
                    this.productOfNumbersArray[this.nextIndex] = num;
                } else {
                    this.productOfNumbersArray[this.nextIndex] = this.productOfNumbersArray[this.nextIndex - 1] * num;
                }
            }
        }
        this.lastAddedElement = num;
        this.nextIndex++;
    }

    public int getProduct(int k){
        if(k < 1){
            throw new IllegalArgumentException("k must be greater than 0");
        }
        if(k > MAX_ELEMENTS){
            throw new IllegalArgumentException(String.format("k must be less than or equal to the length: %d.", MAX_ELEMENTS));
        }
        if(k > this.nextIndex){
            throw new IllegalArgumentException("k must be less than or equal to the number of elements in the list.");
        }
        int numberOfElementsBeforeLastZero = this.getElementsCountBeforeLastZero();
        if(k == 1){
            return this.lastAddedElement;
        } else if (k == numberOfElementsBeforeLastZero){
            return this.productOfNumbersArray[this.nextIndex - 1];
        } else if(k > numberOfElementsBeforeLastZero){
            return 0;
        } else {
            return this.productOfNumbersArray[this.nextIndex-1] / this.productOfNumbersArray[this.nextIndex - k - 1];
        }
    }

    private int getElementsCountBeforeLastZero(){
        return this.nextIndex - this.lastZeroElementIndex - 1;
    }
}
