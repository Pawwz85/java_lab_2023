import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public boolean is_real;
    public Person(String name, LocalDate birth) {
        this(name, birth, null);
    }

    public Person(String name, LocalDate birth, LocalDate death) {
        this.is_real = true;
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

    public String getName() {
        return name;
    }
    public LocalDate getBirth() {
        return birth;
    }

    public LocalDate getDeath() {
        return death;
    }

    public Person[] getParents() {
        return parents;
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
    void checkForStrangeParent() throws ParentingAgeException{
        if(parents[0] != null)
        {
            if(!birth.isAfter(parents[0].getBirth()))
                throw new ParentingAgeException("");
            if(parents[0].death != null && birth.isAfter(parents[0].getDeath()))
                throw new ParentingAgeException("");
            if( parents[0].getBirth().plusYears(15).isAfter(birth))
                throw new ParentingAgeException("");
            if(!parents[0].getBirth().plusYears(50).isAfter(birth))
                throw new ParentingAgeException("");
        }
        if(parents[1] != null)
        {
            if(!birth.isAfter(parents[1].getBirth()))
                throw new ParentingAgeException("");
            if(parents[1].death != null && birth.isAfter(parents[1].getDeath()))
                throw new ParentingAgeException("");
            if( parents[1].getBirth().plusYears(15).isAfter(birth))
                throw new ParentingAgeException("");
            if(!parents[1].getBirth().plusYears(50).isAfter(birth))
                throw new ParentingAgeException("");
        }
    }
     public static Person loadPerson (String filePath) throws FileNotFoundException, AmbigiousPersonException
    {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        String name = scan.nextLine();
        LocalDate birthTime = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd.MM.uuuu"));

        LocalDate deathTime;
        String nextLine;
        deathTime = null;
        Person parent1 = null, parent2 = null;
        while(scan.hasNext())
        {
            nextLine = scan.nextLine();
            if (nextLine.compareTo("Rodzice:") == 0)
            {
                parent1 = new ReferencedPerson(scan.nextLine());
                if(scan.hasNext())
                    parent2 = new ReferencedPerson(scan.nextLine());
            }
            else
            deathTime = LocalDate.parse(nextLine, DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        }




        for(var i: names)
        {
            if(i.person.name.compareTo(name) == 0)
            {
                throw new AmbigiousPersonException(name, filePath, "We found a clone.");
            }
        }
        try
        {
            Person result = new Person(name, birthTime, deathTime, parent1, parent2);
            names.add(new TemporaryPerson(result, filePath));
            return result;
        }
       catch (IncestException e)
        {
            throw new RuntimeException();
        }


    }
    public static Person[] loadArray(String[] Paths) throws UndefinedPersonReferenceException
    {
        Vector<Person> resultV  = new Vector<Person>();
        for (var path: Paths )
        {
            try
            {
                resultV.add(loadPerson(path));
            }
            catch (java.io.FileNotFoundException | AmbigiousPersonException e) {};
        }
        Person result[] = resultV.toArray(new Person[resultV.size()]);
        for (int i = 0; i < resultV.size(); i++) {
            if(result[i].parents[0] != null)
            for (int j = 0; j < resultV.size(); j++) {
            if(result[j].getName().compareTo(result[i].parents[0].getName()) == 0)
                result[i].parents[0] = result[j];
            }

            if(result[i].parents[1] != null)
                for (int j = 0; j < resultV.size(); j++) {
                    if(result[j].getName().compareTo(result[i].parents[1].getName())  ==0)
                        result[i].parents[1] = result[j];
                }
        }

        for (var person:result)
        {
            if(person.parents[0] != null && person.parents[0].is_real == false)
            throw new UndefinedPersonReferenceException("",person.parents[0]);
            if(person.parents[1] != null && person.parents[1].is_real == false)
            throw new UndefinedPersonReferenceException("",person.parents[1]);
            try{person.checkForStrangeParent();} catch (ParentingAgeException e)
            {
                System.out.println(String.format("%s ma anomalną datę urodzenia %s. Czy potwierdzasz dodanie takiej osoby [Y]?",
                        person.name, person.birth.toString()));
                try {
                    if(System.in.read() != 'Y')
                        person = null;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }


        }
        return result;
    }
}