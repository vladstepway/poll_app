package by.stepovoy.domain.service;

import by.stepovoy.domain.model.user.Role;
import by.stepovoy.domain.model.user.RoleName;
import by.stepovoy.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
