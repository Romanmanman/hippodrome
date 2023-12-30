import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class HippodromeTest {
    @Test
    public void testNullConstructor(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    public void testNullConstructorMessage(){
        try{
            new Hippodrome(null);
        }catch (Exception e){
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }
    @Test
    public void testEmptyList(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }
    @Test
    public void testEmptyListMessage(){
        try {
            new Hippodrome(new ArrayList<>());
        }catch (Exception e){
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void testGetHorses(){
        List<Horse> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            list.add(new Horse(i + "", i));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        assertEquals(list, hippodrome.getHorses());
    }
    @Test
    public void testMoveMethod(){
        List<Horse>list = new ArrayList<>();
        for (int i = 1; i <=50 ; i++) {
            list.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();
        for (int i = 0; i < list.size(); i++) {
            Mockito.verify(list.get(i)).move();
        }
    }
}
