package com.lucky_home.service;

import com.lucky_home.domain.jimg;

import java.util.List;

public interface JimgService {
    /**
     * 根据tid找jimg
     * @param tid_int
     * @return
     */
    List<jimg> findBytidJimg(int tid_int);

    /**
     * 用户jid 查询一个jimg
     * @param jid_one
     * @return
     */
    jimg findJid(int jid_one);

    /**
     * 检查是否收藏
     * @param id
     * @param jid_one
     * @return
     */
    boolean findIfshoucang(int id, int jid_one);

    boolean addjimg(int id, int jid_one);
}
