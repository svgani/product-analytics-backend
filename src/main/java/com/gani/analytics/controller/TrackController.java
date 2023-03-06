package com.gani.analytics.controller;

import com.gani.analytics.TrackRepository;
import com.gani.analytics.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class TrackController {

    @Autowired
    TrackRepository repo;

    @RequestMapping(value="/track")
    public String hello() {
        return "Testing";
    }

    @GetMapping("/events")
    public List<Track> getAll() {
        System.out.println(repo.findAll());
        return repo.findAll();
    }

    @GetMapping("/events/count")
    public int getAllCount() {
        System.out.println(repo.findAll());
        return repo.findAll().size();
    }

    @GetMapping("/events/delete")
    public void deleteAll() {
        System.out.println(repo.findAll());
        repo.deleteAll();
    }

    @GetMapping("/events/insert")
    public void insert() throws ParseException {
        Track p = new Track();
        p.setLink("view-portal");
//        p.setDate(getCurrentUtcTime());
        p.setDate(new Date());
        p.setUserId("testUser");
        repo.save(p);
        System.out.println("Inserting");
    }

    Comparator<Track> com = new Comparator<Track>() {
        @Override
        public int compare(Track o1, Track o2) {
            if (o1.getDate().compareTo(o2.getDate()) < 0)
                return 1;
            return -1;
        }
    };

    @GetMapping(value="/getEventsCount")
    public List<Track> getCount_Identify(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").parse(endDate + " " + "23:59:59:999");
        Date from = sdFormat.parse(startDate);
        System.out.println(to);
        System.out.println(from);
        List<Track> li = repo.findAll().stream().filter(e -> (e.getDate().compareTo(from) >= 0 && e.getDate().compareTo(to) <= 0)).toList();
        li.stream().forEach(t -> System.out.println(t));
        List<Track> temp = new ArrayList<>();
        temp.addAll(li);
        Collections.sort(temp, com);
        return temp;
    }

//    private Date getCurrentUtcTime() throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//        return localDateFormat.parse( simpleDateFormat.format(new Date()) );
//    }

}
