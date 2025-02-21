package net.hfsdev;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductOfNumbersTest {

    @Test
    void testProductOfNumbersGreaterThanNumberOfElementsIsNotAllowed(){
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(5);
        productOfNumbers.add(10);
        assertThatThrownBy(()->productOfNumbers.getProduct(3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testSingleElementList(){
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(5);
        assertThat(pon.getProduct(1)).isEqualTo(5);
    }
    @Test
    void testDoubleElementsListWithoutZero(){
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(5);
        pon.add(10);
        assertThat(pon.getProduct(2)).isEqualTo(50);
    }
    @Test
    void testDoubleElementsListWithZeroInFirstElement(){
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(0);
        pon.add(10);
        assertAll(
                ()->assertThat(pon.getProduct(1)).isEqualTo(10),
                ()->assertThat(pon.getProduct(2)).isEqualTo(0)
        );
    }
    @Test
    void testMixedNumberWithoutZero(){
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(2);
        pon.add(5);
        pon.add(3);
        pon.add(4);
        assertAll(
                ()->assertThat(pon.getProduct(4)).isEqualTo(120),
                ()->assertThat(pon.getProduct(3)).isEqualTo(60),
                ()->assertThat(pon.getProduct(2)).isEqualTo(12),
                ()->assertThat(pon.getProduct(1)).isEqualTo(4));
    }

    @Test
    void testMixedNumberWithZeroInFirstPosition(){
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(0);
        pon.add(5);
        pon.add(3);
        pon.add(4);
        assertAll(
                ()->assertThat(pon.getProduct(4)).isEqualTo(0),
                ()->assertThat(pon.getProduct(3)).isEqualTo(60),
                ()->assertThat(pon.getProduct(2)).isEqualTo(12),
                ()->assertThat(pon.getProduct(1)).isEqualTo(4));
    }

    @Test
    void testMixedNumberWithZeroInLastPosition(){
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(2);
        pon.add(5);
        pon.add(3);
        pon.add(0);
        assertAll(
                ()->assertThat(pon.getProduct(4)).isZero(),
                ()->assertThat(pon.getProduct(3)).isZero(),
                ()->assertThat(pon.getProduct(2)).isZero(),
                ()->assertThat(pon.getProduct(1)).isZero());
    }

    @Test
    void testMixedNumberWithZeroInSecondPosition(){
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(2);
        pon.add(0);
        pon.add(3);
        pon.add(5);
        assertAll(
                ()->assertThat(pon.getProduct(4)).isZero(),
                ()->assertThat(pon.getProduct(3)).isZero(),
                ()->assertThat(pon.getProduct(2)).isEqualTo(15),
                ()->assertThat(pon.getProduct(1)).isEqualTo(5));
    }
}