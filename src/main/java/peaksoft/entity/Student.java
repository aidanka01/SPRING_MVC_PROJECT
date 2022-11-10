package peaksoft.entity;

import lombok.*;
import peaksoft.entity.enumStudyFormat.StudyFormat;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "group_id")
    private Group groups;
    @Transient
    private Long groupId;

}
