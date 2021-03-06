import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * Implementation of interface {@see PastMeeting}.
 *
 * @author federico.bartolomei (BBK-PiJ-2014-21)
 */
public class PastMeetingImpl extends MeetingImpl implements Serializable, PastMeeting {
    private static final long serialVersionUID = 1L;
    private String notes;

    /**
     * Constructor for a past meeting without any notes.
     * It will pass the arguments to the parent class Meeting, and the values of the notes
     * will be the empty String.
     *
     * @param date the date of the meeting
     * @param contacts the contacts that attended the meeting
     * @param id the unique-id of the meeting
     * @throws IllegalArgumentException if the date entered is in the future
     */
    public PastMeetingImpl(Calendar date, Set<Contact> contacts, int id) {
        super(date, contacts, id);
        if(date.after(Calendar.getInstance())) {
            throw new IllegalArgumentException("Cannot create a PastMeeting with a future date");
        }
        notes = "";
    }

    /**
     * Constructor for a past meeting with notes.
     *
     * @param date the date of the meeting
     * @param contacts the contacts that attended the meeting
     * @param id the unique-id of the meeting
     * @param notes the notes about the meeting
     * @throws NullPointerException for an attempt to add null notes
     * @throws IllegalArgumentException if the date entered is in the future
     */
    public PastMeetingImpl(Calendar date, Set<Contact> contacts, int id, String notes) {
        super(date, contacts, id);
        if (notes == null) {
            throw new NullPointerException("Cannot add null notes");
        } else if(date.after(Calendar.getInstance())) {
            throw new IllegalArgumentException("Cannot create a PastMeeting with a future date");
        } else {
            this.notes = notes;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the notes about the past meeting
     */
    @Override
    public String getNotes() {
        return notes;
    }

    /**
     * Add some notes about the past meeting.
     *
     * @param newNotes the new notes to be added about the meeting
     * @throws NullPointerException for an attempt to add null notes
     */
    public void addNotes(String newNotes) {
        if(newNotes==null) {
            throw new NullPointerException("Cannot add null notes");
        } else {
            if(notes.equals("")) {
                notes = newNotes;
            } else {
                notes += " " + newNotes;
            }
        }
    }

}
