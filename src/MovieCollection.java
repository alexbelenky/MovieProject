import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<String> actors = new ArrayList<>();
    public MovieCollection() {
        run();
    }
    private void run() {
        setMovies();
        setActors();
        welcome();
    }

    private void setMovies() {
        try {
            Scanner input = new Scanner(new File("src\\movies_data.csv"));
            for (int i = 0; i <= 4999; i++) {
                String line = input.nextLine();
                if (!line.equals("title, cast, director, overview, runtime, userRating")) {
                    String[] split = line.split(","); //cast keeps the |
                    movies.add(new Movie(split[0], split[1], split[2], split[3], split[4], split[5]));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sortMovieArray();
    }

    private void setActors() {
        for (Movie movie : movies) {
            String[] actors = movie.getCast().split(",");
            this.actors.addAll(Arrays.asList(actors));
        }
    }

    private void welcome() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";
        Scanner scanner = new Scanner(System.in);

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
                System.out.println("\n");
            } else if (menuOption.equals("c")) {
                searchCast();
                System.out.println("\n");
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void searchTitles() {
        String term = askQuestion("Enter a title search term: ");
        ArrayList<Movie> matches = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(term.toLowerCase())) { //checks if the title contains the term
                matches.add(movie);
            }
        }
        for (int i = 0; i < matches.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, matches.get(i).getTitle());
        }
        if (matches.size() != 0) {
            try {
                int choice = Integer.parseInt(askQuestion("\nWhich movie would you like to know more about?\nNumber: "));
                System.out.println(matches.get(choice - 1));
            } catch (Exception e) {
                System.out.println("Invalid choice!");
            }
        } else {
            System.out.println("Movie wasn't found!");
        }
        askQuestion("Press enter to continue");
    }

    private void searchCast() {
        String part = askQuestion("Enter the name (first or last): ");
        ArrayList<String> matches = new ArrayList<>();

        for (String actor : actors) {
            if (actor.toLowerCase().contains(part.toLowerCase())) {
                matches.add(actor);
            }
        }

        if (matches.size() != 0) {
            for (int i = 0; i < matches.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, matches.get(i));
            }
        } else {
            System.out.println("Actor not found!");
        }
        askQuestion("Press enter to continue");
    }

    //insertion sort
    private void sortMovieArray() {
        int beforeWord;
        int checkWord;
        for (int i = 1; i < movies.size(); i++) {
            beforeWord = i - 1;
            checkWord = i;
            while (movies.get(checkWord).getTitle().compareTo(movies.get(beforeWord).getTitle()) < 0) {
                movies.set(beforeWord, movies.set(checkWord, movies.get(beforeWord)));
                beforeWord--;
                checkWord--;
                if (beforeWord == -1) {
                    break;
                }
            }
        }
    }

    //returns the input given by the user
    private static String askQuestion(String ques) {
        Scanner scan = new Scanner(System.in);
        System.out.print(ques);
        return scan.nextLine();
    }

}
