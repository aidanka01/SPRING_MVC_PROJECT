package peaksoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Group {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "date_of_start")
    private String dateOfStart;
    @Column(name = "date_of_finish")
    private String dateOfFinish;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "groups_courses",
            joinColumns = @JoinColumn(name ="group_id" ),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
   private List<Course> courses;

    // student relation
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groups")
    List<Student> students;

    @Transient
    private Long courseId;

}
