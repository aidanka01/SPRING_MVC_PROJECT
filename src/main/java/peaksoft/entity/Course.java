package peaksoft.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_duration")
    private String duration;

   // company relation
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "company_id")
    private Company company;

    // group relation
     @ManyToMany(cascade ={CascadeType.ALL},mappedBy = "courses" )
    private List<Group> groups;

    // instructor relation
   @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
   private Instructor instructor;



    @Transient
    private Long companyId;

}
