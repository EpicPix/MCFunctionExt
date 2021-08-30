package ga.epicpix.mcfext;

import java.io.File;

public class Start {

    public static void main(String[] args) {
        int ret = run(args);
        if(ret!=0) {
            System.exit(ret);
        }
    }

    public static int run(String[] args) {
        String compile = String.join(" ", args);
        if(compile.isEmpty()) {
            System.err.println("Missing compile path in arguments");
            return 1;
        }
        File file = new File(compile);
        if(!file.exists()) {
            System.err.println("File does not exist");
            return 1;
        }
        return 0;
    }

}
