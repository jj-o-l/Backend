package com.example.javaproject.Service;

import com.example.javaproject.Repository.ChallengeRepository;
import com.example.javaproject.Table.Challenge;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public void createChallenge(Map<String, Object> requestData) {
        Challenge challenge = new Challenge();
        challenge.setUserId((Integer) requestData.get("userId"));
        challenge.setMissionId((Integer) requestData.get("missionId"));
        challenge.setTitle((String) requestData.get("title"));
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> checkboxes = objectMapper.convertValue(
                requestData.get("checkboxes"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)
        );
        challenge.setCheckboxes(checkboxes);
        challenge.setSuccess(0);
        challenge.setFailed(0);
        challengeRepository.save(challenge);
    }

    public void editChallenge(Integer id, boolean vote) {
        if(challengeRepository.findById(id).isPresent()) {
            Challenge challenge = challengeRepository.findById(id).get();
            if(vote) {
                challenge.setSuccess(challenge.getSuccess() + 1);
                System.out.println(challenge.getSuccess());
            }
            else {
                challenge.setFailed(challenge.getFailed() + 1);
                System.out.println(challenge.getFailed());
            }
            challengeRepository.save(challenge);
        } else {
            System.out.println("Challenge not found");
        }
    }

    public List<Challenge> getIdChallenges(Integer missionId) {
        return challengeRepository.findALlByMissionId(missionId);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
}
