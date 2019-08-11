//package com.stackroute.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.domain.Track;
//import com.stackroute.exceptions.TrackAlreadyExistsException;
//import com.stackroute.exceptions.TrackNotFoundException;
//import com.stackroute.service.TrackService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
///**
// * @RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit
// */
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class TrackControllerTest {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//    private Track track;
//    /**
//     * Create a mock for TrackService
//     */
//    @MockBean
//    private TrackService trackService;
//    /**
//     * Inject the mocks as dependencies into TrackController
//     */
//    @InjectMocks
//    private TrackController trackController;
//
//    /**
//     * Run this before each test case
//     */
//    private List<Track> list = null;
//
//    @Before
//    public void setUp() {
//        //Initialising the mock object
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
//        //act
//        track = new Track();
//        track.setId(1);
//        track.setTrackName("Music track1");
//        track.setComments("Music comment1");
//        list = new ArrayList<>();
//        list.add(track);
//    }
//
//    @Test
//    public void givenUrlShouldReturnTheSavedTrack() throws Exception {
//        when(trackService.saveTrack(any())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void givenUrlAndTrackShouldThrowTrackAlreadyException() throws TrackAlreadyExistsException, Exception {
//        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenUrlAndTrackShouldReturnInternalServerError() throws TrackAlreadyExistsException, Exception {
//        when(trackService.saveTrack(any())).thenThrow(Exception.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenUrlShouldReturnListOfAllTracks() throws Exception {
//        when(trackService.getAllTracks()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void givenUrlShouldReturnException() throws Exception {
//        when(trackService.getAllTracks()).thenThrow(Exception.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void givenUrlWithIdShouldReturnTrackOfThatId() throws TrackNotFoundException, Exception {
//        when(trackService.getTrackById(anyInt())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenUrlAndIdShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
//        when(trackService.getTrackById(anyInt())).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/100")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenUrlWithIdShouldReturnInternalServerError() throws TrackNotFoundException, Exception {
//        when(trackService.getTrackById(anyInt())).thenThrow(Exception.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/200")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenUrlWithTrackNameShouldReturnTrack() throws TrackNotFoundException, Exception {
//        when(trackService.getTrackByName(any())).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/Music")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenUrlWithNameShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
//        when(trackService.getTrackByName(any())).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/yedho onnu solluvom")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void givenUrlWithTrackNameShouldReturnServerException() throws TrackNotFoundException, Exception {
//        when(trackService.getTrackByName(any())).thenThrow(Exception.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/zzzzz")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//
//    @Test
//    public void givenUrlAndTrackShouldReturnUpdatedTrack() throws TrackNotFoundException, Exception {
//        when(trackService.updateTrackById(any(), any())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void givenUrlAndTrackShouldReturnTrackNotFoundException() throws TrackNotFoundException, Exception {
//        when(trackService.updateTrackById(any(), any())).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/200")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//
//    @Test
//    public void givenUrlAndTrackShouldReturnTheServerException() throws TrackNotFoundException, Exception {
//        when(trackService.updateTrackById(any(), any())).thenThrow(Exception.class);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    private static String asJsonString(final Object object) {
//        try {
//            return new ObjectMapper().writeValueAsString(object);
//
//        } catch (Exception exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//
//}
