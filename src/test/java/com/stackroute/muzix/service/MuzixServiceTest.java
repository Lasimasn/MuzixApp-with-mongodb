package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.MuzixRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MuzixServiceTest {
    private Muzix muzix;

    //Create a mock for UserRepository
    @Mock
    private MuzixRepository muzixRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private MuzixServiceImpl muzixService;
    List<Muzix> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        muzix = new Muzix();
        muzix.setTrackId(13);
        muzix.setTrackName("Dil se re");
        muzix.setComments("AR Rahman");
        list = new ArrayList<>();
        list.add(muzix);
        muzix.setTrackId(12);
        muzix.setTrackName("Red");
        muzix.setComments("Taylor");
        list.add(muzix);


    }

    @Test
    public void saveMuzixTestSuccess() throws TrackAlreadyExistException {

        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        Muzix savedMuzix = muzixService.addMuzix(muzix);
        Assert.assertEquals(muzix,savedMuzix);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository,times(1)).save(muzix);

    }

    @Test(expected = TrackAlreadyExistException.class)
    public void saveMuzixTestFailure() throws TrackAlreadyExistException {
        when(muzixRepository.existsById(muzix.getTrackId())).thenReturn(true);
        when(muzixRepository.save(muzix)).thenReturn(null);
        Muzix savedMuzix = muzixService.addMuzix(muzix);
        System.out.println("savedMuzix" + savedMuzix);
        Assert.assertEquals(muzix,savedMuzix);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }
    @Test
    public void updateMuzixTestSuccess() throws TrackNotFoundException {

        when(muzixRepository.save((Muzix) any())).thenReturn(muzix);
        Muzix savedMuzix = muzixService.updateList(muzix);
        Assert.assertEquals(muzix,savedMuzix);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository,times(1)).save(muzix);

    }

    @Test(expected = TrackNotFoundException.class)
    public void updateMuzixTestFailure() throws TrackNotFoundException {
        when(muzixRepository.save((Muzix)any())).thenReturn(null);
        Muzix savedMuzix = muzixService.updateList(muzix);
        System.out.println("savedMuzix" + savedMuzix);
        Assert.assertEquals(muzix,savedMuzix);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    @Test
    public void getAllUser(){

        muzixRepository.save(muzix);
        //stubbing the mock to return specific data
        when(muzixRepository.findAll()).thenReturn(list);
        List<Muzix> muzixlist = muzixService.displayAll();
        Assert.assertEquals(list,muzixlist);
    }

    @Test
    public void removeMuzixTestSuccess() throws TrackNotFoundException {
        when(muzixRepository.existsById(muzix.getTrackId())).thenReturn(true);
        muzixRepository.deleteById(muzix.getTrackId());
        when(muzixRepository.findAll()).thenReturn(list);
        List <Muzix> removeMuzix = muzixService.remove(muzix.getTrackId());
        System.out.println(muzix);
        Assert.assertEquals(list,removeMuzix);

        //verify here verifies that userRepository save method is only called once
        verify(muzixRepository,times(2)).deleteById(muzix.getTrackId());

    }

    @Test(expected = TrackNotFoundException.class)
    public void removeMuzixTestFailure() throws TrackNotFoundException {
        muzixRepository.deleteById(muzix.getTrackId());
        when(muzixRepository.findAll()).thenReturn(null);
        List <Muzix> removeMuzix = muzixService.remove(muzix.getTrackId());
        System.out.println("savedMuzix" + removeMuzix);
        System.out.println(muzix);
        Assert.assertEquals(list,removeMuzix);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }




}
