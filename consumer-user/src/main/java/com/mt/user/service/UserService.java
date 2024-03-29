package com.mt.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.mt.user.dao.UserDao;
import com.mt.user.pojo.Movie;
import com.mt.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;

    /*
     * 根据ID得到用户对象
     * */
    public User getUserById(Integer id) {
        User user = userDao.getUser(id);
        return user;
    }

    /**
     * 购买最新的电影票
     *
     * @param id 用户ID
     * @return
     */
    @HystrixCommand(fallbackMethod = "buyMovieFallbackMethod")
    public Map<String, Object> buyMovie(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //1.查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);
        //2.查到最新电影票
        //TODO 暂时为null,最终需要从电影服务中获取电影信息，进行添加
        Movie mo = restTemplate.getForObject("http://PROVIDERMOVIE/movie", Movie.class);
        result.put("movie", mo);
        return result;
    }

    public Map<String, Object> buyMovieFallbackMethod(Integer id) {
        User user = new User();
        user.setId(-1);
        user.setUserName("未知用户");
        Movie movie = new Movie();
        movie.setId(-100);
        movie.setMovieName("无此电影");
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("movice", movie);
        return result;
    }


}
