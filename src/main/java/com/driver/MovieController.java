package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    //1. Add a movie: POST /movies/add-movie
    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovieSe(movie);
        return new ResponseEntity<>("Movie added successfully",HttpStatus.CREATED);
    }
    //2. Add a director: POST /movies/add-director
    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity<>("Director added successfully",HttpStatus.CREATED);
    }
    //3. Pair an existing movie and director: PUT /movies/add-movie-director-pair
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie,@RequestParam String director) {
        movieService.addMovieDirectorPair(movie,director);
        return new ResponseEntity<>("Movie director pair created",HttpStatus.CREATED);
    }
    //4. Get Movie by movie name: GET /movies/get-movie-by-name/{name}
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        Movie res = movieService.getMovieByName(name);
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }
    //5. Get Director by director name: GET /movies/get-director-by-name/{name}
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {
        return new ResponseEntity<>(movieService.getDirectorByName(name),HttpStatus.ACCEPTED);
    }
    //6. Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(director),HttpStatus.ACCEPTED);
    }
    //7. Get List of all movies added: GET /movies/get-all-movies
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.ACCEPTED);
    }
    //8. Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director) {
        return new ResponseEntity<>(movieService.deleteDirectorByName(director),HttpStatus.CREATED);
    }
    //9. Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        return new ResponseEntity<>(movieService.deleteAllDirectors(),HttpStatus.CREATED);
    }
}
