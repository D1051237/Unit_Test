package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class HW02Test {

    @DisplayName("BMI Test with CsvSource method")
    @ParameterizedTest(name = "BMI {2} is correct")
    @CsvSource({
            "160, 50, 19.53",  // Test case 1
            "170, 70, 24.22",  // Test case 2
            "180, 80, 24.69"   // Test case 3
    })
    public void testCalculateBMI(int height, int weight, double expectedBMI) {
        HW02 hw2 = new HW02();
        assertEquals(expectedBMI, hw2.getbmi(height,weight), 0.01); // 0.01 tolerance for double comparison
    }

    @DisplayName("Day of week test with CsvSource method")
    @ParameterizedTest(name = "2021年{0}月{1}日 是 {2}")
    @CsvSource({
            "1, 4, 星期一",
            "4, 21, 星期三",
            "5, 30, 星期日",
            "7, 8, 星期四",
            "12, 25, 星期六"
    })
    void Checkdate1(int m,int d,String dw){
        HW02 hw2 = new HW02();
        assertEquals(dw, hw2.daychecker(m,d));
    }

    @DisplayName("Day of week Test with CsvFileSource method")
    @ParameterizedTest(name = "2021年{0}月{1}日 是 {2}")
    @CsvFileSource(resources = "/DateTestdata.csv", numLinesToSkip = 1)
    void Checkdate2(int m,int d,String dw){
        HW02 hw02 = new HW02();
        assertEquals(dw,hw02.daychecker(m,d));
    }

    @DisplayName("Currency test with CsvSource method")
    @ParameterizedTest(name = "{0}{1} + {2}{3} = {4}{5}")
    @CsvSource({
            "100, NT, 100, NT, 200, NT",   // Test case 1
            "100, US, 3000, NT, 200, US",  // Test case 2
            "100, NT, 3, US, 190, NT"    // Test case 3
    })
    void currencytest(int fst_amount, String fst_sym, int sec_amount, String sec_sym, int expectedamount, String expectedSym){
        HW02 hw2 = new HW02();
        HW02.Currency Currency1 = hw2.new Currency(fst_amount,fst_sym);
        HW02.Currency Currency2 = hw2.new Currency(sec_amount,sec_sym);
        HW02.Currency result = Currency1.add(Currency2);

        assertEquals(expectedamount, result.amount); // Test the amount
        assertEquals(expectedSym, result.symbol); // Test the symbol
    }

    @DisplayName("NextDay test with CsvSource method")
    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "2023-01-01, 2023-01-02",
            "2023-03-07, 2023-03-08",
            "2023-05-15, 2023-05-16",
            "2023-10-21, 2023-10-22",
            "2023-12-07, 2023-12-08"
    })
    void Checknextdaytest(String day,String nextday){
        HW02 hw02 = new HW02();
        assertEquals(nextday,hw02.Checknextday(day));
    }
}