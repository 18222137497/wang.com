package com.lucky_home.service.impl;

import com.lucky_home.dao.JimgDao;
import com.lucky_home.dao.impl.JimgDaoUImpl;
import com.lucky_home.domain.jimg;
import com.lucky_home.service.JimgService;
import com.lucky_home.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

public class JimgServiceImpl implements JimgService {
    private JimgDao jimgDao=new JimgDaoUImpl();
    private JedisPool jedisPool=JedisUtil.getJedisPool();
    @Override
    public List<jimg> findBytidJimg(int tid_int) {
//        Jedis redis = jedisPool.getResource();
//        Set<Tuple> jimg = redis.zrangeWithScores("jimg", 0, -1);
//        List<jimg> list=null;
//        if (jimg.size()==0||jimg==null) {
//            list = jimgDao.findBytidJimg(tid_int);
//            for (jimg j:list){
//
//            }
//        }
        List<jimg> list = jimgDao.findBytidJimg(tid_int);
        return list;
    }

    @Override
    public jimg findJid(int jid_one) {
        return jimgDao.findJid(jid_one);
    }

    @Override
    public boolean findIfshoucang(int id, int jid_one) {
        int i=jimgDao.findIfshoucang(id,jid_one);
        if (jid_one==i){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addjimg(int id, int jid_one) {
        int i=jimgDao.addjimg(id,jid_one);
        if (i==0){
            return false;
        }else {
            return true;
        }
    }
}
