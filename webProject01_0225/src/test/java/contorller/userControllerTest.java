package contorller;


import org.example.webproject01_0225.userTXT.user;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class userControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InputStream inputStream;

    @InjectMocks
    private org.example.webproject01_0225.contorller.userController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void list_ValidData_ReturnsUsers() throws Exception {
        String validData = "1,username1,password1,name1,25,2023-01-01 12:00:00\n" +
                           "2,username2,password2,name2,30,2023-02-01 13:00:00\n";
        when(inputStream.readAllBytes()).thenReturn(validData.getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    List<user> users = userController.list();
                    assertEquals(2, users.size());
                    assertEquals(1, users.get(0).getId());
                    assertEquals("username1", users.get(0).getUsername());
                    assertEquals("name1", users.get(0).getName());
                    assertEquals(25, users.get(0).getAge());
                    assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), users.get(0).getUpdateTime());
                });
    }

    @Test
    public void list_FileNotFound_ReturnsEmptyList() throws Exception {
        when(inputStream.readAllBytes()).thenThrow(new RuntimeException("File not found"));

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    List<user> users = userController.list();
                    assertEquals(0, users.size());
                });
    }

    @Test
    public void list_InvalidData_ReturnsEmptyList() throws Exception {
        String invalidData = "1,username1,password1,name1,25\n"; // 缺少updateTime字段
        when(inputStream.readAllBytes()).thenReturn(invalidData.getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    List<user> users = userController.list();
                    assertEquals(0, users.size());
                });
    }
}
