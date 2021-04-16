package com.jarosz.zadanie_rekrutacyjne.domain;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.xml.bind.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> searchByParams(SearchParams searchParams) {
        return userRepository.findByParams(searchParams);
    }

    public void createXml() {
        Users users = new Users();
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.setDefaultUseWrapper(false);

            for (int j = 1; j <= 50000; j++) {
                User user = new User();
                user.setId(j);
                System.out.println();
                user.setName("name" + j);
                user.setSurname("surname" + j);
                user.setLogin("login" + j);
                users.getUser().add(user);
            }
            mapper.writeValue(new File
                    ("src\\main\\resources\\users.xml"), users);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void readXml() throws JAXBException{
        File xmlFile = new File("src\\main\\resources\\users.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class, User.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Users user = (Users)jaxbUnmarshaller.unmarshal(xmlFile);

        userRepository.saveAll(user.getUser());
    }
}
