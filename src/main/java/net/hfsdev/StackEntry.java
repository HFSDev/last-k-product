package net.hfsdev;

public class StackEntry {
    /**
     * The element itself;
     */
    private int element;

    /**
     * The product of all the elements in the stack from
     * outermost element to this element.
     * Outermost element has product
     */
    private int stackElementsProduct;

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public int getStackElementsProduct() {
        return stackElementsProduct;
    }

    public void setStackElementsProduct(int stackElementsProduct) {
        this.stackElementsProduct = stackElementsProduct;
    }
    /**
     * [2   4   6]
     * [48  24  6]
     *
     * [2   4   6   3]
     * [128 64  18  3]
     */
}
