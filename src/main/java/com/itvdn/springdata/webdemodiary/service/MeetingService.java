package com.itvdn.springdata.webdemodiary.service;

import com.itvdn.springdata.webdemodiary.data.City;
import com.itvdn.springdata.webdemodiary.data.Meeting;
import com.itvdn.springdata.webdemodiary.repository.MeetingRepository;
import com.itvdn.springdata.webdemodiary.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class MeetingService {
    private MeetingRepository meetingRepository;
    private PersonRepository personRepository;

    public List<Meeting> findAvailableMeetings(int personId) {
        return meetingRepository.findAvailableMeetings(personId);
    }

    public void addParticipation(int personId, int meetingId) {
        meetingRepository.findById(meetingId).ifPresent(m -> {
            personRepository.findById(personId).ifPresent(p -> {
                if (m.getPeople() == null) {
                    m.setPeople(new HashSet<>());
                }
                m.getPeople().add(p);
                meetingRepository.save(m);
            });
        });

    }

    public void cancelParticipation(int personId, int meetingId) {
        meetingRepository.findById(meetingId).ifPresent(m -> {
            m.getPeople().removeIf(p -> p.getId() == personId);
            meetingRepository.save(m);
        });


    }
}
