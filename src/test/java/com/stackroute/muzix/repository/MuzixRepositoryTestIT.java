package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Muzix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
public class MuzixRepositoryTestIT {

    @Autowired
    private MuzixRepository muzixRepository;
    private Muzix muzix;
    private Muzix muzix1;

    @Before
    public void setUp()
    {
        muzix = new Muzix();
        muzix.setTrackId(11);
        muzix.setTrackName("Photograph");
        muzix.setComments("Ed Sheeran");


    }

    @After
    public void tearDown(){

        muzixRepository.deleteAll();
    }



    @Test
    public void testSaveMuzix(){
        muzixRepository.save(muzix);
        Muzix fetchMuzix = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertEquals(11,fetchMuzix.getTrackId());

    }

    @Test
    public void testSaveMuzixFailure(){
        Muzix testUser = new Muzix(11,"Diamonds","Rihanna");
        muzixRepository.save(muzix);
        Muzix fetchMuzix = muzixRepository.findById(muzix.getTrackId()).get();
        Assert.assertNotSame(testUser,muzix);
    }
    @Test
    public void testUpdateMuzix(){
        muzixRepository.save(muzix1);
        Muzix fetchMuzix = muzixRepository.findById(muzix1.getTrackId()).get();
        Assert.assertEquals(12,fetchMuzix.getTrackId());

    }

    @Test
    public void testUpdateMuzixFailure(){
        Muzix testUser = new Muzix(12,"Diamonds","Rihanna");
        muzixRepository.save(muzix1);
        Muzix fetchMuzix = muzixRepository.findById(muzix1.getTrackId()).get();
        Assert.assertNotSame(testUser,muzix1);
    }
    @Test
    public void testGetAllUser(){
        Muzix u = new Muzix(12,"In the name of love","Bebe Rexha");
        Muzix u1 = new Muzix(14,"Scared to be Lonely","Dua Lipa");
        muzixRepository.save(u);
        muzixRepository.save(u1);

        List<Muzix> list = muzixRepository.findAll();
        Assert.assertEquals(12,list.get(0).getTrackId());

    }
    @Test
    public void testDeleteMuzix(){
        muzixRepository.deleteById(6);
        List<Muzix> muzix =muzixRepository.findAll();
        System.out.println(muzix.get(0).getTrackId());
        Assert.assertEquals(5,muzix.get(0).getTrackId());

    }

    @Test
    public void testDeleteMuzixFailure(){
        muzixRepository.deleteById(12);
        Assert.assertNotSame(null,muzix.getTrackId());
    }



}
