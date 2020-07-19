package framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteURL {

    private String hostname;

    private Integer port;
}
