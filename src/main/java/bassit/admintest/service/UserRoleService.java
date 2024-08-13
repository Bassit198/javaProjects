package bassit.admintest.service;

import bassit.admintest.repo.UserRoleRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;
}
