package com.source.spring.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jun.liu
 * @date created on 2020/11/17
 * description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemoParam {

    private String id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemoParam)) return false;

        DemoParam demoParam = (DemoParam) o;

        if (!getId().equals(demoParam.getId())) return false;
        return getName().equals(demoParam.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
