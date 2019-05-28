package com.stackroute.muzix.config;
import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.service.MuzixServiceImpl;
import com.stackroute.muzix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerApp implements CommandLineRunner {

    private final Logger logger= LoggerFactory.getLogger(CommandLineRunnerApp.class);

    private MuzixServiceImpl muzixService;
    private Muzix muzix;

    public CommandLineRunnerApp(MuzixServiceImpl muzixService, Muzix muzix) {
        this.muzixService = muzixService;
        this.muzix = muzix;
    }
    @Value("${music.id}")
    int id;
    @Value("${music.name}")
    String name;
    @Value("${music.comments}")
    String comments;


    @Override
    public void run(String... args) throws Exception {

        logger.info("Loading data..");
        muzix.setTrackId(id);
        muzix.setTrackName(name);
        muzix.setComments(comments);
        muzixService.seedData(muzix);
    }
}
