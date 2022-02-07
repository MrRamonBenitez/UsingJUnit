import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneContactsTests {

    PhoneContacts phoneContacts;

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @BeforeEach
    void setUp() {
        phoneContacts = new PhoneContacts();
    }

    @Test
    public void testCountingNumberGroups() {
        //arrange
        int groupNumber;
        String[] groupName = new String[]{"Family", "Work", "Friends"};
        int expectedGroupNumber = groupName.length;
        for (String name : groupName) phoneContacts.addGroup(name);

        //act
        groupNumber = phoneContacts.countingNumberGroups(phoneContacts);

        //assert
        assertEquals(expectedGroupNumber, groupNumber);
    }

    @Test
    public void testGroupPresenceCheck() {
        //arrange
        String testGroupName = "Work";
        String[] groupName = new String[]{"Family", "Work", "Friends"};
        for (String name : groupName) phoneContacts.addGroup(name);

        //assert
        assertTrue(phoneContacts.groupPresenceCheck(testGroupName));
    }

    @Test
    public void testGroupAbsenceCheck() {
        //arrange
        String testGroupName = "Club";
        String[] groupName = new String[]{"Family", "Work", "Friends"};
        for (String name : groupName) phoneContacts.addGroup(name);

        //assert
        assertFalse(phoneContacts.groupPresenceCheck(testGroupName));
    }

    @Test
    public void testGetСontactByPhoneNumber() {
        //arrange
        Contact testContact;
        Contact expectedContact = new Contact("Ivan Petrov", "+79354445566");
        String testNumber = "+79354445566";

        Contact[] contacts = {new Contact("Ivan Petrov", "+79354445566"),
                new Contact("Petr Ivanov", "+79374497788"),
                new Contact("Nikolai Semenov", "+79374494353"),
                new Contact("Michaela Nekrasova", "+79384498900"),
                new Contact("Svetlana Semenova", "+793744947777")};

        // создание тестового списка контактов
        List<Contact> testList = new ArrayList<>();
        for (Contact contact : contacts) {
            Collections.sort(testList);
            int position = Collections.binarySearch(testList, contact);
            if (position == -1) position = Math.abs(position) - 1;
            testList.add(position, contact);
        }

        // набор наименований групп
        String[] groupName = new String[]{"Family", "Work", "Friends"};

        //создание тестового объекта
        for (String name : groupName) phoneContacts.addGroup(name);
        for (Contact contact : contacts) phoneContacts.addContactToMap(contact, groupName);

        //act
        testContact = phoneContacts.getСontactByPhoneNumber(testNumber);

        //assert
        assertEquals(expectedContact, testContact);
    }
}