package com.wang.service.Impl;

import com.wang.dao.SysLogDao;
import com.wang.domain.Syslog;
import com.wang.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SysLogService")
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void save(Syslog syslog) throws Exception {
        sysLogDao.save(syslog);
    }

    @Override
    public List<Syslog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
