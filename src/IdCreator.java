/**
 * Interface to issue unique IDs, created to support the implementation of {@see Contact} and {@see Meeting}.
 * 
 * @author federico.bartolomei (BBK-PiJ-2014-21)
 */
public interface IdCreator {

    /**
     * Create an unique ID number to be associated with a Contact. 
     * The implementation should guarantee that a different number is returned 
     * every time the method is called.
     *
     * @return an unique id number.
     */
    public int createContactId();

    /**
     * Create an unique ID number to be associated with a Meeting. 
     * The implementation should guarantee that a different number is returned 
     * every time the method is called.
     *  
     * @return an unique id number for the Meeting.
     */
    public int createMeetingId();
    
}
