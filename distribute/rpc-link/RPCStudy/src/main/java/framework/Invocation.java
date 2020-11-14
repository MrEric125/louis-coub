package framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invocation implements Serializable {

    private String interfaceName;

    private String methodName;

    private Class[] paramTypes;

    private Object[] params;
}
