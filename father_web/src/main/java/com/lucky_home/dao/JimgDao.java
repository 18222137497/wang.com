package com.lucky_home.dao;

import com.lucky_home.domain.jimg;

import java.util.List;

public interface JimgDao {
    /**
     * 根据tid插在jimg
     * @param tid_int
     * @return
     */
    List<jimg> findBytidJimg(int tid_int);

    /**
     * 根据jid 查询一个jimg
     * @param jid_one
     * @return
     */
    jimg findJid(int jid_one);

    /**
     * 查询是否收藏
     * @param id
     * @param jid_one
     * @return
     */
    int findIfshoucang(int id, int jid_one);

    /**
     * 添加收藏
     * @param id
     * @param jid_one
     * @return
     */
    int addjimg(int id, int jid_one);
}
