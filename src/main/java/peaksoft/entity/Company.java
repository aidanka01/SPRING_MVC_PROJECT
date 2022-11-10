package peaksoft.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,mappedBy = "company")
    private List<Course> course;

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }




}


