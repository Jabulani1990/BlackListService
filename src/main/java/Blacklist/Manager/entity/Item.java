package Blacklist.Manager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "itemCategory", referencedColumnName = "categoryName")
    private ItemCategory itemCategory;
}