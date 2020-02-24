package com.ZooManagerApp.controller;

import com.ZooManagerApp.service.HomePageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class HomePageControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HomePageService homePageService;
    @InjectMocks
    private HomePageController homePageController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(homePageController)
                .build();
    }

    @Test
    public void testGetHomePage() throws Exception {
        when(homePageService.constructLoginAddress()).thenReturn("http://localhost:8081/login");

        mockMvc.perform(
                get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome in ZooManagerApp! Please, log in to the service. \n"
                        + "http://localhost:8081/login"));

        verify(homePageService).constructLoginAddress();
    }
}













