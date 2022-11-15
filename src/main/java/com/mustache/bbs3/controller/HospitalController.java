package com.mustache.bbs3.controller;

import com.mustache.bbs3.domain.entity.Comment;
import com.mustache.bbs3.domain.entity.Hospital;
import com.mustache.bbs3.repository.CommentRepository;
import com.mustache.bbs3.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {

    private final HospitalRepository hospitalRepository;
    private final CommentRepository commentRepository;

    public HospitalController(HospitalRepository hospitalRepository, CommentRepository commentRepository) {
        this.hospitalRepository = hospitalRepository;
        this.commentRepository = commentRepository;
    }


    @GetMapping("{id}")
    public String list(@PathVariable Integer id, Model model) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        List<Comment> comments = commentRepository.findByArticleId(id);
        model.addAttribute("hospital", hospital.get());
        model.addAttribute("comments", comments);
        return "hospital/show";

    }



    @GetMapping("")
    public String list(Model model, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        log.info("size:{}", hospitals.getSize());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/list";
    }
}
