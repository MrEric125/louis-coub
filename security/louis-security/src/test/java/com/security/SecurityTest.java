package com.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author louis
 * <p>
 * Date: 2019/12/2
 * Description:
 */

public class SecurityTest {

    @Test
    public void test() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        boolean matches = passwordEncoder.matches("admin", "$10$GV6ODucmont6Ja51ibBUx.l.falrOYVdVhIn3VmOwDkJinXHOltke");
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);

    }
}
