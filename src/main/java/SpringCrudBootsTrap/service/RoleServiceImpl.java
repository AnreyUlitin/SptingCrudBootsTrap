package SpringCrudBootsTrap.service;

import SpringCrudBootsTrap.model.Role;
import SpringCrudBootsTrap.repository.RoleRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }


    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }


    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.getById(id);
    }


    @Override
    public Role getRoleByRole(String role) {
        return roleRepository.getRoleByRole(role);
    }
}




