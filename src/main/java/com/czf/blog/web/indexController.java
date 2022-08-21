package com.czf.blog.web;

import com.czf.blog.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
        //int i =9/0;
       // String blog = null;
        //if (blog == null){
         //   throw new NotFoundException("Blog not found");
        //}

        System.out.println("==============index==================");

        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        //int i =9/0;
        // String blog = null;
        //if (blog == null){
        //   throw new NotFoundException("Blog not found");
        //}

        return "blog";
    }
}
