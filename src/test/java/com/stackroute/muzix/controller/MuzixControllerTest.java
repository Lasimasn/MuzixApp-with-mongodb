package com.stackroute.muzix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.MuzixService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

    @RunWith(SpringRunner.class)
    @WebMvcTest
    public class MuzixControllerTest {

        @Autowired
        private MockMvc mockMvc;
        private Muzix muzix;
        @MockBean
        private MuzixService muzixService;
        @InjectMocks
        private MuzixController muzixController;

        private List<Muzix> list =null;

        @Before
        public void setUp(){

            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
            muzix = new Muzix();
            muzix.setTrackId(10);
            muzix.setTrackName("Red");
            muzix.setComments("Taylor Swift");
            list = new ArrayList();
            list.add(muzix);
        }

        @Test
        public void saveMuzix() throws Exception {
            when(muzixService.addMuzix(any())).thenReturn(muzix);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/add")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void saveMuzixFailure() throws Exception {
            when(muzixService.addMuzix(any())).thenThrow(TrackAlreadyExistException.class);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/add")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void updateMuzix() throws Exception {
            when(muzixService.updateList(any())).thenReturn(muzix);
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/update")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void updateMuzixFailure() throws Exception {
            when(muzixService.updateList(any())).thenThrow(TrackNotFoundException.class);
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/update")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void deleteMuzix() throws Exception {
            List <Muzix> muzixList=muzixService.remove(muzix.getTrackId());
            when(muzixService.remove(muzix.getTrackId())).thenReturn((muzixList));

            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/remove/{trackId}",11)
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void deleteMuzixFailure() throws Exception {
            when(muzixService.remove(anyInt())).thenThrow(TrackNotFoundException.class);
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/remove/{trackId}",11)
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andDo(MockMvcResultHandlers.print());
        }

        @Test
        public void searchMuzix() throws Exception {
            when(muzixService.searchByName(anyString())).thenReturn(muzix);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/search/{trackName}","Diamonds")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void searchMuzixFailure() throws Exception {
            when(muzixService.searchByName(anyString())).thenThrow(TrackNotFoundException.class);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/search/{trackName}","Diamonds")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andDo(MockMvcResultHandlers.print());
        }
        @Test
        public void getAllMuzix() throws Exception {
            when(muzixService.displayAll()).thenReturn(list);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/display")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());

        }

        private static String asJsonString(final Object obj)
        {
            try{
                return new ObjectMapper().writeValueAsString(obj);

            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }


    }




