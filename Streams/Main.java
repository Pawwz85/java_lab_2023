import java.nio.file.Path;

public class Main
{
    public static void main(String[] args)
    {
        FileCommander fileCommander =new FileCommander();
        System.out.println(fileCommander.pwd());
        //fileCommander.cd(Path.of("/home/Student/Pulpt"));
        System.out.println(fileCommander.pwd());
        System.out.println(fileCommander.ls());
    }
}