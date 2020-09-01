package com.lucky_home.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtil {
    //创建redis连接池
    private static JedisPool jedisPool;
    static {
        //创建Properties对象
        Properties properties=new Properties();
        //读取配置文件
        InputStream resourceAsStream = JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        //关联文件
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        //初始化jedisPool
        jedisPool=new JedisPool(jedisPoolConfig,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }
}
