import java.util.*;

public class Main {

    enum WeekDay {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public static void main(String[] args) {
        List<WeekDay> annsWorkDays = new ArrayList<>(List.of(WeekDay.MONDAY,
                WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));

        var annsDaysSet = EnumSet.copyOf(annsWorkDays);
        System.out.println(annsDaysSet.getClass().getSimpleName());
        annsDaysSet.forEach(System.out::println);

        var allDaysSet = EnumSet.allOf(WeekDay.class);
        System.out.println("_______________________");
        allDaysSet.forEach(System.out::println);

        Set<WeekDay> newPersonDays = EnumSet.complementOf(annsDaysSet);
        System.out.println("_______________________");
        newPersonDays.forEach(System.out::println);

        Set<WeekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(annsDaysSet);
        System.out.println("-----------------------------");
        anotherWay.forEach(System.out::println);

        Set<WeekDay> BusinessDay = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
        System.out.println("____________________");
        BusinessDay.forEach(System.out::println);


//        Set<WeekDay> SomeDay = EnumSet.noneOf(allDaysSet);
//        System.out.println("----------------------");
//        SomeDay.forEach(System.out::println);

        Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);
        employeeMap.put(WeekDay.FRIDAY, new String[] {"Maria", "Sharpova", "WilliamSon"});
        employeeMap.put(WeekDay.MONDAY, new String[] {"Maria", "Sharpova"});

        employeeMap.forEach((k, v) -> System.out.println(k + " : " + Arrays.toString(v)));
    }
}