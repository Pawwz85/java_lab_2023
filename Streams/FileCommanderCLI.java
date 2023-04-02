import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
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
    public void eventLoop() {

        while(true){
        try {
            String line = reader.readLine();
            runCommand(line);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        }
    }
    private void runCommand(String line) throws IOException {
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
                    if(p.length() > 10 && p.substring(0,9).compareTo("--filter=") == 0)
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
                        folderDecorator = folderDecorator.andThen( (o  -> {
                            return new DecoratedString(o, "blue").findAndColor(finalArg,"red").toString();
                        }));
                    }else folderDecorator = folderDecorator.andThen( o -> FileCommander.colorBlue(o));

                }
                  else
                    folderDecorator = folderDecorator.andThen( o -> FileCommander.encloseWithBrackets(o));

                  List<String> res;
                  if(useFilter)
                    res = fileCommander.ls(folderDecorator, arg);
                  else
                    res =fileCommander.ls(folderDecorator,null);

                  for(var r:res)
                  {
                      System.out.println(r);
                      writer.write("\n"+r);
                  }

            }
            case "pwd" -> writer.write("\n" + fileCommander.pwd());
            case "cd" -> fileCommander.cd(Path.of(params[1]));
        }
    }
}
