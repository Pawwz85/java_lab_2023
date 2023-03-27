import java.io.*;
import java.nio.file.Path;

public class FileCommanderCLI {
    private FileCommander fileCommander;
    private BufferedReader reader;
    private BufferedWriter writer;
    public FileCommanderCLI (InputStream start, PrintStream end)
    {
        fileCommander = new FileCommander();
        reader = new BufferedReader(new InputStreamReader(start));
        writer = new BufferedWriter( new OutputStreamWriter(end));
    }
    public void eventLoop()
    {
        try
        {
            String line = reader.readLine();
            runCommand(line);
        }
        catch(IOException e)
        {
            throw new RuntimeException();
        }
    }
    private void runCommand(String line){
        String[] params = line.split(" ");
        if(params.length == 0)
            return;
        switch (params[0])
        {
            case "ls" -> fileCommander.ls();
            case "pwd" -> fileCommander.pwd();
            case "cd" -> fileCommander.cd(Path.of(params[1]));
        }
    }
}
