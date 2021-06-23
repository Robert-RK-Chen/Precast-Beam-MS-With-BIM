package hibernate.model;

import hibernate.abstractModel.AbstractModel;
import hibernate.entities.UsersEntity;

public class UsersModel extends AbstractModel<UsersEntity>
{
    public UsersModel()
    {
        super(UsersEntity.class);
    }
}
