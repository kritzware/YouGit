package sample;

public class BrowseController {

    public static Repository newRepository() {
        return new Repository("kritzbot", "https://github.com/kritzware/kritzbot.git");
    }

}
