package peaksoft.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @Column(name = "instructor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;

    @OneToOne(cascade = { CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Transient
    private Long courseId;
}
