package bootbatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //所有的get、set、toString方法
@AllArgsConstructor  //所有的有参构造
@NoArgsConstructor   //所有的无参构造

public class User {
    private Integer id;
    private String name;
    private String pwd;
    private String perm;

//    这里如果用lombok这个依赖就不需要手动写了

}
