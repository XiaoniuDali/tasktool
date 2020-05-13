package com.bymz.tasktool.modules.sys.dict.service.impl;
import java.net.URL;
import java.util.List;

import com.bymz.tasktool.TasktoolApplication;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.bymz.tasktool.modules.sys.dict.service.SysDictDataService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



/**
 *
 * @desciption 用户管理测试类
 * @author ChenXiHua
 * @date 2019年2月19日
 *
 */
@SpringBootTest(classes = TasktoolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysDictDataServiceImplTest {

    private int port = 8080;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SysDictDataService dictDataService;

    @Before
    public void setUp() throws Exception {
        String url = String.format("%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    /**
     * 向"/test"地址发送请求，并打印返回结果
     * @throws Exception
     */
    //@Test
    public void test1() throws Exception {

        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/test", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }

    //@Test
    public void getAllTest() throws Exception {

        ResponseEntity<List> response = this.restTemplate.getForEntity(
                this.base.toString() + "/getAll", List.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }

//    @Test
//    public void getUserByIdTest() throws Exception {
//
//        ResponseEntity<User> response = this.restTemplate.getForEntity(
//                this.base.toString() + "/getUserById?id=1", User.class, "");
//        System.out.println(String.format("测试结果为：%s", response.getBody().toString()));
//    }

    @Test
    public void queryByDictTypeTest(){
        List<SysDictData> sys_status = dictDataService.queryByDictType("sys_status");
        System.out.println(sys_status.toString());
    }


    @Test
    public void findByDictTypeTest() throws Exception{
        String url = String.format("http://localhost:%d/", port);
        this.base = new URL(url);

        ResponseEntity<Object> responseEntity = this.restTemplate.getForEntity(
                this.base.toString() + "/dictData/findByDictType?dictType=sys_status",
                Object.class, ""
        );
        System.out.println(String.format("测试结果为：%s", responseEntity.getBody().toString()));
    }
}
