package com.jarosz.zadanie_rekrutacyjne.external;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo_sequence_generator")
    @GenericGenerator(
            name = "hilo_sequence_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value="EXAMPLE_SEQ"),
                    @Parameter(name = "initial_value", value="1"),
                    @Parameter(name = "increment_size", value = "100"),
                    @Parameter(name = "optimizer", value = "hilo")
            })
    private int id;
    private String name;
    private String surname;
    private String login;




}
