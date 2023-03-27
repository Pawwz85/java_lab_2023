public class ParentNotReferencingChildException extends ParentChildInconsistencyException{
    public ParentNotReferencingChildException(Person parent, Person child, String message) {
        super(parent, child, message);
    }
}
