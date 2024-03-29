package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

   @Insert("insert into community.table_user (account_id,name,token,gmt_create,gmt_modified) " +
           "VALUES (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
   public void insert(User user);

   @Select("select * from community.table_user where token = #{token}")
   User findByToken(@Param("token") String token);
}
