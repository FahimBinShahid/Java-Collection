import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsMain {
    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("-------------------------------");

        Map<String, Contact> contacts = new HashMap<>();

        for (Contact contact : fullList) {
            contacts.put(contact.getName(), contact);
        }
            contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));
            System.out.println(contacts.get("Charlie Brown"));

            System.out.println(contacts.get("Chuck Brown"));

            Contact defaultContact = new Contact("Chuck Brown");
            System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));

            System.out.println("______________________________");
            contacts.clear();
            for (Contact contact : fullList) {
                Contact duplicate = contacts.put(contact.getName(), contact);

                if (duplicate != null) {
//                System.out.println("duplicate = " + duplicate);
//                System.out.println("current=" + contact);
                    contacts.put(contact.getName(), contact.mergeContactData(duplicate));
                }
            }
            contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("_________________");
        contacts.clear();

        for (Contact contact : fullList) {
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            if(duplicate != null) {
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value= " + v));

        System.out.println("-------------------------------");
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergeContactData

                ));
        contacts.forEach((k, v) ->System.out.println("key=" + k + ", value= " + v));

        System.out.println("------------------------------");
        for(String contactName : new String[] {"Daisy Duck", "Daffy Duck","Scrooge McDuck"}){
            contacts.computeIfAbsent(contactName, k -> new Contact(k));
        }
        contacts.forEach((k, v) -> System.out.println("key " + k + ", value= " + v));

        System.out.println("------------------------------");
        for(String contactName : new String[] {"Daisy Duck", "Daffy Duck","Scrooge McDuck"}){
            contacts.computeIfPresent(contactName, (k, v) -> {
                v.addEmails("Fun Place");
                return v;
            });
        }

        contacts.forEach((k, v) -> System.out.println("key " + k + ", value= " + v));

        System.out.println("_________________________________");
        contacts.replaceAll((k,v) -> {
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailifExist("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((k, v) -> System.out.println("key " + k + ", value= " + v));

        System.out.println("-----------------------------");
        Contact daisy = new Contact("Daisy Jane Duck" , "daisyj@duck.com");

        Contact replaceContact = contacts.replace("Daisy Duck" , daisy);
        System.out.println("daisy = " + daisy);
        System.out.println("replacedContact = " + replaceContact);
        contacts.forEach((k, v) -> System.out.println("key " + k + ", value= " + v));

        System.out.println("---------------------------------");
        Contact updatedDaisy = replaceContact.mergeContactData(daisy);
        System.out.println("updatedDaisy = " + updatedDaisy);
        boolean success = contacts.replace("Daisy Duck", replaceContact, updatedDaisy);

        if(success){
            System.out.println("Successfully replaced element");
        } else{
            System.out.println("Did not match on both key: %s and value: %s %n"
                    .formatted("Daisy Duck", replaceContact));
        }
        contacts.forEach((k, v) -> System.out.println("key =" + k + ", value= " + v));
        System.out.println("_____________________________");
        success = contacts.remove("DaisyDuck" , daisy);

        if(success) {
            System.out.println("Successfully removed");
        } else {
            System.out.println("Dis not match on both key: %s and value: %s %n"
                    .formatted("Daisy Duck", daisy));
        }
        contacts.forEach((k, v) -> System.out.println("key" + k + ", value " + v));

    }
}


