package block1processfileandstreams.block1_process_file_and_streams.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Person {

    private String name;

    private String town;

    private String age;

    @Override
    public String toString() {
        return "Name: " + name + ". Town: " + town + ". Age: " + age;
    }
}
