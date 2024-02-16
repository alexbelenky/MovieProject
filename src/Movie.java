public class Movie {
    private final String title;
    private final String cast;
    private final String director;
    private final String overview;
    private final String runtime;
    private final String userRating;

    public Movie(String title, String cast, String director, String overview, String runtime, String userRating) {
        this.title = title;
        this.cast = cast.replaceAll("\\|", ", ");
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getOverview() {
        return overview;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getUserRating() {
        return userRating;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\nRuntime: %s minutes\nDirected by: %s\nCast: %s\nOverview: %s\nUser rating: %s", title, runtime, director, cast, overview, userRating);
    }
}
