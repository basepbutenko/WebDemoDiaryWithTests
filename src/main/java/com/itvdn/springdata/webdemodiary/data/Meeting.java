package com.itvdn.springdata.webdemodiary.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "start", nullable = false)
    private Instant start;

    @Column(name = "finish")
    private Instant finish;

    @ManyToMany
    @JoinTable(
            name = "meeting_person",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> people = new LinkedHashSet<>();

//    @ManyToMany(mappedBy = "people")
//    private Set<Meeting> meetings = new HashSet<>();
}