import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * JUnit test class for interface {@see Meeting}. It assumes an implementation called MeetingImpl.
 *
 * It does not test for the uniqueness of the ID, as this should be provided by the class in charge of issuing them,
 * or by the one above that. The Meeting class accepts the ID provided at construction time as final, without any
 * kind of checking.
 *
 * @author federico.bartolomei (BBK-PiJ-2014-21)  
 */
public class TestMeeting {
    Set<Contact> contacts;
    Calendar date;
    
    @Before
    public void setUp() {
        contacts = null;
        date = new GregorianCalendar(2015,10,21,10,15);
    }
    
    @After
    public void tearDown() {
        contacts = null;
        date = null;
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    public Contact createContactsMock(String name, int id, String notes) {
        Contact dummy = mock(Contact.class);
        when(dummy.getName()).thenReturn(name);
        when(dummy.getId()).thenReturn(id);
        when(dummy.getNotes()).thenReturn(notes);
        return dummy;
    }
    
    public Set<Contact> createSetOfContactsMock(int n) {
        Set<Contact> toReturn = new LinkedHashSet<Contact>();
        for(int i=1; i<=n; i++) {
            toReturn.add(createContactsMock("Contact" + i, i, "Notes about Contact" + i));
        }
        return toReturn;
    }
    
    @Test
    public void createMeetingWithNullDateShouldThrowNullPointerException() {
        exception.expect(NullPointerException.class);
        Meeting nullDate = new MeetingImpl(null, contacts, 3);
    }
    
    @Test
    public void createMeetingWithNullContactsShouldThrowNullPointerException() {
        exception.expect(NullPointerException.class);
        Meeting nullContacts = new MeetingImpl(date, null, 10);
    }
    
    @Test
    public void createMeetingWithOneContactTestGetContactsSize() {
        contacts = createSetOfContactsMock(1);
        Meeting test = new MeetingImpl(date, contacts, 3);
        assertEquals(test.getContacts().size(), 1);
    }
    
    @Test
    public void createMeetingWith10ContactsTestGetContactsSize() {
        contacts = createSetOfContactsMock(10);
        Meeting test = new MeetingImpl(date, contacts, 4);
        assertEquals(test.getContacts().size(), 10);
    }
    
    @Test
    public void testGetId() {
        contacts = createSetOfContactsMock(10);
        Meeting test = new MeetingImpl(date, contacts, 500);
        assertEquals(test.getId(), 500);
    }
    
    @Test
    public void testGetDate() {
        contacts = createSetOfContactsMock(5);
        Meeting test = new MeetingImpl(date, contacts, 200);
        assertEquals(test.getDate().get(Calendar.DAY_OF_MONTH), 21);
    }
    
    @Test
    public void testGetContactsFirstContact() {
        contacts = createSetOfContactsMock(5);
        Meeting test = new MeetingImpl(date, contacts, 10);
        Contact first = (Contact)test.getContacts().toArray()[0];
        assertEquals(first.getName(), "Contact1");
        assertEquals(first.getId(), 1);
        assertEquals(first.getNotes(), "Notes about Contact1");
    }

    @Test
    public void testGetContactsLastContact() {
        contacts = createSetOfContactsMock(10);
        Meeting test = new MeetingImpl(date, contacts, 100);
        Contact last = (Contact)test.getContacts().toArray()[9];
        assertEquals(last.getName(), "Contact10");
        assertEquals(last.getId(), 10);
        assertEquals(last.getNotes(), "Notes about Contact10");
    }

    @Test
    public void testGetContactSetSize() {
        contacts = createSetOfContactsMock(100);
        Meeting test = new MeetingImpl(date, contacts, 3);
        assertEquals(test.getContacts().size(), 100);
    }

    @Test
    public void testCreateMeetingWithEmptySetOfContactsShouldThrowAnIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        Set<Contact> empty = new HashSet<Contact>();
        Meeting test = new MeetingImpl(date, empty, 10);
    }

}
