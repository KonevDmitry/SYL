package SYL.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")

@RequiredArgsConstructor
@Getter
@Setter

public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PLAN_PLAN_ID_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "userid")
    private long userID;

    @Column(name = "privelege")
    private String privelege;
}
