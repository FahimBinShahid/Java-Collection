package dev.lpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
//        printData("Phone List", phones);
//        printData("Email List", emails);

        Set<Contact> emailContact  = new HashSet<>(emails);
        Set<Contact> phoneContact = new HashSet<>(phones);

        printData("Phone List",phoneContact);
        printData("Email List" , emailContact);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmails(" Forest");
        robinHood.addEmails(" Forest");
        robinHood.replaceEmailifExist("RD@forest.com","rdr37@rockstar.com");
        System.out.println(robinHood);

        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(emailContact);
        unionAB.addAll(phoneContact);
        printData("(A ∪ B) Union of emails (A) with phones (B)" ,unionAB);

        Set<Contact> intersectAB = new HashSet<>(emailContact);
        intersectAB.retainAll(phoneContact);
        printData("(A ∩ B) Intersect emails (A) and phones (B)",intersectAB);

        Set<Contact> intersectBA = new HashSet<>(phoneContact);
        intersectBA.retainAll(emailContact);
        printData("(B ∩ A) Intersect phones (B) and emails (A)",intersectBA);

        Set<Contact> AMinusB = new HashSet<>(emailContact);
        AMinusB.removeAll(phoneContact);
        printData("(A - B)  emails (A) - phones (B)",AMinusB);

        Set<Contact> BMinusA = new HashSet<>(phoneContact);
        BMinusA.removeAll(emailContact);
        printData("(B - A)  phones (B) - emails (A)",BMinusA);

        Set<Contact> symmetricDiff = new HashSet<>(AMinusB);
        symmetricDiff.addAll(BMinusA);
        printData("Symmetric Difference",symmetricDiff);

        Set<Contact> symmetricDiff2 = new HashSet<>(unionAB);
        symmetricDiff2.removeAll(intersectAB);
        printData("Symmetric Difference",symmetricDiff2);





    }

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
