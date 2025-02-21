package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("用户信息测试类HBB")
class test_User_Service_Hbb {
    private UserService userService;
    @BeforeEach
    void setUp() {userService = new UserService();}

    @Test
    public void getAge(){
//        UserService userService = new UserService();
        int age = userService.getAge("100000200010011031");
        System.out.println(age);
        Assertions.assertEquals(24, age);
//        Assertions.assertThrows(age, 18);
    }

    @DisplayName("好宝宝测试当前断言测试报错")
    @Test
    public void testThrow(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getAge("1");
        });
    }

    @DisplayName("好宝宝测试参数输入测试")
    @ParameterizedTest
    @ValueSource(strings = {"100000200010011011", "100000200010011031", "100000200010011051"})
    public void getAge_MultipleMaleIdAge(String idCard) {
        Assertions.assertEquals(24, userService.getAge(idCard));
    }


    @DisplayName("好宝宝测试csv参数输入的类，2025年2月22日18:37:28")
    @ParameterizedTest
    @CsvFileSource(resources = "testData.csv", numLinesToSkip = 1)
    public void getAge_MultipleMaleIdAge_csv(String idCard) {
        Assertions.assertEquals(24, userService.getAge(idCard));
    }

    



}
