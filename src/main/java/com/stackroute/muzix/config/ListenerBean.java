package com.stackroute.muzix.config;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.service.MuzixServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")

public class ListenerBean implements ApplicationListener<ContextRefreshedEvent>
{
        private MuzixServiceImpl muzixService;
        private  Muzix muzix;
        @Autowired
        public ListenerBean(MuzixServiceImpl muzixService, Muzix muzix) {
            this.muzixService = muzixService;
            this.muzix =muzix;
        }

        public ListenerBean() {
        }
        @Autowired
        private Environment env;
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("Context refreshed event fired:");
            System.out.println(event);
            //Muzix muzix=new Muzix(1,"Jai ho","Hindi");
            //Muzix muzix1=new Muzix(2,"Harry","English");

                muzix.setTrackId(Integer.parseInt(env.getProperty("trackId1")));
                muzix.setTrackName(env.getProperty("trackName1"));
                muzix.setComments(env.getProperty("comments1"));
                muzixService.seedData(muzix);
                muzix.setTrackId(Integer.parseInt(env.getProperty("trackId2")));
                muzix.setTrackName(env.getProperty("trackName2"));
                muzix.setComments(env.getProperty("comments2"));
                muzixService.seedData(muzix);
                muzix.setTrackId(Integer.parseInt(env.getProperty("trackId3")));
                muzix.setTrackName(env.getProperty("trackName3"));
                muzix.setComments(env.getProperty("comments3"));
                muzixService.seedData(muzix);

            }
        }


