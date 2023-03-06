package com.gani.analytics.controller;

import com.gani.analytics.IdentifyRepository;
import com.gani.analytics.model.Identify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class IdentifyController {

    @Autowired
    IdentifyRepository repo;

    @RequestMapping(value="/")
    public String hello() {
        return "Testing";
    }

    Comparator<Identify> com = new Comparator<Identify>() {
        @Override
        public int compare(Identify o1, Identify o2) {
            if (o1.getDate().compareTo(o2.getDate()) < 0)
                return 1;
            return -1;
        }
    };

    @GetMapping(value="/getUsersCount")
    public List<Identify> getCount_Identify(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").parse(endDate + " " + "23:59:59:999");
        Date from = sdFormat.parse(startDate);
        List<Identify> li = repo.findAll().stream().filter(e -> (e.getDate().compareTo(from) >= 0 && e.getDate().compareTo(to) <=0)).toList();
        List<Identify> temp = new ArrayList<>();
        temp.addAll(li);
        Collections.sort(temp, com);
        return temp;
    }

    @GetMapping("/users")
    public List<Identify> getAll() {
        return repo.findAll();
    }

    @GetMapping("/users/count")
    public int getAllCount() {
        return repo.findAll().size();
    }

    @GetMapping("/users/time")
    public List<Identify> getToday() throws ParseException {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S").parse("2023-03-25" + " " + "23:59:59:999");
        Date from = sdFormat.parse("2022-02-20");
        Identify k = repo.findAll().get(0);

        List<Identify> li = repo.findAll().stream().filter(e -> (e.getDate().compareTo(from) >= 0 && e.getDate().compareTo(to) <=0)).toList();
        return li;
    }

    @GetMapping("/users/delete")
    public void deleteAll() {
        repo.deleteAll();
    }

    @GetMapping("/users/insert")
    public void insert(@RequestParam String id) {
        List<Identify> li = repo.findAll().stream().filter(e -> e.getUserId().equals(id)).limit(1).toList();
        if (li.size() > 0) {
            return;
        }
        Identify p = new Identify();
        p.setUserId(id);
        p.setDate(new Date());
        repo.save(p);
    }

}
