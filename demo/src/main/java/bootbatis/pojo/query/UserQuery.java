package bootbatis.pojo.query;

//把需要查询的内容都封装到这个实体类里

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    private Integer pageNum = 1;  //当前的页码
    private Integer pageSize = 2; //每一页所显示的数量
    private String name;  //根据用户查询




}
