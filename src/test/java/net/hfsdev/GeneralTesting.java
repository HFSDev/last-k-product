package net.hfsdev;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GeneralTesting {
    @Test
    void testArrayInitialization(){
        int[] arr = new int[10];
        for(int i=0;i<arr.length;i++){
            assertThat(arr[i]).isEqualTo(0);
        }
    }


}