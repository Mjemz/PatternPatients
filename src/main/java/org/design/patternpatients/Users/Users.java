package org.design.patternpatients.Users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"Users\"")
public class Users
{@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String role;
    private String email;

}
