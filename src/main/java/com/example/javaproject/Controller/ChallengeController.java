package com.example.javaproject.Controller;

import com.example.javaproject.Service.ChallengeService;
import com.example.javaproject.Table.Challenge;
import com.example.javaproject.DTO.VoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "https://jjol.netlify.app")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    @PostMapping("/challenge/create")
    @ResponseBody
    public void createchallenge(@RequestBody Map<String, Object> requestData ) {
        challengeService.createChallenge(requestData);
    }

    @PostMapping("/vote")
    @ResponseBody
    public void vote(@RequestBody VoteRequest voteRequest) {
        Integer id = voteRequest.getId();
        boolean vote = voteRequest.isVote();
        challengeService.editChallenge(id, vote);
    }

    @GetMapping("/challenges/{id}")
    @ResponseBody
    public List<Challenge> getIdChallenges(@PathVariable Integer id) {
        return challengeService.getIdChallenges(id);
    }

    @GetMapping("/challenges")
    @ResponseBody
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }
}

