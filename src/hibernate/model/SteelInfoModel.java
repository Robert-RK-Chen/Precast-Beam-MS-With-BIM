package hibernate.model;

import hibernate.abstractModel.AbstractModel;
import hibernate.entities.SteelInfoEntity;

/**
 * @author Robert Chen
 */
public class SteelInfoModel extends AbstractModel<SteelInfoEntity>
{
    public SteelInfoModel()
    {
        super((SteelInfoEntity.class));
    }
}
