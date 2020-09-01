package com.lucky_home.service.impl;

import com.lucky_home.dao.UserDao;
import com.lucky_home.dao.impl.UserDaoImpl;
import com.lucky_home.domain.Type;
import com.lucky_home.domain.User;
import com.lucky_home.service.UserService;
import com.lucky_home.util.JedisUtil;
import com.lucky_home.util.MailUtils;
import com.lucky_home.util.UuidUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    /**
     * 注册用户的方法
     * @param user
     * @return
     */
    @Override
    public boolean sign(User user) {
        //1.根据用户名查询对象
        User byUsername = userDao.findByUsername(user.getUsername());
        //2.判断是否为null
        if(byUsername!=null){
            //用户存在
            return false;
        }
        //3.不存在，保存用户
        //3.1设置激活码
        user.setCode(UuidUtil.getUuid());
        //3.2设置激活状态
        user.setStatus("N");
        userDao.save(user);
        String content="<a href='http://192.168.18.3:8080/father_web/activeUserServlet?code="+user.getCode()+"'>点击激活</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据激活码查询用户对象
        User user=userDao.findByCode(code);
        if (user!=null){
            //修改激活状态
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }

    }

    /**
     * 查询user是否存在的登录方法
     * @param user
     * @return
     */

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    /**
     * 缓存优化的type查询
     *  必须先启动redis服务器
     * @return
     */
    @Override
    public List<Type> findType() {
        //1.从rides中查询
        JedisPool jedisPool = JedisUtil.getJedisPool();
        Jedis redis = jedisPool.getResource();
        //查询分数(tid)和值  携带tid  tid为sortedset分数
        Set<Tuple> type = redis.zrangeWithScores("type", 0, -1);
        List<Type> t =null;
        if (type==null||type.size()==0){
            t = userDao.findType();
            for (Type y: t) {
                redis.zadd("type",y.getTid(),y.getTname());
            }
        }else {
            t=new ArrayList<Type>();
            for (Tuple tt:type) {
                Type type1=new Type();
                type1.setTname(tt.getElement());
                type1.setTid((int)tt.getScore());
                t.add(type1);
            }
        }
        return t;
    }
}
