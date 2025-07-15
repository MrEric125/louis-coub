import org.apache.dubbo.config.annotation.Service;

@Service
public class EchoServiceImpl implements EchoService {


    @Override
    public String echo(String name) {
        return "echo" + name;
    }
}
