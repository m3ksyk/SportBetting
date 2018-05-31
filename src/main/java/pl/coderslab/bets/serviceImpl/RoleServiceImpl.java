package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.bets.entity.Role;
import pl.coderslab.bets.repository.RoleRepository;
import pl.coderslab.bets.service.RoleService;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getOrCreate(String name) {

        Role role = roleRepository.findByName(name);

        if (role == null) {
            role = new Role();
            role.setName(name);
            role = roleRepository.save(role);
        }

        return role;
    }

}
