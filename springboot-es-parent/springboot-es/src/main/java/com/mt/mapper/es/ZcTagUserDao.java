package com.mt.mapper.es;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZcTagUserDao {


    List<String> selectTagNamesByTagUuid(@Param("tagUuid") String tagUuid);



}

