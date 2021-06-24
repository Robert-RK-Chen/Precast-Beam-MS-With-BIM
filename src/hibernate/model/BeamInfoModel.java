package hibernate.model;

import hibernate.abstractModel.AbstractModel;
import hibernate.entities.BeamInfoEntity;

/**
 * @author Robert Chen
 */
public class BeamInfoModel extends AbstractModel<BeamInfoEntity> {
    public BeamInfoModel() {
        super(BeamInfoEntity.class);
    }
}
