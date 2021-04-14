package com.jarosz.zadanie_rekrutacyjne.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElement
    List<User> user = new ArrayList<>();
}
