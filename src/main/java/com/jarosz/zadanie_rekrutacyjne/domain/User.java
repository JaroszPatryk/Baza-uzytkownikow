package com.jarosz.zadanie_rekrutacyjne.domain;

import lombok.*;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private String surname;
    @XmlElement
    private String login;

}
