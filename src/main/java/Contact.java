import java.util.Objects;

public class Contact implements Comparable<Contact> {

    private String name;
    private String mobileNumber;

    public Contact(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return name + "\t" + mobileNumber + "\n";
    }

    @Override
    public int compareTo(Contact o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(getMobileNumber(), contact.getMobileNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getMobileNumber());
    }
}
