package com.ajax;

import com.google.common.collect.ImmutableMap;
import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
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
    public HttpResult noParam() {
        Map handler = ImmutableMap.of("resultData", "noParam");

        return HttpResult.ok(handler);
    }

    @GetMapping("/path/{path}")
    public HttpResult paramPath(@PathVariable String path) {
        Map handler = ImmutableMap.of("resultData", path);

        return HttpResult.ok(handler);
    }
    @GetMapping("/param")
    public HttpResult param(@RequestParam String param) {
        Map handler = ImmutableMap.of("resultData", param);

        return HttpResult.ok(handler);
    }


    @PostMapping("paramBody")
    public HttpResult paramBody(@RequestBody CorsRequest corsRequest) {
        Map handler = ImmutableMap.of("resultData", corsRequest);
        return HttpResult.ok(handler);
    }
}
