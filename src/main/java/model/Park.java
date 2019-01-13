package model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Double ticketPrice;

    @Setter(value = AccessLevel.NONE)
    @Column
    private Double profit;

    @OneToMany(mappedBy = "park")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Ticket> tickets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Park_Attractions",
            joinColumns = { @JoinColumn(name = "park_id") },
            inverseJoinColumns = { @JoinColumn(name = "attraction_id") }
    )
    private List<Attraction> attractions;

    public void setProfit() {
        this.profit = (tickets.size()+1) * ticketPrice;
    }

    @Override
    public String toString(){
        return "Id: " + id + "Name:" + name + "Ticket price: " + ticketPrice + "Profit:" + profit;
    }
}
