package louis.server.repository;

import louis.server.entity.asocciate.ClientDetailsToAuthorizedGrantType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author louis
 * <p>
 * Date: 2019/11/27
 * Description:
 */
public interface ClientDetailsAuthorizedRepository extends JpaRepository<ClientDetailsToAuthorizedGrantType, Long> {

}
