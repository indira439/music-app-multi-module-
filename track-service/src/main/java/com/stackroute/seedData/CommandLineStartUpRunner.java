//package com.stackroute.seedData;
//
//import com.stackroute.domain.Track;
//import com.stackroute.exceptions.TrackAlreadyExistsException;
//import com.stackroute.repository.TrackRepository;
//import com.stackroute.service.TrackService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//
//@Component
///**Command Line Runner is used to execute the code after the Spring Boot application started*/
//public class CommandLineStartUpRunner implements CommandLineRunner {
//    private TrackService trackService;
//
//    @Autowired
//    private CommandLineStartUpRunner(TrackService trackService) {
//        this.trackService = trackService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        /**Pre-fill the database whenever application starts*/
//        try {
//            Track track10 = new Track(10, "track10", "comment10");
//            trackService.saveTrack(track10);
//        } catch (TrackAlreadyExistsException exception) {
//            exception.printStackTrace();
//        }
//    }
//}
