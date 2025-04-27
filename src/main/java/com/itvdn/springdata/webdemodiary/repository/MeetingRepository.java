package com.itvdn.springdata.webdemodiary.repository;

import com.itvdn.springdata.webdemodiary.data.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
    @Query("SELECT m FROM Meeting m WHERE not exists (SELECT 1 FROM Person p WHERE m MEMBER OF p.meetings and p.id=?1)")
    List<Meeting> findAvailableMeetings(int personId);

}