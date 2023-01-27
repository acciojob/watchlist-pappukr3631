package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovieSe(Movie movie) {
        movieRepository.addMovieRepo(movie);
    }
    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        movieRepository.addMovieDirectorPair(movie,director);
    }

    public Movie getMovieByName(String movie) {
        return movieRepository.getMovieByName(movie);
    }

    public Director getDirectorByName(String dirName) {
        return movieRepository.getDirectorByName(dirName);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieRepository.getMoviesByDirectorName(director);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String director) {
        return movieRepository.deleteDirectorByName(director);
    }

    public String deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}
