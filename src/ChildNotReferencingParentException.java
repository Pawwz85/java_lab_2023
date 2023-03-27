public class ChildNotReferencingParentException extends ParentChildInconsistencyException{
    public ChildNotReferencingParentException(Person parent, Person child, String message) {
        super(parent, child, message);
    }
}
