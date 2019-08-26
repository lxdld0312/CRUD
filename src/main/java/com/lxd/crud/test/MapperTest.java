package com.lxd.crud.test;

import com.lxd.crud.bean.Employee;
import com.lxd.crud.dao.DepartmentMapper;
import com.lxd.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 * 推荐spring的项目可以使用spring的单元测试，可以自动注入我们需要的组件
 *  1、导入springTest模块
 *  2、@ContextConfiguration指定spring配置文件的路径
 *  3、直接autowired
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){
        /*// 窗口SpringIOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取mapper
        ioc.getBean(DepartmentMapper.class);*/
        /*System.out.println(departmentMapper);*/

        /* 1、插入几个部门 */
        /*departmentMapper.insertSelective(new Department(null, "开发部"));
        departmentMapper.insertSelective(new Department(null, "测试部"));*/

        /* 2、员工插入 */
        /*Employee employee = new Employee();
        employee.setdId(null);
        employee.setEmpName("张三");
        employee.setEmail("123456@163.com");
        employee.setGender(Employee.SEX_M);
        employee.setdId(7);
        employeeMapper.insertSelective(employee);*/

        /* 3、批量插入多个员工：批量：使用可以执行批量操作的sqlSession */

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i<1000;i++){
            String uuid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insert(new Employee(null, uuid, Employee.SEX_M, uuid+"@163.com", 7));
        }
    }
}
