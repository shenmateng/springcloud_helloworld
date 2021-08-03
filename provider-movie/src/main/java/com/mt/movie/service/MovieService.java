package com.mt.movie.service;

import com.mt.movie.dao.MovieDao;
import com.mt.movie.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Value("${server.port}")
    private String port;

    @Autowired
    private MovieDao movieDao;

    public Movie getNewMovie() {
        System.out.println("当前电影服务的端口："+port);
        return movieDao.getNewMovie();
    }
}
