import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Function;

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
            case "ls" -> {
                boolean useColor = false, useFilter = false;
                String arg ="";
                for (var p : params)
                {
                    if(p.compareTo("--color") == 0)
                        useColor = true;
                    if(p.substring(0,9).compareTo("--filter=") == 0)
                    {
                        useFilter = true;
                        arg = p.substring(9);
                    }

                    }
                Function<String, String> folderDecorator = Function.identity();
                if (useColor) {
                    if(useFilter)
                    {
                        String finalArg = arg;
                        folderDecorator.andThen( (o  -> {
                            return new DecoratedString(o, "blue").findAndColor(finalArg,"red").toString();
                        }));
                    }else folderDecorator.andThen( o -> FileCommander.colorBlue(o));

                }
                  else
                    folderDecorator.andThen( o -> FileCommander.encloseWithBrackets(o));
                fileCommander.ls(folderDecorator);

            }
            case "pwd" -> fileCommander.pwd();
            case "cd" -> fileCommander.cd(Path.of(params[1]));
        }
    }
}
