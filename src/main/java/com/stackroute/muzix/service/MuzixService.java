package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;

import java.util.List;


public interface MuzixService {

    public Muzix addMuzix(Muzix muzix)throws TrackAlreadyExistException;

    public List<Muzix> displayAll() throws TrackNotFoundException;
     public Muzix updateList(Muzix muzix) throws TrackNotFoundException;
    public List<Muzix> remove(int trackId)throws  TrackNotFoundException;
   public Muzix searchByName(String trackName) throws  TrackNotFoundException;
   public void seedData(Muzix muzix);

}
