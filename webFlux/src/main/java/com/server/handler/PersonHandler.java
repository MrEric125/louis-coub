package com.server.handler;

import com.server.PersonService;
import com.server.entity.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 */
@RestController
@RequestMapping("/api")
public class PersonHandler {

    @Autowired
    private PersonService personService;

    @GetMapping("user")
    public Mono<ServerResponse> getAllUser(){
        Flux<Person> allUser = personService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(allUser,Person.class);
    }

    @GetMapping("id/{id}")
    public Mono<ServerResponse> getUserById(@PathVariable long id){
        //获取url上的id
//        Long uid = Long.valueOf(serverRequest.pathVariable("id"));
        Mono<Person> user = personService.getUserById(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user,Person.class);
    }

    @PostMapping("save")
    public Mono<ServerResponse> saveUser(ServerRequest serverRequest){
        Mono<Person> user = serverRequest.bodyToMono(Person.class);
        return ServerResponse.ok().build(personService.saveUser(user));
    }





}
