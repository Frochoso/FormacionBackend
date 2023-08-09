package block1processfileandstreams.block1_process_file_and_streams.fileReader;

import block1processfileandstreams.block1_process_file_and_streams.domain.Person;
import block1processfileandstreams.block1_process_file_and_streams.exception.InvalidLineFormatException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Lector {

    List<Person> people = new ArrayList<>();

    public List<Person> readCSV(String filePath) throws IOException, InvalidLineFormatException {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {

                    String[] fields = line.split(":");
                    String name = fields[0].trim();
                    String town = fields.length > 1 ? fields[1] : "";
                    String age = fields.length > 2 ? fields[2] : "0";

                    if (line.isEmpty()) {
                        throw new InvalidLineFormatException(name + ":" + town + ":" + age + " -> " + "Línea vacía");
                    }

                    if (fields.length < 1) {
                        throw new InvalidLineFormatException(name + ":" + town + ":" + age + " -> " + "Falta el último delimitador de campo (:)");
                    }

                    if (name.isEmpty()) {
                        throw new InvalidLineFormatException(name + ":" + town + ":" + age + " -> " + "El nombre es obligatorio. Hay 3 espacios en el campo y esto se considera como blank.");
                    }

                    Person person = new Person(name, town, age);
                    people.add(person);

                } catch (Exception e) {
                    throw new InvalidLineFormatException("Error en línea " + line + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return people;
    }

}