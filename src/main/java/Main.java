import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа Справочник");
        PhoneContacts phoneContacts = new PhoneContacts();

        groupCreate(phoneContacts, scanner);
        contactsListCreate(phoneContacts, scanner);

        System.out.print(phoneContacts);

    }

    private static void groupCreate(PhoneContacts phoneContacts, Scanner scanner) {
        while (true) {
            System.out.println("Создать группу контактов (введите название или введите <нет>)?");
            System.out.print("> ");
            String nameGroup = scanner.nextLine();

            if (nameGroup.equals("нет")) {
                break;
            } else {
                phoneContacts.addGroup(nameGroup);
            }
        }
     }

    private static void contactsListCreate(PhoneContacts phoneContacts, Scanner scanner) {
        Contact nextContact;

        while (true) {
            System.out.println("Создать контакт (введите наименование и его номер или введите <нет>)?");
            System.out.print("> ");

            String dataContact = scanner.nextLine();

            if (dataContact.equals("нет")) {
                break;
            } else {
                String[] contactParts = dataContact.split(" ");
                String name = contactParts[0] + " " + contactParts[1];
                String phone = contactParts[2];

                nextContact = new Contact(name, phone);
            }

            System.out.println("Укажите группы контакта через пробел:");
            System.out.print("> ");

            String dataGroup = scanner.nextLine();
            String[] groupParts = dataGroup.split(" ");

            phoneContacts.addContact(nextContact, groupParts);
        }
    }
}


