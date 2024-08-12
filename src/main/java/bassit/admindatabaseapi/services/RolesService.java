package bassit.admindatabaseapi.services;

import bassit.admindatabaseapi.repositories.RolesRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//this service will interact with the DB
@Log
@Service
public class RolesService {

    private final RolesRepo rolesRepo;

    @Autowired
    public RolesService(RolesRepo rolesRepo) {this.rolesRepo = rolesRepo;}
}
