import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class main{
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        Comparator<Person> personComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }




        System.out.println("Count of minor persons: ");
    Stream<Person> minorPersonStream = persons.stream();
        System.out.println(minorPersonStream.filter(person -> person.getAge() < 18)
            .count());

        System.out.println("List of conscripts: ");
    Stream<Person> conscriptsPersonStream = persons.stream();
        System.out.println(conscriptsPersonStream.filter(person -> person.getAge() >= 18)
            .filter(person -> person.getAge() < 27)
            .map(person -> person.getFamily())
            .collect(Collectors.toList()));

        System.out.println("List of workers: ");
    Stream<Person> workersStream = persons.stream();
        System.out.println(workersStream
                .filter(
    person -> person.getAge() >= 18 &&
            ((person.getSex() == Sex.MAN && person.getAge() < 65) ||
            (person.getSex() == Sex.WOMAN && person.getAge() < 60)))
            .filter(person -> person.getEducation() == Education.HIGHER)
            .sorted(personComparator)
                        .map(person -> person.getFamily() + " " + person.getName())
            .collect(Collectors.toList()));
}
}
