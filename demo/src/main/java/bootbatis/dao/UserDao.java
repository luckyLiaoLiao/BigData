package bootbatis.dao;

import bootbatis.pojo.User;
import bootbatis.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  //告诉springboot这是一个mybatis的mapper类
//加上这个注解以后，在mapper的xml文件的namespace就可以直接使用这个文件的接口
@Repository  //将userDao交由spring容器管理
public interface UserDao {

//    查询所有用户
    public List<User> listUser();

//    根据用户名来查询用户，并分页展示
    public List<User> listUserByName(UserQuery userQuery);
}
