package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Config {

    private static String appData;
    private static String UserOS = (System.getProperty("os.name")).toUpperCase();

    public static HashMap<String, Object> loadConfig() {
        if(UserOS.contains("WIN")) {
            appData = System.getenv("AppData");
        } else {
            appData = System.getProperty("user.home");
            appData += "/Library/Application Support";
        }

        Scanner conf = null;
        try {
            conf = new Scanner(new File(appData + "/YouGit/conf.yougit"));
        } catch(Exception e) {
            System.out.println("Could not load config");
            return null;
        }
        HashMap<String, Object> confMap = new HashMap<>();
        while(conf.hasNextLine()) {
            String current = conf.nextLine();
            String[] splitLine = current.split("=");
            confMap.put(splitLine[0], splitLine[1]);
        }
        System.out.println("Successfully found and loaded config");
        return confMap;
    }

    public static void createConfig() throws IOException {
        File confDir = new File(appData + "/YouGit/");
        confDir.mkdirs();
        File conf = new File(appData + "/YouGit/conf.yougit");
        conf.createNewFile();
    }

    public static void addKey(String key, Object val) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(appData + "/YouGit/conf.yougit", true));
        out.println("\n" + key + "=" + val);
        out.close();
    }

}
