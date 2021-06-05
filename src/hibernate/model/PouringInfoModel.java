package hibernate.model;

import hibernate.abstractModel.AbstractModel;
import hibernate.entities.PouringInfoEntity;

/**
 * @author Robert Chen
 */
public class PouringInfoModel extends AbstractModel<PouringInfoEntity> {
    public PouringInfoModel()
    {
        super(PouringInfoEntity.class);
    }
}
