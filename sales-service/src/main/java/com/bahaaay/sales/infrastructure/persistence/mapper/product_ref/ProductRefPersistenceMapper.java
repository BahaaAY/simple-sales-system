package com.bahaaay.sales.infrastructure.persistence.mapper.product_ref;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.sales.domain.entity.product_ref.ProductRef;
import com.bahaaay.sales.infrastructure.persistence.entity.product_ref.ProductRefJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductRefPersistenceMapper {

    public ProductRef productRefJpaEntityToProductRef(ProductRefJpaEntity productRefJpaEntity) {
        if (productRefJpaEntity == null) {
            return null;
        }

        return ProductRef.load(
                ProductId.from(productRefJpaEntity.getId()),
                productRefJpaEntity.getName(),
                productRefJpaEntity.getPrice()
        );
    }
}
