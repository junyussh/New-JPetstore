package org.csu.jpetstore.dao;

import org.apache.ibatis.annotations.*;
import org.csu.jpetstore.bean.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {
    /**
     * Find user by user's id
     * @param id
     * @return Account
     */
    @Select("select * from Account where id = #{ID}")
    Account findAccountByID(@Param("ID") String id);

    @Select("SELECT * FROM Account")
    List<Account> findAllAccount();

    @Insert("INSERT into Account (id, username, password, email, firstName, lastName, status, address1, address2, city, state, zip, country, phone) " +
            "VALUES(#{id}, #{username}, #{password}, #{email}, #{firstName}, #{lastName}, #{status}, #{address1},  #{address2}, #{city}, #{state}, #{zip}, #{country}, #{phone})")
    void insertAccount(Account account);

    @Update("UPDATE Account SET username=#{username}, password=#{password}, email=#{email}, firstName=#{firstName}, lastName=#{lastName}, address1=#{address1}, address2=#{address2}, city=#{city}, state=#{state}, zip=#{zip}, country=#{country}, phone=#{phone} WHERE id=#{id}")
    void updateAccountInfo(Account account);

    @Update("UPDATE Account SET status=#{status} WHERE id=#{id}")
    void updateAccountStatus(@Param("id") String id, @Param("status") boolean status);

    @Update("UPDATE Account SET role=#{role} WHERE id=#{id}")
    void updateAccountRole(@Param("id") String id, @Param("role") String role);

    @Delete("DELETE FROM Account WHERE id=#{id}")
    void deleteAccount(String id);
}
