package model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String nameOfTicket;

    @OneToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "park_id")
    private Park park;

    @Override
    public String toString(){
        return "Id: " + id + "Name of ticket:" + nameOfTicket + "Child: " + child + "Park:" + park;
    }
}
