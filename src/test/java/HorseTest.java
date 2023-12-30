import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void testNullName(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10));
    }

    @Test
    public void testNullNameMessage(){
        try {
            new Horse(null, 10);
        }catch (Exception e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void testEmptyName(String name){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void testEmptyNameMessage(String name){
        try {
            new Horse(name, 10);
        }catch (Exception e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }
    @Test
    public void testNegativeSpeed(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Lasunya", -1));
    }
    @Test
    public void testNegativeSpeedMessage(){
        try {
            new Horse("Lasunya", -1);
        }catch (Exception e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }
    @Test
    public void testNegativeDistance(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Lasunya", 10, -1));
    }
    @Test
    public void testNegativeDestanceMessage(){
        try {
            new Horse("Lasunya", 10, -1);
        }catch (Exception e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }
    @Test
    public void testGetNaveMethod(){
        Horse horse = new Horse("Lasunya", 10);
        assertEquals("Lasunya", horse.getName());
    }
    @Test
    public void testGetSpeedMethod(){
        Horse horse = new Horse("Lasunya", 10);
        assertEquals(10, horse.getSpeed());
    }
    @Test
    public void testGetDistanceWithThreeParameters(){
        Horse horse = new Horse("Lasunya", 10, 5);
        assertEquals(5, horse.getDistance());
    }
    @Test
    public void testGetDistanceWithTwoParameters(){
        Horse horse = new Horse("Lasunya", 10);
        assertEquals(0, horse.getDistance());
    }
    @Test
    public void testMoveInvokeGetRandomDouble(){
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            new Horse("Lasunya", 10).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @Test
    public void testCorrectDistanceAfterMove(){
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("Lasunya", 10);
            horse.move();
            assertEquals(5, horse.getDistance());
        }
    }
}
