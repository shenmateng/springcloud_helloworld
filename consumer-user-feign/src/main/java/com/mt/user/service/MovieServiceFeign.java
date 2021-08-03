package com.mt.user.service;

import com.mt.user.pojo.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "PROVIDERMOVIE",fallback = MovieFeignExceptionHandlerService.class)  //与被调用的服务名称一致
public interface MovieServiceFeign {

    @GetMapping("/movie")
    public Movie getNewMovie();  //与被调用服务端映射方法一致
}
