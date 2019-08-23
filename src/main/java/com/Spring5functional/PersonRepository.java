package com.Spring5functional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author louis
 * <p>
 * Date: 2019/8/23
 * Description:
 */
public interface PersonRepository {

    Mono<Person> getPerson(int id);

    Flux<Person> allPeople();

    Mono<Void> savePerson(Mono<Person> person);


}
