package ru.ssau.webLabs2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pr_id", referencedColumnName = "pr_id")
    private Project id;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id")
    @SequenceGenerator(name = "task_id", sequenceName = "task_id", allocationSize = 1)
    @Column(name ="task_id")
    private int taskID;
    @Column(name ="name_task")
    private String task_name;
    @Column(name ="task_description")
    private String task_description;
    @Column(name ="plan_comp_date")
    @Temporal(TemporalType.DATE)
    private LocalDate plan_com_date;
    @Column(name ="flag_t")
    private boolean flag;
}
