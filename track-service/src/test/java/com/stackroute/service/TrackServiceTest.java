//package com.stackroute.service;
//
//
//import com.stackroute.domain.Track;
//import com.stackroute.exceptions.TrackAlreadyExistsException;
//import com.stackroute.exceptions.TrackNotFoundException;
//import com.stackroute.repository.TrackRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class TrackServiceTest {
//
//    private Track track;
//
//    /**
//     * Create a mock for TrackRepository
//     */
//    @Mock
//    private TrackRepository trackRepository;
//
//    /**
//     * Inject the mocks as dependencies into TrackServiceImplements
//     */
//    @InjectMocks
//    TrackServiceImplements trackServiceImplements;
//
//    /**Run this before each test case*/
//    @Before
//    public void setUp() {
//        //Initialising the mock object
//        MockitoAnnotations.initMocks(this);
//        track = new Track();
//        track.setId(1);
//        track.setTrackName("Music track1");
//        track.setComments("Music comment1");
//
//    }
//
//    @Test
//    public void givenTrackShouldSaveTrack() throws TrackAlreadyExistsException {
//        //act
//        when(trackRepository.save((Track) any())).thenReturn(track);
//        Track savedTrack = trackServiceImplements.saveTrack(track);
//        //assert
//        Assert.assertEquals(track, savedTrack);
//
//        //verify here verifies that trackRepository save method is only called once
//        verify(trackRepository, times(1)).save(track);
//    }
//
//    @Test(expected = TrackAlreadyExistsException.class)
//    public void givenDuplicateTrackShouldReturnTrackAlreadyExistsException() throws TrackAlreadyExistsException {
//        //act
//        when(trackRepository.existsById((track.getId()))).thenReturn(true);
//        trackServiceImplements.saveTrack(track);
//        trackServiceImplements.saveTrack(track);
//    }
//
//    @Test
//    public void givenTrackIdShouldReturnTrackWithThatId() throws TrackNotFoundException {
//        //act
//        when(trackRepository.existsById(track.getId())).thenReturn(true);
//        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
//        Track retrieveTrack = trackServiceImplements.getTrackById(track.getId());
//        //assert
//        Assert.assertEquals(track, retrieveTrack);
//
//        //verify here verifies that trackRepository findById method is only called once
//        verify(trackRepository, times(1)).findById(track.getId());
//
//    }
//
//    @Test(expected = TrackNotFoundException.class)
//    public void givenInvalidIdShouldReturnTrackNotFoundException() throws TrackNotFoundException {
//        trackServiceImplements.getTrackById(100);
//    }
//
//    @Test
//    public void givenTracksShouldReturnAllTracks() {
//        //act
//        List<Track> expectedList = new ArrayList<>();
//        expectedList.add(track);
//        //stubbing the mock to return specific data
//        when(trackRepository.findAll()).thenReturn(expectedList);
//        List<Track> trackList = trackServiceImplements.getAllTracks();
//        //assert
//        Assert.assertEquals(expectedList, trackList);
//
//        //verify here verifies that trackRepository findById method is only called once
//        verify(trackRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void givenTrackIdShouldDeleteThatTrackANdReturnRemainingTracks() throws TrackNotFoundException {
//        //act
//        when(trackRepository.existsById(track.getId())).thenReturn(true);
//        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
//        trackServiceImplements.deleteTrackById(track.getId());
//        List<Track> expectedResult = new ArrayList<>();
//        //assert
//        Assert.assertEquals(expectedResult, trackServiceImplements.getAllTracks());
//    }
//
//    @Test
//    public void givenTrackShouldReturnUpdatedTrack() throws TrackNotFoundException {
//        //act
//        when(trackRepository.existsById(track.getId())).thenReturn(true);
//        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
//        track.setComments("Updated comment");
//        Track track1 = new Track(1, "Music track1", "Updated comment");
//        //assert
//        Assert.assertEquals(track1, track);
//    }
//
//    @Test
//    public void givenTrackNameShouldReturnListOfTracksWithThatTrackName() throws TrackNotFoundException {
//        //act
//        List<Track> trackList = new ArrayList<>();
//        trackList.add(track);
//        when(trackRepository.findBytrackName(track.getTrackName())).thenReturn(trackList);
//        List<Track> actualTrackList = trackServiceImplements.getTrackByName(track.getTrackName());
//        //assert
//        Assert.assertEquals(trackList, actualTrackList);
//    }
//
//    @Test(expected = TrackNotFoundException.class)
//    public void givenTrackNameShouldReturnTrackNotFoundException() throws TrackNotFoundException {
//        trackServiceImplements.getTrackByName("aaaaaaaaaaaaaaa");
//    }
//
//}
