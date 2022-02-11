import java.util.*;

public class PhoneContacts {
    private final Map<String, List<Contact>> groupMap;

    public PhoneContacts() {
        this.groupMap = new HashMap<>();
    }

    public Map<String, List<Contact>> getGroupMap() {
        return groupMap;
    }

    public void addGroup (String groupName) {
        if (groupMap.containsKey(groupName)) {
            System.out.println("Группа уже существует!");
        } else {
            groupMap.put(groupName, new ArrayList<>());
        }
    }

    public void addContact (Contact contact, String[] groupNames) {
        for (String name : groupNames) {
            List<Contact> contactList = groupMap.get(name);
            if (!(contactList == null)) {
                Collections.sort(contactList);
                int position = Collections.binarySearch(contactList, contact);
                if (position == -1) {
                    position = 0;
                    contactList.add(position, contact);
                } else {
                    contactList.add(position, contact);
                }
            }
        }
    }

    public int getNumberGroups() {
        return groupMap.keySet().size();
    }

    public boolean groupPresenceCheck(String groupName) {
        return groupMap.containsKey(groupName);
    }

    public Contact getContactByPhoneNumber(String number) {
        Contact resultContact;
        for (String s : groupMap.keySet()) {
            List<Contact> contactList = groupMap.get(s);
            for (Contact contact : contactList) {
                resultContact = contact;
                if (number.equals(resultContact.getMobileNumber())) { return resultContact; }
            }
        }
        System.out.println("Такого контакта нет в списке!");
        return null;
    }

    public boolean contactPresenceCheck(String number) {
        for (String s : groupMap.keySet()) {
            List<Contact> contactList = groupMap.get(s);
            for (Contact tempContact : contactList) {
                if (number.equals(tempContact.getMobileNumber())) { return true; }
            }
        }
        System.out.println("Такого контакта нет в списке!");
        return false;
    }

    @Override
    public String toString () {
        StringBuilder outputGroup = new StringBuilder();
        for (Map.Entry<String, List<Contact>> entry : groupMap.entrySet()) {
            outputGroup.append("- ").append(entry.getKey()).append(":\n");
            for (Contact contact : entry.getValue()) {
                outputGroup.append("\t").append(contact);
            }
        }
        return outputGroup.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof PhoneContacts that)) { return false; }
        return Objects.equals(getGroupMap(), that.getGroupMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupMap());
    }
}
