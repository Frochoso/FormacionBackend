package block1processfileandstreams.block1_process_file_and_streams;

import block1processfileandstreams.block1_process_file_and_streams.domain.Person;
import block1processfileandstreams.block1_process_file_and_streams.exception.InvalidLineFormatException;
import block1processfileandstreams.block1_process_file_and_streams.fileReader.Lector;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Block1ProcessFileAndStreamsApplication {

    public static Lector lector = new Lector();
    static List<Person> filteredPeople = new ArrayList<>();
    static List<Person> menores = new ArrayList<>();
    public static boolean isPrimerResidente = true;

    public static void main(String[] args) {
        try {
            List<Person> people = lector.readCSV("people.csv");
            for (Person person : people) {
                System.out.println(person.toString());
                almacenarMenores25(person);
                eliminarPersonas(person);
            }

            System.out.println(" ");
            System.out.println("MENORES DE 25: ");
            for (Person persona : menores) {
                System.out.println(persona);
                primerHabitante(persona, "Madrid");
                primerHabitante(persona, "Barcelona");
            }

            System.out.println(" ");
            System.out.println("USUARIOS ELIMINADOS: ");
            for (Person persona : filteredPeople) {
                System.out.println(persona);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InvalidLineFormatException e) {
            e.printStackTrace();
        }
    }

    public static void almacenarMenores25(Person person) {
        if (Integer.parseInt(person.getAge()) <= 25) {
            if (person.getAge().equals("0")) {
                person.setAge("unknown");
            }
            if (person.getTown().isEmpty()) {
                person.setTown("unknown");
            }

            menores.add(new Person(person.getName(), person.getTown(), person.getAge()));

        }
    }

    public static void eliminarPersonas(Person person) {
        if (person.getName().startsWith("A")) {

            if (person.getAge().equals("0")) {
                person.setAge("unknown");
            }
            if (person.getTown().isEmpty()) {
                person.setTown("unknown");
            }

            filteredPeople.add(new Person(person.getName(), person.getTown(), person.getAge()));
        }
    }

    public static void primerHabitante(Person persona, String poblacion) {
        if (isPrimerResidente) {
            Optional<Person> firstPersonFromWantedTown = menores.stream()
                    .filter(x -> persona.getTown().equalsIgnoreCase(poblacion))
                    .findFirst();

            if (firstPersonFromWantedTown.isPresent()) {
                System.out.println(" ");
                System.out.println("PRIMERA PERSONA DE "+poblacion+": ");
                System.out.println(persona.toString());
                System.out.println(" ");

                isPrimerResidente = false;
            }
        }
    }

}