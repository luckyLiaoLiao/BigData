package bootbatis.service;

import bootbatis.pojo.User;
import bootbatis.pojo.query.UserQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {


    //    查询所有用户
    public List<User> listUser();

    //    根据用户名来查询用户，并分页展示
//    这里使用pageHelper里面的一个内置的对象，需要引入依赖
    public PageInfo<User> listUserByName(UserQuery userQuery);
}
