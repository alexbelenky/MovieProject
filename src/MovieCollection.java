import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<String> test = new ArrayList<>();
    public MovieCollection() {
        run();
    }
    private void run() {
        setMovies();
    }

    private void setMovies() {
        try {
            Scanner input = new Scanner(new File("src\\movies_data.csv"));
            for (int i = 0; i <= 4999; i++) {
                String line = input.nextLine();
                if (!line.equals("title, cast, director, overview, runtime, userRating")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
