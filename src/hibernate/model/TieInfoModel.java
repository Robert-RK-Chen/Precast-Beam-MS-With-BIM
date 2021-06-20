package hibernate.model;

import hibernate.abstractModel.AbstractModel;
import hibernate.entities.TieInfoEntity;

/**
 * @author Robert Chen
 */
public class TieInfoModel extends AbstractModel<TieInfoEntity>
{
    public TieInfoModel()
    {
        super((TieInfoEntity.class));
    }
}
