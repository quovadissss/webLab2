package ru.ssau.webLabs2.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id")
    @SequenceGenerator(name = "project_id", sequenceName = "project_id", allocationSize = 1)
    @Column(name ="pr_id")
    private int id;
    @Column(name ="name_pr")
    private String name;
    @Column(name ="project_description")
    private String description;
    @Column(name ="start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate start_data;
    @Column(name ="end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate end_data;
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Task> tasks;
}
