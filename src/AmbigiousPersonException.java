public class AmbigiousPersonException extends Exception{

    String personName;
    public AmbigiousPersonException(String name)
    {
        this.personName = name;
    }
}
