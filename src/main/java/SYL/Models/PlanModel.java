package SYL.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class PlanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PLAN_PLAN_ID_seq")
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "cost")
    private Integer cost;

    @NonNull
    @Column(name = "description")
    private String desc;

    @CollectionTable(name="plans", joinColumns=@JoinColumn(name="id"))
    @Column(name="priveleges", columnDefinition = "text array")
    @Type(type = "list-array")
    private List<String> priveleges;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "plan",
            targetEntity = UserModel.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserModel> users = new ArrayList<>();
}
