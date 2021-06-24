package hibernate.model;

import hibernate.abstractModel.AbstractModel;
import hibernate.entities.CuringInfoEntity;

/**
 * @author Robert Chen
 */
public class CuringInfoModel extends AbstractModel<CuringInfoEntity> {
    public CuringInfoModel() {
        super(CuringInfoEntity.class);
    }
}
