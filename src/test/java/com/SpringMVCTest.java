package com;


import com.web.controller.MainController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration("WebConfig")
public class SpringMVCTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = webAppContextSetup(ctx).build();
    }



    @Configuration
    public static class TestConfig{
        @Bean
        public MainController loginController(){
            return new MainController();
        }
    }


    //Test Cases
    @Test
    public void welcome() throws Exception{
        mockMvc.perform(get("/welcome.jsp").accept(MediaType.TEXT_PLAIN))
                .andDo(print());
    }
    @Test
    public void testLogin() throws Exception{
        mockMvc.perform(get("/login.jsp").accept(MediaType.TEXT_PLAIN))
                .andDo(print());
    }
    @Test
    public void testAdmin() throws Exception{
        mockMvc.perform(get("/admin.jsp").accept(MediaType.TEXT_PLAIN))
                .andDo(print());
    }

    @Test
    public void testHello() throws Exception{
        mockMvc.perform(get("/hello.jsp").accept(MediaType.TEXT_PLAIN))
                .andDo(print());
    }
    @Test
    public void testTranslate() throws Exception{
        mockMvc.perform(get("/translator.jsp").accept(MediaType.TEXT_PLAIN))
                .andDo(print());
    }

}
