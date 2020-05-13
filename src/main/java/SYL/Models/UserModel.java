package SYL.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "users")

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_ID_seq")
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "surname")
    private String surname;

    @NonNull
    @Column(name = "email")
    private String email;

    @ColumnTransformer(
            forColumn = "password",
            read =  "pgp_sym_decrypt(" +
                    "    password::bytea, " +
                    "    'ya zaebalsa'" +
                    ")",
            write = "pgp_sym_encrypt( " +
                    "    ?, " +
                    "    'ya zaebalsa'" +
                    ") "
    )
    @NonNull
    @Column(name = "password", columnDefinition = "bytea")
    private String password;

    @JsonBackReference
    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")

    private PlanModel plan;
}