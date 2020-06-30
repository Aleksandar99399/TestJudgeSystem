package softuni.livedemo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.livedemo.models.entities.Role;
import softuni.livedemo.models.serviceModel.RoleServiceModel;
import softuni.livedemo.repositories.RoleRepository;
import softuni.livedemo.services.RoleService;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init(){
        if (this.roleRepository.count()==0){
            Role admin=new Role("ADMIN");
            Role user=new Role("USER");

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }
    @Override
    public RoleServiceModel findByName(String name) {

        return this.roleRepository
                .findByName(name)
                .map(role -> this.modelMapper.map(role,RoleServiceModel.class))
                .orElse(null);
    }
}
