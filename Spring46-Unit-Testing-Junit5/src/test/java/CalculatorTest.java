import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @BeforeEach
    void setUpEach(){
        System.out.println("Before each executed ");
    }

    @AfterEach
    void tearEach(){
        System.out.println("After each executed ");
    }

    @BeforeAll
    static void setUpAll(){
        System.out.println("Before All Executed");
    }

    @AfterAll
    static void tearAll(){
        System.out.println("After All Executed");
    }

    @Test
    void testCase1() {
        System.out.println("TC1 executed ");
        fail("Not implemented yet");
    }


    @Test
    void testCase2() {
        System.out.println("TC2 executed ");
       assertTrue(Calculator.operator.equals("add"));
    }

    @Test
    void testCase3() {
        System.out.println("TC3 executed ");
        assertArrayEquals(new int[]{1,2,3}, new int[]{1,2,3});
    }

    @Test
    void testCase4() {
        System.out.println("TC4 executed ");
        String emoji = null;
        assertNull(emoji);
      //  assertNotNull(emoji);
    }

    @Test
    void testCase5() {
    System.out.println("TC5 executed ");
    Calculator c1 = new Calculator();
    Calculator c2 = c1;
    Calculator c3 = new Calculator();

   // assertNotSame(c1, c2);
    assertNotSame(c3, c2);
    }

    @Test
    void add() {
        System.out.println("TC0 executed ");
       int actual = Calculator.add(2,5);
       assertEquals(7, actual);
    }
}