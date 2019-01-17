package model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attraction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private Integer minAge;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "child_attraction",
            joinColumns = { @JoinColumn(name = "child_id") },
            inverseJoinColumns = { @JoinColumn(name = "attraction_id") }
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Child> children;

    @ManyToMany(mappedBy = "attractions")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Park> parks;


}
