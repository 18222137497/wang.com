package com.wang.dao;

import com.wang.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员表dao
 */
@Repository
public interface MemberDao {
    /**
     * 查询所有会员信息
     * @return
     * @throws Exception
     */
    @Select("select * from member")
    List<Member> findAll() throws Exception;

    /**
     * 根据id查询某个会员信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
