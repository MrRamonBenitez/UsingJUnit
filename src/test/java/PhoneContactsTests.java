import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneContactsTests {
    PhoneContacts phoneContacts;

    @BeforeAll
    public static void started() { System.out.println("Tests started"); }

    @AfterEach
    public void finished() { System.out.println("Test completed"); }

    @AfterAll
    public static void finishedAll() { System.out.println("Tests completed"); }

    @BeforeEach
    void setUp() {
        phoneContacts = new PhoneContacts();
    }

    @Test
    public void testCountingNumberGroups() {

        //arrange
        String[] groupName = new String[]{"Family", "Work", "Friends"};
        for (String name : groupName) phoneContacts.addGroup(name);

        //act
        int actualGroupNumber = phoneContacts.getNumberGroups();

        //assert
        int expectedGroupNumber = groupName.length;
        assertEquals(expectedGroupNumber, actualGroupNumber);
    }

    @Test
    public void testGroupPresenceCheck() {
        //arrange
        String[] groupName = new String[]{"Family", "Work", "Friends"};

        //act
        for (String name : groupName) phoneContacts.addGroup(name);

        //assert
        String testGroupName = "Work";
        assertTrue(phoneContacts.groupPresenceCheck(testGroupName));
    }

    @Test
    public void testGroupAbsenceCheck() {
        //arrange
        String[] groupName = new String[]{"Family", "Work", "Friends"};

        //act
        for (String name : groupName) phoneContacts.addGroup(name);

        //assert
        String testGroupName = "Club";
        assertFalse(phoneContacts.groupPresenceCheck(testGroupName));
    }

    @Test
    public void testGetContactByPhoneNumber() {
        //arrange
        String testNumber = "+79354445566";

        phoneContacts.addGroup("Family");
        phoneContacts.addGroup("Work");

        String[] groupName = new String[]{"Family", "Work"};
        Contact testContact = new Contact("Ivan Petrov", "+79354445566");
        phoneContacts.addContact(testContact, groupName);

        //act
        Contact actualContact = phoneContacts.getContactByPhoneNumber(testNumber);

        //assert
        assertEquals(testContact, actualContact);
    }

    @Test
    public void testContactPresenceCheck() {
        //arrange
        String testNumber = "+79354445566";

        phoneContacts.addGroup("Family");
        phoneContacts.addGroup("Work");

        String[] groupName = new String[]{"Family", "Work"};
        Contact testContact = new Contact("Ivan Petrov", "+79354445566");
        phoneContacts.addContact(testContact, groupName);

        //assert
        assertTrue(phoneContacts.contactPresenceCheck(testNumber));
    }

    @Test
    public void testContactAbsenceCheck() {
        //arrange
        String testNumber = "+79378885566";

        phoneContacts.addGroup("Family");
        phoneContacts.addGroup("Work");

        String[] groupName = new String[]{"Family", "Work"};
        Contact testContact = new Contact("Ivan Petrov", "+79354445566");
        phoneContacts.addContact(testContact, groupName);

        //assert
        assertFalse(phoneContacts.contactPresenceCheck(testNumber));
    }
}