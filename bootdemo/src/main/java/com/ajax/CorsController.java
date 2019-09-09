package com.ajax;

import com.google.common.collect.ImmutableMap;
import com.louis.bootmybatis.common.WrapMapper;
import com.louis.bootmybatis.common.Wrapper;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author John·Louis
 * @date create in 2019/9/8
 * description:
 */
@RestController
@RequestMapping("/cors")
public class CorsController {


    @GetMapping("/noParam")
    public Wrapper noParam() {
        Map handler = ImmutableMap.of("resultData", "noParam");

        return WrapMapper.ok(handler);
    }

    @GetMapping("/path/{path}")
    public Wrapper paramPath(@PathVariable String path) {
        Map handler = ImmutableMap.of("resultData", path);

        return WrapMapper.ok(handler);
    }
    @GetMapping("/param")
    public Wrapper param(@RequestParam String param) {
        Map handler = ImmutableMap.of("resultData", param);

        return WrapMapper.ok(handler);
    }


    @PostMapping("paramBody")
    public Wrapper paramBody(@RequestBody CorsRequest corsRequest) {
        Map handler = ImmutableMap.of("resultData", corsRequest);
        return WrapMapper.ok(handler);
    }
}
