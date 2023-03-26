public class UndefinedPersonReferenceException extends Exception{
    Person parent;
    public UndefinedPersonReferenceException(String message, Person parent) {
        super(String.format("%s. Also person %s is not defined", message, parent.getName()));
    }
}
