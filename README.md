# Problem derived from de Prefix Sum Array problem
## Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.

Implement the ProductOfNumbers class:

    ProductOfNumbers() Initializes the object with an empty stream.
    void add(int num) Appends the integer num to the stream.
    int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that always the current list has at least k numbers.

Constraints:

    0 <= num <= 100
    1 <= k <= 4 * 104
    At most 4 * 104 calls will be made to add and getProduct.
    The product of the stream at any point in time will fit in a 32-bit integer.

Follow-up: Can you implement both GetProduct and Add to work in O(1) time complexity instead of O(k) time complexity?
## Explanation
To solve the problem easily, one would need only to iterate over the list of numbers calculating the product as the result. This would make the solution run in a time complexity of O(k), where k is the number of elements one would have to iterate. A junior developer might think this is ok, but in a real-world problem, this cost is not acceptable. To solve the problem adequately, you have to notice that you can store the products in a same-sized array:
An input array like [2    3    5   2] would have a Prefix Product Array with the products instead, like in [2    6   30    60]. You might think you'd have to recalculate the inner positions as different inputs for k are provided, but that's not true. For k=2, we´d have a product of 5*2=10, which is the same thing as dividing the total product (last position) by the product in position i = (array.length - k), or 4 - 2 (we have to subtract 1 for the zero-based array). In our example, this would be 60 / 6 = 10. The main problem is solved with the previous rule. After considering that, all that remains is the edge cases revolving around zeros, which you have to take notice of since any k that surpasses the zero position will result in a product of zero, as in [2    3    0    5    2] where any k greater than 2 would result in zero. Other edge cases should be accounted for easily, for example when k is the same as the amount of elements in the list, in which case you´d return the prefix product in the last position.