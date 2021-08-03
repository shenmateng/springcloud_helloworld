package com.mt.user.service;

import com.mt.user.dao.UserDao;
import com.mt.user.pojo.Movie;
import com.mt.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MovieServiceFeign  movieServiceFeign;   //调用Feign接口；其实就是调用远程服务



    /*
    * 根据ID得到用户对象
    * */
    public User getUserById(Integer id) {
        User user = userDao.getUser(id);
        return user;
    }

    /**
     * 购买最新的电影票
     * @param id 用户ID
     * @return
     */
    public Map<String, Object> buyMovie(Integer id) {
        Map<String, Object> result = new HashMap<>();
        //1.查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);
        //2.查到最新电影票
        //TODO 暂时为null,最终需要从电影服务中获取电影信息，进行添加
        Movie movie = movieServiceFeign.getNewMovie();
        result.put("movie", movie);
        return result;
    }


}
