package SYL.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_ID_seq")
    private long id;

    @NonNull
    @Column(name = "Email")
    private String email;

    @NonNull
    @Column(name = "Name")
    private String name;

    @NonNull
    @Column(name = "Surname")
    private String surname;

    @NonNull
    @Column(name = "Password")
    private String password;
}