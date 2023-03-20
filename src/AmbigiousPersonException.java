public class AmbigiousPersonException extends Exception{

    final String personName;
    final String path;

    public AmbigiousPersonException(String personName, String path, String message) {
        super(message);
        this.personName = personName;
        this.path = path;
    }

    @Override
    public String toString() {
        return "AmbigiousPersonException{" +
                "personName='" + personName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
