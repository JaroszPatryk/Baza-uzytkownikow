package com.jarosz.zadanie_rekrutacyjne.domain;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jarosz.zadanie_rekrutacyjne.dataUser.SearchParams;
import com.jarosz.zadanie_rekrutacyjne.external.UserEntity;
import com.twmacinta.util.MD5;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import javax.xml.bind.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public Page<UserEntity> findPaginated(int pageNr, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNr - 1, pageSize);
        return userRepository.findAll(pageable);
    }

    public List<User> searchByParams(SearchParams searchParams) {
        return userRepository.findByParams(searchParams);
    }

    public Object createXml() {
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
        return null;
    }


    public void readXml() throws JAXBException{
        File xmlFile = new File("src\\main\\resources\\users.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class, User.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Users user = (Users)jaxbUnmarshaller.unmarshal(xmlFile);

        userRepository.saveAll(user.getUser());

    }

    public String generateMD5(String surname) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(surname.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
