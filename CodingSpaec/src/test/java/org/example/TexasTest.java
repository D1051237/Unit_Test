package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TexasTest {

    @DisplayName("九局內正常結束測試")
    @Test
    void TeaxsTest1() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,3,2};
        int[] inB = {1,1,1,1,1,1,1,1,1};
        int[] pyA = {1,1,3,0,2,1,1,2,1};
        int[] pyB = {2,0,1,2,1,1,0,2,0};

        Texas t = new Texas(inA,inB,pyA,pyB);
        int result = t.score(inA,inB,pyA,pyB);
        assertEquals(3,result);
    }

    @DisplayName("九上正常結束測試")
    @Test
    void TeaxsTest2() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,1,1};
        int[] inB = {1,1,1,1,1,1,1,3,-1};
        int[] pyA = {2,0,1,0,1,0,2,1,2};
        int[] pyB = {0,2,1,1,0,2,1,3,0};

        Texas t = new Texas(inA,inB,pyA,pyB);
        int result = t.score(inA,inB,pyA,pyB);
        assertEquals(-1,result);
    }

    @DisplayName("延長賽測試測試")
    @Test
    void TeaxsTest3() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,1,1,3};
        int[] inB = {1,1,1,1,1,1,1,1,1,2};
        int[] pyA = {2,0,1,2,1,1,2,1,2};
        int[] pyB = {3,2,1,1,0,0,1,3,0};

        Texas t = new Texas(inA,inB,pyA,pyB);
        int result = t.score(inA,inB,pyA,pyB);
        assertEquals(1,result);
    }

    @DisplayName("不合理提前結束測試")
    @Test
    void TeaxsTest4() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,1,1};
        int[] inB = {1,1,1,1,1,1,1,1,-1};
        int[] pyA = {2,0,1,0,1,0,2,1,2};
        int[] pyB = {0,2,1,1,0,0,1,3,0};

        Texas t = new Texas(inA,inB,pyA,pyB);
        Exception exception = assertThrows(Exception.class, () -> { t.score(inA,inB,pyA,pyB); });
        System.out.println("Corresponded exception: " + exception.getMessage());
    }

    @DisplayName("不必要的延長賽錯誤測試")
    @Test
    void TeaxsTest5() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,1,3,1};
        int[] inB = {1,1,1,1,1,1,1,1,0,1};
        int[] pyA = {2,1,2,1,3,0,1,0,2};
        int[] pyB = {2,0,1,1,0,2,0,3,0};

        Texas t = new Texas(inA,inB,pyA,pyB);
        Exception exception = assertThrows(Exception.class, () -> { t.score(inA,inB,pyA,pyB); });
        System.out.println("Corresponded exception: " + exception.getMessage());
    }

    @DisplayName("小於九局錯誤測試")
    @Test
    void TeaxsTest6() throws Exception {
        int[] inA = {1,0,1,1,2,1};
        int[] inB = {1,1,0,1,1,1};
        int[] pyA = {2,0,0,0,0,0,2,0,2};
        int[] pyB = {0,0,1,1,0,0,0,3,0};

        Texas t = new Texas(inA,inB,pyA,pyB);
        Exception exception = assertThrows(Exception.class, () -> { t.score(inA,inB,pyA,pyB); });
        System.out.println("Corresponded exception: " + exception.getMessage());
    }

    @DisplayName("大於九局並和局錯誤測試")
    @Test
    void TeaxsTest7() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,1,1,1,1};
        int[] inB = {1,1,1,1,1,1,1,1,1,1,1};
        int[] pyA = {2,1,2,1,0,1,2,0,2};
        int[] pyB = {2,0,1,1,0,2,0,3,2};

        Texas t = new Texas(inA,inB,pyA,pyB);
        Exception exception = assertThrows(Exception.class, () -> { t.score(inA,inB,pyA,pyB); });
        System.out.println("Corresponded exception: " + exception.getMessage());
    }

    @DisplayName("第九局先攻未打錯誤測試")
    @Test
    void TeaxsTest8() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,1,-1};
        int[] inB = {1,1,1,1,1,1,1,1,1};
        int[] pyA = {2,1,2,1,0,0,0,0,2};
        int[] pyB = {2,0,1,1,0,2,0,3,0};

        Texas t = new Texas(inA,inB,pyA,pyB);
        Exception exception = assertThrows(Exception.class, () -> { t.score(inA,inB,pyA,pyB); });
        System.out.println("Corresponded exception: " + exception.getMessage());
    }

    @DisplayName("B隊九下已打測試")
    @Test
    void TeaxsTest9() throws Exception {
        int[] inA = {1,1,1,1,1,1,1,1,1};
        int[] inB = {1,1,1,1,1,1,1,1,3};
        int[] pyA = {2,1,2,1,0,1,0,0,2};
        int[] pyB = {2,0,1,1,0,2,1,3,1};

        Texas t = new Texas(inA,inB,pyA,pyB);
        int result = t.score(inA,inB,pyA,pyB);
        assertEquals(-2,result);
    }
}