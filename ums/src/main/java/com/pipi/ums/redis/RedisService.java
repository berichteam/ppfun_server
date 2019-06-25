package com.pipi.ums.redis;

import java.util.List;

/**
 * @author lazyb
 * @create 2019/6/25
 * @desc
 **/
public interface RedisService {

    boolean set(String key, String value);

    String get(String key);

    boolean expire(String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    <T> List<T> getListFromJson(String key, Class<T> clz);

    long lpush(String key, Object obj);

    long rpush(String key, Object obj);

    String lpop(String key);

    void del(String... key);

}
