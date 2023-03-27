import java.nio.file.Path;

public class FileCommander {
    Path path;
    public FileCommander ()
    {
        this.path = Path.of(System.getProperty("user.home"));
    }
    public String pwd()
    {
        return path.toString();
    }

    public void cd (Path path)
    {
        this.path = this.path.resolve(path).normalize();
    }
}
