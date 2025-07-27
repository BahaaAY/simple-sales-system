package com.bahaaay.sales.infrastructure.persistence.mapper.client_ref;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.sales.domain.entity.client_ref.ClientRef;
import com.bahaaay.sales.infrastructure.persistence.entity.client_ref.ClientRefJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientRefPersistenceMapper {

    public ClientRef clientRefJpaEntityToClientRef(ClientRefJpaEntity clientRefJpaEntity) {
        if (clientRefJpaEntity == null) {
            return null;
        }
        return ClientRef.load(
                ClientId.from(clientRefJpaEntity.getId()),
                clientRefJpaEntity.getFullName(),
                clientRefJpaEntity.getMobileNumber()
        );
    }
}
