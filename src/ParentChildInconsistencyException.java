public class ParentChildInconsistencyException extends Exception{
    private Person parent, child;
    public  ParentChildInconsistencyException(Person parent, Person child, String message)
    {
        super(message);
        this.parent = parent;
        this.child = child;
    }

    @Override
    public String toString() {
        return "ParentChildInconsistencyException{" +
                "parent=" + parent +
                ", child=" + child +
                ", message=" + getMessage()+
                '}';
    }
}
