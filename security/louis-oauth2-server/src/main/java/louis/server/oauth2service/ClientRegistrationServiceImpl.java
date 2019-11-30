//package louis.server.oauth2service;
//
//import louis.server.entity.ClientDetailsEntity;
//import louis.server.service.ClientDetailEntityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientRegistrationService;
//import org.springframework.security.oauth2.provider.NoSuchClientException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author louis
// * <p>
// * Date: 2019/11/29
// * Description:
// */
//@Service
//public class ClientRegistrationServiceImpl implements ClientRegistrationService {
//    @Autowired
//    private ClientDetailEntityService clientDetailEntityService;
//
//    @Override
//    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
//        ClientDetailsEntity clientDetailsEntity = clientDetailEntityService.loadClientByClientId(clientDetails.getClientId());
//
//        if (clientDetailsEntity != null) {
//            throw new ClientAlreadyExistsException("client id " + clientDetails.getClientId() + " already exists");
//        }
//        ClientDetailsEntity.builder()
//                .clientId(clientDetails.getClientId())
//                .clientSecret(clientDetails.getClientSecret())
//                .accessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds())
//                .refreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds())
//                .build();
//
//
//    }
//
//    @Override
//    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public void updateClientSecret(String s, String s1) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public void removeClientDetails(String s) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public List<ClientDetails> listClientDetails() {
//        return null;
//    }
//
//
//}
