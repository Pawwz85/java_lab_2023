import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<String> ls()
    {
        Comparator<Path> comparator = (path2, path1)-> Boolean.compare(Files.isDirectory(path1), Files.isDirectory(path2));
        comparator =  comparator.thenComparing(Path::getFileName);
        try  {
        return Files.list(path)
                .sorted(comparator)
                 .map( o->{
                    if(Files.isDirectory(o))
                    {
                        return  "[" + o.getFileName().toString()+"]";
                    }
                    return o.getFileName().toString();
                })
                .collect(Collectors.toList());

        }
        catch (IOException e)
        {

            throw new RuntimeException();
        }
    }
    public List<String> find(String substring) {
        try{
            return Files.walk(this.path)
                    .map(o->o.getFileName())
                    .filter(o->o.toString().contains(substring))
                    .map (o ->o.toString())
                    .collect(Collectors.toList());
    }catch (IOException e)
        {

            throw new RuntimeException();
        }
    }
}
