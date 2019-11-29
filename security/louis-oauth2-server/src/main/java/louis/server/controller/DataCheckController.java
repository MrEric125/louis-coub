package louis.server.controller;

import louis.server.entity.UserEntity;
import louis.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@RestController
@RequestMapping("/checkData")
public class DataCheckController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/{username}/")
    public UserEntity getUser(@PathVariable String username, @RequestParam(required = false) boolean userCache) {

        UserEntity userEntity;
        if (userCache) {
            userEntity = userService.direactGetByCache(username);
        } else {
            userEntity = userService.getUserById(username);
        }
        return userEntity;
    }
    @RequestMapping("/user/clean")
    public String cleanCache() {
        userService.cleanCache();
        return "clean success";
    }
}
