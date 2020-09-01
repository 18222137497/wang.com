package com.wang.service;

import com.wang.domain.Syslog;

import java.util.List;

/**
 * 日志业务层
 */
public interface SysLogService {
    /**
     * 保存日志
     * @param syslog
     * @throws Exception
     */
   void save(Syslog syslog) throws Exception;

    /**
     * 查询所有日志
     * @return
     * @throws Exception
     */
    List<Syslog> findAll() throws Exception;
}
