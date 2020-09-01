package com.wang.dao;

import com.wang.domain.Role;
import com.wang.domain.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户dao
 */
@Repository
public interface UserDao {
    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    @Select("select * from users")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status")
    })
    List<Users> findAll() throws Exception;

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(Users user) throws Exception;

    /**
     * 根据email查询用户
     * @param email
     * @return
     * @throws Exception
     */
    @Select("select * from users where email=#{email}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.wang.dao.RoleDao.findByUsersId"))
    })
    Users findByEmail(String email) throws Exception;

    /**
     * 根据id查询用户
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from users where id=#{userId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.wang.dao.RoleDao.findByUsersId"))
    })
    Users findById(String userId) throws Exception;

    /**
     * 查询该用户没有的角色
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findOtherRole(String id) throws Exception;

    /**
     * 给用户添加角色
     * @param id
     * @param rid
     * @throws Exception
     */
    @Insert("insert into users_role values(#{id},#{rid})")
    void addRoleToUser(@Param("id") String id,@Param("rid") String rid) throws Exception;
    //上面这个一旦有多个参数，他会识别不到第二个参数，必须加一个这个注解指定他的名字
}
