package com.example.future.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *-- account
 * 	- 账户类别(1用户，2管理) -- type -- int-- not null
 * 	- 姓名 -- name -- varchar(20)-- not null
 * 	- 性别 -- sex -- char(2) (配上锁死)-- not null
 * 	- 年龄 -- age -- int
 * 	- 邮箱或电话(主键) -- contact -- varchar(20)-- not null
 * 	- 密码 -- password -- varchar(20)-- not null
 */

@AllArgsConstructor
@Data
public class Account {
    private Integer type;
    private String   name ;
    private String  sex ;
    private Integer age;
	private String contact ;
    private String password ;
}
