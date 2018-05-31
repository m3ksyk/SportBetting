package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Role;

public interface RoleService {
    Role getOrCreate(String roleName);
}
