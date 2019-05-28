package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.MuzixService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="api/v1")
@Api(value="Music Management System")
public class MuzixController {
    @Autowired
    MuzixService muzixService;
    @Autowired
    public MuzixService getMuzixService()
    {
        return muzixService;
    }
    public MuzixController(MuzixService muzixService)
    {
        this.muzixService=muzixService;
    }

    //add tracks to the app
    @PostMapping("music")
    public ResponseEntity<?> addMuzix(@RequestBody Muzix muzix) throws TrackAlreadyExistException
    {
        ResponseEntity responseEntity;
        try {
            muzixService.addMuzix(muzix);
            responseEntity = new ResponseEntity(muzix, HttpStatus.CREATED);
        } catch (TrackAlreadyExistException ex)
        {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
            //throw ex;
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //display all track in the app
    //@ApiOperation(value="View a list of music tracks",response=Muzix.class)
    @GetMapping("music")
    public ResponseEntity<?> displayAll() throws TrackNotFoundException
    {   ResponseEntity responseEntity=null;
        try {
            return new ResponseEntity<List<Muzix>>(muzixService.displayAll(), HttpStatus.OK);
        }
        catch(TrackNotFoundException e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);

        }
        return responseEntity;
    }
    //update an existing track
    @PutMapping("music")
    public ResponseEntity updateList( @RequestBody Muzix muzix)throws TrackNotFoundException
    {
        try {
            return new ResponseEntity(muzixService.updateList(muzix), HttpStatus.OK);
        }
        catch (TrackNotFoundException ex)
        {
            return new ResponseEntity(ex.getMessage(),HttpStatus.ALREADY_REPORTED);
            //throw ex;
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.ALREADY_REPORTED);
        }
    }
    //Delete a track by Id
    @DeleteMapping ("music/{trackId}")
    public ResponseEntity remove(@PathVariable int trackId)throws TrackNotFoundException
    {
        ResponseEntity responseEntity=null;
        try {
            return new ResponseEntity(muzixService.remove(trackId), HttpStatus.OK);
        }
        catch(TrackNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
            //throw e;
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }
    //find a track by Name
    @GetMapping("music/{trackName}")
    public ResponseEntity searchByName(@PathVariable  String trackName)throws  TrackNotFoundException
    {   ResponseEntity responseEntity=null;
        try {
            return new ResponseEntity (muzixService.searchByName(trackName), HttpStatus.OK);
        }
        catch (TrackNotFoundException e)
        {
            return new ResponseEntity (e.getMessage(),HttpStatus.CONFLICT);
            //throw e;
        }
        catch (Exception e)
        {
            //e.getMessage();
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

}
