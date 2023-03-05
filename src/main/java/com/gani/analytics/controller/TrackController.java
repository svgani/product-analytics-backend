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

//    @PostMapping(value="/getCount")
//    public int getCount_Identify(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String action) throws ParseException {
//        System.out.println("Test");
//        System.out.println(action);
//
//        if (action.equals("IdentifyEvents")) {
//            System.out.println("Testing");
//            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").parse(endDate + " " + "23:59:59:999");
//            Date from = sdFormat.parse(startDate);
//            System.out.println(to);
//            System.out.println(from);
//            List<Identify> li = repo.findAll().stream().filter(e -> (e.getDate().compareTo(from) >= 0 && e.getDate().compareTo(to) <=0)).toList();
//            System.out.println(li);
//            return li.size();
//        }
//        return 0;
//    }
//
//    @GetMapping("/posts")
//    public List<Identify> getAll() {
//        System.out.println(repo.findAll());
//        return repo.findAll();
//    }
//
//    @GetMapping("/posts/count")
//    public int getAllCount() {
//        System.out.println(repo.findAll());
//        return repo.findAll().size();
//    }
//
//    @GetMapping("/posts/today")
//    public List<Identify> getToday() throws ParseException {
//        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").parse("2023-03-05" + " " + "23:59:59:999");
//        Date from = sdFormat.parse("2022-02-20");
//        System.out.println(to);
//        System.out.println(from);
//        Identify k = repo.findAll().get(0);
//        System.out.println(k.toString());
//        System.out.println(k.getDate());
//        System.out.println(k.getDate().compareTo(from));
//        System.out.println(k.getDate().compareTo(to));
//
//        if (k.getDate().compareTo(from) >= 0 && k.getDate().compareTo(to) <=0) {
//            System.out.println("truee...");
//        }
////        repo.findAll().stream().forEach((p) -> System.out.println(p.toString()));
////        System.out.println(p.toString());
//        List<Identify> li = repo.findAll().stream().filter(e -> (e.getDate().compareTo(from) >= 0 && e.getDate().compareTo(to) <=0)).toList();
//        System.out.println(li);
//        return li;
//    }
//
//    @GetMapping("/delete")
//    public void deleteAll() {
//        System.out.println(repo.findAll());
//        repo.deleteAll();
//    }

    @GetMapping("/track/insert")
    public void insert() throws ParseException {
        Track p = new Track();
        p.setLink("hello");
//        p.setDate(getCurrentUtcTime());
        p.setDate(new Date());
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

    @GetMapping(value="/getTrackCount")
    public List<Track> getCount_Identify(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").parse(endDate + " " + "23:59:59:999");
//        Date to = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        Date from = sdFormat.parse(startDate);
        System.out.println(to);
        System.out.println(from);
        List<Track> li = repo.findAll().stream().filter(e -> (e.getDate().compareTo(from) >= 0 && e.getDate().compareTo(to) <= 0)).toList();
//        System.out.println(li);
        li.stream().forEach(t -> System.out.println(t));
        List<Track> temp = new ArrayList<>();
        temp.addAll(li);
        Collections.sort(temp, com);
//        System.out.println(li);
//        System.out.println(temp);
        return temp;
    }

//    private Date getCurrentUtcTime() throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//        return localDateFormat.parse( simpleDateFormat.format(new Date()) );
//    }

}
