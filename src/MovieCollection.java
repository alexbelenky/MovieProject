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
            for (int i = 0; i <= 4999; i++) { //manually scaled for the specific data set
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
            for (String actor : actors) {
                actor = actor.trim(); // Trim leading and trailing spaces
                if (!this.actors.contains(actor)) {
                    this.actors.add(actor);
                }
            }
        }
        sortCast();
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
        if (!matches.isEmpty()) {
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

        if (!matches.isEmpty()) {
            for (int i = 0; i < matches.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, matches.get(i));
            }
            searchActorMovies(matches);
        } else {
            System.out.println("Actor not found!");
        }
        askQuestion("Press enter to continue");
    }

    private void searchActorMovies(ArrayList <String> searchedActors) {
        int number = Integer.parseInt(askQuestion("Which would you like to see all movies for?\nEnter a number: "));
        try {
            String actor = searchedActors.get(number - 1);
            ArrayList<Movie> actorMovies = new ArrayList<>();
            for (Movie movie : movies) {
                if (movie.getCast().contains(actor)) {
                    actorMovies.add(movie);
                }
            }
            for (int i = 0; i < actorMovies.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, actorMovies.get(i).getTitle());
            }
            int numberMovie = Integer.parseInt(askQuestion("Which movie would you like to learn more about?\nEnter a number: "));
            System.out.println("\n" + actorMovies.get(numberMovie - 1));
        } catch(Exception e) {
            System.out.println("Actor not found!");
        }

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

    //insertion sort
    private void sortCast() {
        int beforeWord;
        int checkWord;
        for (int i = 1; i < actors.size(); i++) {
            beforeWord = i - 1;
            checkWord = i;
            while (actors.get(checkWord).compareTo(actors.get(beforeWord)) < 0) {
                actors.set(beforeWord, actors.set(checkWord, actors.get(beforeWord)));
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
