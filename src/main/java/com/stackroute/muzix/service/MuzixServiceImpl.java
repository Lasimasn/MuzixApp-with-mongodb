package com.stackroute.muzix.service;


import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuzixServiceImpl implements MuzixService {
    @Autowired
    MuzixRepository muzixRepository;
    public MuzixServiceImpl(MuzixRepository muzixRepository)
    {
        this.muzixRepository=muzixRepository;
    }
    public MuzixServiceImpl() {}
    @Override
    //add tracks to the app
    public Muzix addMuzix(Muzix muzix)throws TrackAlreadyExistException
    {
        if(muzixRepository.existsById(muzix.getTrackId())) {
            throw new TrackAlreadyExistException("Track Already Exists");
        } else
            return muzixRepository.save(muzix);
    }
    //display all track in the app
    @Override
    public List<Muzix> displayAll() throws TrackNotFoundException
    {
        if(muzixRepository==null)
        {
            throw new TrackNotFoundException("No tracks to display");
        }
        return muzixRepository.findAll();
    }
    //update an existing track
    @Override
    public Muzix updateList(Muzix muzix) throws TrackNotFoundException {

        Muzix muzix1=muzixRepository.save(muzix);
        if(muzix1==null)
        {
            throw new TrackNotFoundException("Track Not found");
        }
        return muzix1;
    }
    //find a track by Name
    @Override
    public Muzix searchByName(String trackName) throws TrackNotFoundException{
        muzixRepository.searchByName(trackName);
        if(trackName==null)
        {
            throw new TrackNotFoundException("Track not found");
        }
        return (Muzix) muzixRepository;
    }
    //Delete a track by Id
    @Override
    public List<Muzix> remove(int trackId) throws TrackNotFoundException{
        if(muzixRepository.existsById(trackId)) {

            muzixRepository.deleteById(trackId);
        }
        else
        {
            throw  new TrackNotFoundException("Track not found");
        }
        return muzixRepository.findAll();

    }
    //Add tracks
    @Override
    public void seedData(Muzix muzix) {
        if(!muzixRepository.existsById(muzix.getTrackId()))
            muzixRepository.save(muzix);
    }

}
