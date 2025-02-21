package net.hfsdev;

/**
 * <a href="https://leetcode.com/problems/product-of-the-last-k-numbers/description/">LeetCode Question</a></br>
 * Author: <a href="https://github.com/HFSDev">HFSDev</a></br>
 *</br>
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
