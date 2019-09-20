package com.server;

import com.google.common.collect.Lists;
import com.server.entity.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 */
@Service
public class PersonService {


    private Map<Long,Person> map = new HashMap<Long,Person>(10);

    @PostConstruct
    public void init(){
        map.put(1L,new Person(1L,"admin"));
        map.put(2L,new Person(2L,"admin22"));
        map.put(3L,new Person(3L,"admin33"));
    }

    public Flux<Person> getAllUser() {
        return Flux.fromIterable(map.values());
    }

    public Mono<Person> getUserById(Long id) {
        return Mono.just(map.get(id));
    }

    public Mono<Void> saveUser(Mono<Person> userMono) {
        Mono<Person> mono = userMono.doOnNext(Person ->
                map.put(Person.getId(),Person)
        );
        return mono.then();
    }
}
