import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * JUnit test class for interface {@see IdCreator}. It assumes an implementation called IdCreatorImpl.
 * 
 * @author federico.bartolomei (BBK-PiJ-2014-21)
 */
public class TestIdCreator {
    
    public int[] createIdArray(int n) {
        IdCreator id = new IdCreatorImpl();
        int[] array = new int[n];
        for(int i=0; i<array.length; i++) {
            array[i] = id.createContactId();
        }
        return array;
    }
    
    @Test
    public void test100ContactIDsAreUnique() {
        int[] array = createIdArray(100);
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array.length; j++) {
                if(i==j) {
                    continue;
                }
                assertTrue("id at position " + i + "= id at position " + j, array[i] != array[j]);
            }
        }
    }
    
    @Test
    public void testTwoArraysOfIDsCreatedWithTwoInstancesOfIdCreatorShouldHaveTheSameIDs() {
        int[] first = createIdArray(10);
        int[] second = createIdArray(10);
        for (int i = 0; i < first.length; i++) {
            assertFalse(first[i] + "!=" + second[i], first[i] != second[i]);
        }
    }
        
    @Test
    public void testMeetingId() {
        IdCreator test = new IdCreatorImpl();
        Set<Integer> meetingIDs = new HashSet<Integer>();
        for(int i=0; i<9; i++) {
            meetingIDs.add(test.createMeetingId());
        }
        assertEquals(test.createMeetingId(), 10);
    }
    
    
}
