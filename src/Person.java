import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Person implements Serializable {
    private String name;
    private LocalDate birth, death;
    private Person parents[] = new Person[2];
    private static Vector<TemporaryPerson> names = new Vector<>();
    public Person(String name, LocalDate birth) {
        this(name, birth, null);
    }

    public Person(String name, LocalDate birth, LocalDate death) {
        this.name = name;
        this.birth = birth;
        this.death = death;
        try {
            if (birth.isAfter(death)) {
                throw new NegativeLifespanException(birth, death, "Possible time-space loophole.");
            }
        } catch (NullPointerException e) {}
    }

    public Person(String name, LocalDate birth, LocalDate death, Person parent1, Person parent2) throws IncestException {
        this(name, birth, death);
        parents[0] = parent1;
        parents[1] = parent2;

        checkForIncest();
    }

    public Person(String name, LocalDate birth, Person parent1, Person parent2) throws IncestException {
        this(name, birth, null, parent1, parent2);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", death=" + death +
                ", parents=" + Arrays.toString(parents) +
                '}';
    }

    void checkForIncest() throws IncestException {
        if(parents[0] == null || parents[1] == null)
            return;
        for(var leftSideParent : parents[0].parents) {
            if (leftSideParent == null) continue;
            for (var rightSideParent : parents[1].parents) {
                if (rightSideParent == null) continue;
                if (leftSideParent == rightSideParent)
                    throw new IncestException(leftSideParent, this);
            }
        }
    }
     public static Person loadPerson (String filePath) throws FileNotFoundException, AmbigiousPersonException
    {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        String name = scan.nextLine();
        LocalDate birthTime = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd.MM.uuuu"));

        LocalDate deathTime;
        if(scan.hasNext())
        deathTime = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        else deathTime = null;


        for(var i: names)
        {
            if(i.person.name.compareTo(name) == 0)
            {
                throw new AmbigiousPersonException(name, filePath, "We found a clone.");
            }
        }
        Person result = new Person(name, birthTime, deathTime);
        names.add(new TemporaryPerson(result, filePath));
        return result;
    }
}