package hibernate.model;

import hibernate.abstractModel.AbstractModel;
import hibernate.entities.BeamStoreEntity;

/**
 * @author Robert Chen
 */
public class BeamStoreModel extends AbstractModel<BeamStoreEntity>
{
    public BeamStoreModel()
    {
        super(BeamStoreEntity.class);
    }
}
