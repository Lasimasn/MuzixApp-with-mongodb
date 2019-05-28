package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Muzix;
import com.stackroute.muzix.exception.TrackAlreadyExistException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.MuzixService;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="api/v1")
@Api(value="Music Management System")
public class MuzixController {
    @Autowired
    MuzixService muzixService;

    public MuzixService getMuzixService()
    {
        return muzixService;
    }
    public MuzixController(MuzixService muzixService)
    {
        this.muzixService=muzixService;
    }


    @PostMapping("user/add")
    public ResponseEntity<?> addMuzix(@RequestBody Muzix muzix) throws TrackAlreadyExistException
    {
        ResponseEntity responseEntity;
        try {
            muzixService.addMuzix(muzix);
            responseEntity = new ResponseEntity("Success", HttpStatus.CREATED);
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
    //@ApiOperation(value="View a list of music tracks",response=Muzix.class)
    @GetMapping("user/display")
    public ResponseEntity<?> displayAll() throws TrackNotFoundException
    {   ResponseEntity responseEntity=null;
        try {
            return new ResponseEntity<List<Muzix>>(muzixService.displayAll(), HttpStatus.OK);
        }
        catch(TrackNotFoundException e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (Exception e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity("Something went Wrong!!",HttpStatus.CONFLICT);

        }
        return responseEntity;
    }
    @PutMapping("user/update")
    public ResponseEntity updateList( @RequestBody Muzix muzix)throws TrackNotFoundException
    {
        try {
            return new ResponseEntity(muzixService.updateList(muzix), HttpStatus.CREATED);
        }
        catch (TrackNotFoundException ex)
        {
            return new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
            //throw ex;
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping ("user/remove/{trackId}")
    public ResponseEntity remove(@PathVariable int trackId)throws TrackNotFoundException
    {
        ResponseEntity responseEntity=null;
        try {
            return new ResponseEntity(muzixService.remove(trackId), HttpStatus.CREATED);
        }
        catch(TrackNotFoundException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
            //throw e;
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @GetMapping("user/search/{trackName}")
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
