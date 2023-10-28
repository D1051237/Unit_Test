package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HW02Test {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void addOnetest(int x){
        HW02 hw2 = new HW02();
        assertEquals(x + 1,hw2.addOne(x));
    }
    
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "0,-1,-1",
            "1,0,1",
            "1,-1,0"
    })
    void testadd1(int x,int y,int r){
        HW02 hw2 = new HW02();
        assertEquals(r,hw2.add(x,y));
    }
}