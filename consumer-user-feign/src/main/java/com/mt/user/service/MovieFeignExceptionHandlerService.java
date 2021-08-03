package com.mt.user.service;

import com.mt.user.pojo.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieFeignExceptionHandlerService implements MovieServiceFeign {
    /**
     * 远程这个方法调用出问题就会调用此方法
     */
    @Override
    public Movie getNewMovie() {
        Movie movie = new Movie();
        movie.setId(-100);
        movie.setMovieName("无此电影呀...");
        return movie;
    }
}
