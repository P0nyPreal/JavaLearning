package com.itheima;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    // 测试 getAge 方法
    // 当输入为null时，期望抛出IllegalArgumentException异常
    void getAge_NullInput_ThrowsIllegalArgumentException() {
        // 使用assertThrows验证 userService.getAge(null) 调用是否抛出了IllegalArgumentException异常
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getAge(null);
        });
        // 验证抛出的异常消息是否为"无效的身份证号码"
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    void getAge_InvalidLength_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getAge("12345678901234567");
        });
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    void getAge_ValidInput_ReturnsCorrectAge() {
        int age = userService.getAge("123456199001011234");
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        int expectedAge = Period.between(birthDate, LocalDate.now()).getYears();
        assertEquals(expectedAge, age);
    }

    // 测试 getGender 方法
    @Test
    void getGender_NullInput_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender(null);
        });
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    void getGender_InvalidLength_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender("12345678901234567");
        });
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    void getGender_ValidInputOdd_ReturnsMale() {
        assertEquals("男", userService.getGender("123456199001011235"));
    }

    @Test
    void getGender_ValidInputEven_ReturnsFemale() {
        assertEquals("女", userService.getGender("123456199001011224"));
    }
}
