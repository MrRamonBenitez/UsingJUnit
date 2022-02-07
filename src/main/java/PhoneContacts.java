import java.util.*;

public class PhoneContacts {

    private Map<String, List<Contact>> groupMap;

    public PhoneContacts() {
        this.groupMap = new HashMap<>();
    }

    public Map<String, List<Contact>> getGroupMap() {
        return groupMap;
    }

    public boolean addGroup (String groupName) {
        if (groupMap.containsKey(groupName)) {
            System.out.println("Группа уже существует!");
            return false;
        }
        groupMap.put(groupName, new ArrayList<>());
        return true;
    }

    public boolean addContactToMap (Contact contact, String[] groupNames) {
        for (String name : groupNames) {
            List<Contact> contactList = groupMap.get(name);
            if (contactList == null) {
                return false;
            }
            Collections.sort(contactList);
            int position = Collections.binarySearch(contactList, contact);
            if (position == -1) position = Math.abs(position) - 1;
            contactList.add(position, contact);
        }
        return true;
    }

    public int countingNumberGroups(PhoneContacts phoneContacts) {
        Set<String> keyMassive = phoneContacts.groupMap.keySet();
        return keyMassive.size();
    }

    public boolean groupPresenceCheck(String groupName) {
        return groupMap.containsKey(groupName);
    }

    public Contact getСontactByPhoneNumber(String number) {
        Contact tempContact = null;
        Iterator<String> key = groupMap.keySet().iterator();
        while (key.hasNext()) {
            List<Contact> contactList = groupMap.get(key.next());
            ListIterator<Contact> i = contactList.listIterator();
            for (ListIterator<Contact> it = i; it.hasNext();) {
                tempContact = it.next();
                if (number.equals(tempContact.getMobileNumber()))
                    break;
            }
        }
        return tempContact;
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
        if (this == o) return true;
        if (!(o instanceof PhoneContacts)) return false;
        PhoneContacts that = (PhoneContacts) o;
        return Objects.equals(getGroupMap(), that.getGroupMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupMap());
    }
}
