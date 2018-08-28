package ee.sda.maven.hackerrank;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public  class DiagonalDifferenceTest {

    //improve the scope of the new variable on our class level
    private static DiagonalDifference diagonalDifference;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Run before the first test method");
        diagonalDifference = new DiagonalDifference();
    }

    @AfterClass
    public static void tearDownBeforeClass() {
        System.out.println("Run after the last test method");
    }

    @Before
    public void setUp() {
        System.out.println("Run before each test");
        //diagonalDifference = new DiagonalDifference();
    }

    @After
    public void tearDown() {
        System.out.println("Run after each test");
    }


    @Test
    public void diagonalDifference_ReturnsZero_IfSizeIs1() {
        // given
        int[][] input = new int[][]{{10}};

        // when
        int result = diagonalDifference.diagonalDifference(input);

        // then
        assertEquals(0, result);
    }

    @Test
    public void diagonalDifference_ReturnsCorrectResult_IfSizeIsOdd() {
        // given
        int[][] input = new int[][]{
                {1, 5, 8},
                {8, 3, 9},
                {7, 6, 2}
        };

        // when
        int result = diagonalDifference.diagonalDifference(input);

        // then
        assertEquals(12, result);
    }

    @Test
    public void diagonalDifference_ReturnsCorrectResult_IfSizeIsEven() {
        // given
        int[][] input = new int[][]{
                {1, 5, 8, 4},
                {8, 3, 9, 0},
                {7, 6, 2, 1},
                {9, 7, 2, 6}
        };

        // when
        int result = diagonalDifference.diagonalDifference(input);

        // then
        assertEquals(16, result);
    }

    @Test
    public void diagonalDifference_ReturnsCorrectResult_IfHasNegativeNumbers() {
        // given
        int[][] input = new int[][]{
                {1, 5, 8, -4},
                {8, -3, 9, 0},
                {7, -6, 2, 1},
                {9, 7, 2, -6}
        };

        // when
        int result = diagonalDifference.diagonalDifference(input);

        // then
        assertEquals(14, result);
    }
}