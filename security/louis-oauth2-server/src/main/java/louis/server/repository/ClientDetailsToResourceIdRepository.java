package louis.server.repository;

import louis.server.entity.asocciate.ClientDetailsToResourceId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author louis
 * <p>
 * Date: 2019/11/27
 * Description:
 */
public interface ClientDetailsToResourceIdRepository extends JpaRepository<ClientDetailsToResourceId, Long> {

}
