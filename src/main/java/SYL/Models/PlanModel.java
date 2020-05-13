package SYL.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PlanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PLAN_PLAN_ID_seq")
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "desc")
    private String desc;

    @NonNull
    @Column(name = "cost")
    private Integer cost;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "plan",
            targetEntity = UserModel.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<UserModel> users = new ArrayList<>();
}
