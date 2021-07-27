package louis.server.service;

import louis.server.entity.RedirectUriEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RedirectUriService {

    Optional<RedirectUriEntity> findOneByValue(String value);
}
