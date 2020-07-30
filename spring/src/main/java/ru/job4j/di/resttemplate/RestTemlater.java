package ru.job4j.di.resttemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestTemlater {

    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {}
        ).getBody();

        Person entity = rest.postForObject(API, new Person("user", "qwerty"), Person.class);

        Person remote = rest.getForObject(API_ID, Person.class, entity.getId());

        rest.put(API, entity);

        rest.delete(API_ID, entity.getId());
    }
}
