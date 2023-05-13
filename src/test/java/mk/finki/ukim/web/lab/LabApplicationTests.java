package mk.finki.ukim.web.lab;

import mk.finki.ukim.web.lab.model.Manufacturer;
import mk.finki.ukim.web.lab.model.enumerations.Role;
import mk.finki.ukim.web.lab.service.BalloonService;
import mk.finki.ukim.web.lab.service.ManufacturerService;
import mk.finki.ukim.web.lab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {

    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;
    private static Manufacturer m1;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }
    private void initData() {
        if (!dataInitialized) {

             m1 = manufacturerService.save("m1","m1","m1");
            manufacturerService.save("m2", "m2", "m2");

            String user = "user";
            String admin = "admin";

            userService.register(user, user, user, user, user, LocalDate.now(), Role.ROLE_USER);
            userService.register(admin, admin, admin, admin, admin,LocalDate.now(), Role.ROLE_ADMIN);
            dataInitialized = true;
        }

    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetBalloons() throws Exception {
        MockHttpServletRequestBuilder balloonRequest = MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(balloonRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
//                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent","listBalloons"))
//                .andExpect(MockMvcResultMatchers.view().name("master-template"));
                .andExpect(MockMvcResultMatchers.view().name("listBalloons"));
    }
}
