package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    //One way is to add a movie you really want to watch
    private Map<String,Movie> movieMap;

    //another way is to add a new director you discovered and want to explore his work
    private Map<String,Director> directorMap;

    //third way is you want to add a director-movie(list) pair
    private Map<String, List<String>> directorMovieMap;

    MovieRepository(){
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.directorMovieMap = new HashMap<>();
    }
    public void addMovieRepo(Movie movie) {
        this.movieMap.put(movie.getName(),movie);
    }
    public void addDirector(Director director) {
        this.directorMap.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        if(movieMap.containsKey(movie) && directorMap.containsKey(director))
        {
            List<String> movieList = new ArrayList<>();
            if(directorMovieMap.containsKey(director))
            {
                directorMovieMap.get(director).add(movie);
                return;
            }
            movieList.add(movie);
            directorMovieMap.put(director,movieList);
        }
    }

    public Movie getMovieByName(String movie) {
        return movieMap.get(movie);
    }

    public Director getDirectorByName(String dirName) {
        return directorMap.get(dirName);
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movieList = directorMovieMap.get(director);
        return movieList;
    }

    public List<String> findAllMovies() {
        List<String> moviesList = new ArrayList<>(movieMap.keySet());
//        for(String movie : movieMap.keySet())
//            moviesList.add(movie);
        return moviesList;
    }

    public String deleteDirectorByName(String director) {
        List<String> movieList = directorMovieMap.get(director);
        directorMovieMap.remove(director);
        directorMap.remove(director);
        for(String movie : movieList){
            movieMap.remove(movie);
        }
        return "Director and related movies deleted successfully : " + director;
    }

    public String deleteAllDirectors() {
        for(String dir : directorMap.keySet())
        {
            if(directorMovieMap.containsKey(dir))
            {
                List<String> movies = directorMovieMap.get(dir);
                for(String movie : movies)
                {
                    movieMap.remove(movie);//deleting movies of respective directors
                }
                directorMovieMap.remove(dir);//clearing director movie pair
            }
            directorMap.remove(dir);//director map cleared
        }
        return "All directors and their movies deleted";
    }
}
