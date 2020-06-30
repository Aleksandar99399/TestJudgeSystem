package softuni.livedemo.services;

import softuni.livedemo.models.serviceModel.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByName(String name);
}
