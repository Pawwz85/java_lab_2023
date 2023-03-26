public class ReferencedPerson extends Person{
    public ReferencedPerson(String name)
    {
        super(name, null);
        super.is_real = false;
    }
}
