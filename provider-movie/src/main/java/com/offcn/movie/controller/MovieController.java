package com.offcn.movie.controller;

import com.offcn.movie.pojo.Movie;
import com.offcn.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    //获取最新电影
    @GetMapping("/movie")
    public Movie getNewMovie(){
        return movieService.getNewMovie();
    }
}
