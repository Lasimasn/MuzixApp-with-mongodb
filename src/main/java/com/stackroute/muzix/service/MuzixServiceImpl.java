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

    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository)
    {
        this.muzixRepository=muzixRepository;
    }
    public MuzixServiceImpl() {}
    @Override
    public Muzix addMuzix(Muzix muzix)throws TrackAlreadyExistException
    {
        if(muzixRepository.existsById(muzix.getTrackId())) {
            throw new TrackAlreadyExistException("Track Already Exists");
        } else
            return muzixRepository.save(muzix);
    }

    @Override
    public List<Muzix> displayAll()
    {
        return muzixRepository.findAll();
    }

    @Override
    public Muzix updateList(Muzix muzix) throws TrackNotFoundException {

        Muzix muzix1=muzixRepository.save(muzix);
        if(muzix1==null)
        {
            throw new TrackNotFoundException("Track Not found");
        }
        return muzix1;
    }

    @Override
    public Muzix searchByName(String trackName) throws TrackNotFoundException{
        //if(muzixRepository.exists(trackName))
        muzixRepository.searchByName(trackName);
        if(trackName==null)
        {
            throw new TrackNotFoundException("Track not found");
        }
        return (Muzix) muzixRepository;
        //return muzixList;
    }

    @Override
    public void seedData(Muzix muzix) {
        if(!muzixRepository.existsById(muzix.getTrackId()))
            muzixRepository.save(muzix);
    }

    @Override
    public List<Muzix> remove(int trackId) throws TrackNotFoundException{
        if(muzixRepository.existsById(trackId)) {

            muzixRepository.deleteById(trackId);
            //muzixRepository.findAll();
        }
        else
        {
            throw  new TrackNotFoundException("Track not found");
        }
        return muzixRepository.findAll();

    }







}
